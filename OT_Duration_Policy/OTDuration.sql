USE [TMW]
GO
/****** Object:  StoredProcedure [dbo].[getOTDate_Duration]    Script Date: 8/19/2024 1:11:18 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

ALTER PROCEDURE [dbo].[getOTDate_Duration] (
@SDate nvarchar(Max),
@EDate  nvarchar(Max),
@stime nvarchar(Max),
@etime  nvarchar(Max),
@eSK  BIGINT)
AS
BEGIN
DECLARE @StartDate DATETIME
DECLARE @EndDate DATETIME
DECLARE @empSK  BIGINT
DECLARE @EndTime nvarchar(20)
DECLARE @StartTime nvarchar(20)
DECLARE @t1 nvarchar(20)
DECLARE @count nvarchar(6) ='';
DECLARE @status int
DECLARE @diffTime int
DECLARE @diffHour INT
DECLARE @diffMinute INT = 0;
DECLARE @diffMinute1 INT = 0;

DECLARE @tempStartHour INT
DECLARE @tempStartMinute INT

DECLARE @tempEndHour INT
DECLARE @tempEndMinute INT

DECLARE @totalOTHours INT
DECLARE @startTotalMinutes INT
DECLARE @endTotalMinutes INT

DECLARE @totalAssignMinutes INT
------------------------------------------------------
DECLARE @OTStartHour INT
DECLARE @OTStartMinute INT

DECLARE @OTEndHour INT
DECLARE @OTEndMinute INT

DECLARE @startOtTotalMinutes INT
DECLARE @endOtTotalMinutes INT

DECLARE @totalOTMinutes INT

DECLARE @durationHours INT
DECLARE @remainingMinutes INT

DECLARE @workPolicyExists INT
DECLARE @timeintimeoutExists INT
DECLARE @assignDayExists INT

CREATE TABLE #d (duration nvarchar(10))
SET @StartDate = @SDate
SET @EndDate = @EDate;
SET @empSK = @eSK ;
WITH DateRange(DateData) AS 
(
    SELECT @StartDate as Date
    UNION ALL
    SELECT DATEADD(d,1,DateData)
    FROM DateRange 
    WHERE DateData < @EndDate
)
SELECT convert(varchar, DateData, 112) as d into #datearray  FROM DateRange
--select d from #datearray
SET @count = 0
DECLARE TimeCur CURSOR FOR SELECT d FROM #datearray
OPEN TimeCur
FETCH NEXT FROM TimeCur
INTO @t1
WHILE @@FETCH_STATUS = 0
BEGIN

SELECT @workPolicyExists = COUNT(*)
FROM Employeeworkpolicy h
JOIN EmployeeWorkPolicyDetail de ON h.syskey = de.parentid
WHERE h.recordstatus = 1
AND de.n4 = @empSK
AND (@t1 BETWEEN h.t3 AND h.t4)

SELECT @timeintimeoutExists = COUNT(*)
FROM Att102 
WHERE T3 = @t1 
AND N1 = @empSK

-- Assign Day Exist or Not 
SELECT @assignDayExists = COUNT(*)
FROM EmployeeWorkPolicyDetail wd
INNER JOIN att012 at ON wd.n3 = at.syskey
WHERE wd.RecordStatus <> 4
AND at.RecordStatus <> 4
AND wd.n2 = 1
AND @t1 = wd.t5
AND wd.n4 = @empSK;


IF @workPolicyExists > 0 AND @timeintimeoutExists > 0 AND @assignDayExists > 0
BEGIN
	SELECT 
	@endTime= REPLACE(CONVERT (varchar(8),CONVERT(TIME,at.t4), 108),':','')	,
	@startTime = REPLACE(CONVERT (varchar(8),CONVERT(TIME,at.t3), 108),':','')	
	FROM EmployeeWorkPolicyDetail wd INNER JOIN att012 at on wd.n3 = at.syskey
	WHERE  wd.RecordStatus<>4 AND at.RecordStatus<>4 AND wd.n2=1 and @t1 = wd.t5 and wd.n4 = @empSK

	--Converting Assgin Time into Minutes
    SET @tempStartHour = CONVERT(INT, SUBSTRING(@startTime, 1, 2))
    SET @tempStartMinute = CONVERT(INT, SUBSTRING(@startTime, 3, 2))
	SET @tempEndHour = CONVERT (INT , SUBSTRING(@EndTime, 1,2))
	SET @tempEndMinute = CONVERT (INT , SUBSTRING(@EndTime , 3,2))

	--Converting OT Time into Minutes
	SET @OTStartHour = CONVERT(INT, SUBSTRING(@stime, 1, 2))
    SET @OTStartMinute = CONVERT(INT, SUBSTRING(@stime, 3, 2))
	SET @OTEndHour = CONVERT (INT , SUBSTRING(@etime, 1,2))
	SET @OTEndMinute = CONVERT (INT , SUBSTRING(@etime , 3,2))
	
	--SELECT @startTime as OfficeStartTime , @endTime as OfficeEndTime, @stime as StartTime, @etime as EndTime  -- Office Time
	IF @stime < @StartTime AND @etime <= @EndTime
	BEGIN
		--SELECT '@StartTime >= @stime'
		IF @tempStartMinute >= @OTStartMinute
			BEGIN
			--SELECT '@tempStartMinute >= @OTStartMinute'
			SET @diffMinute = @tempStartMinute - @OTStartMinute
			--SELECT @diffMinute AS DifferentMinute
			END
		ELSE
	BEGIN
			--SELECT '@tempStartMinute <= @OTStartMinute'
			SET @tempStartHour = @tempStartHour - 1
			SET @diffMinute = (@tempStartMinute + 60) - @OTStartMinute
			--SELECT @diffMinute AS DifferentMinute
			END
		
		--Hour Different
		SET @diffHour = @tempStartHour - @OTStartHour
		--SELECT @diffHour AS DifferentHour

		SET @startOtTotalMinutes = (@diffHour*60) + @diffMinute
		SET @durationHours = @startOtTotalMinutes/60;
		SET @remainingMinutes =  @startOtTotalMinutes%60;
		--SELECT @durationHours AS OTDurationHour , @remainingMinutes AS OTDurationMinutes 
	END

	ELSE IF @stime >= @StartTime AND @etime >= @EndTime
		BEGIN
			--SELECT '@stime > @tempStartHour AND @etime > @tempEndHour' 
			IF @tempEndMinute > @OTEndMinute
				BEGIN
				SET @OTEndHour = @OTEndHour-1
				SET @diffMinute = (@OTEndMinute + 60) - @tempEndMinute
				--SELECT @diffMinute AS DifferentMinute
				END
			ELSE 
				BEGIN
				SET @diffMinute =  @OTEndMinute - @tempEndMinute
				--SELECT @diffMinute AS DifferentMinute
				END

		--Hour Different
		SET @diffHour = @OTEndHour - @tempEndHour
		--SELECT @diffHour AS DifferentHour

		SET @endOtTotalMinutes = (@diffHour * 60) + @diffMinute
		SET @durationHours = @endOtTotalMinutes /60;
		SET @remainingMinutes = @endOtTotalMinutes %60;

		--SELECT @durationHours AS OTDurationHour , @remainingMinutes AS OTDurationMinutes
	END
	ELSE IF @StartTime > @stime AND @etime >= @EndTime
	BEGIN
			--SELECT '@StartTime < @stime AND @etime > @EndTime'
			IF @OTStartMinute > @tempStartMinute
			BEGIN
				SET @tempStartHour = @tempStartHour-1
				SET @diffMinute = (@tempStartMinute+60) - @OTStartMinute
				--SELECT @diffMinute AS DifferentMinute
			END
			ELSE IF @tempStartMinute > @OTStartMinute
			BEGIN
				SET @diffMinute = @tempStartMinute - @OTStartMinute
				--SELECT @diffMinute AS DifferentMinute
			END

			IF @tempEndMinute > @OTEndMinute
			BEGIN
				SET @OTEndHour = @OTEndHour-1
				SET @diffMinute1 = (@OTEndMinute+60) - @tempEndMinute
				--SELECT @diffMinute AS DifferentMinute
			END
			ELSE IF @tempEndMinute < @OTEndMinute
			BEGIN
				--SELECT @OTEndMinute , @tempEndMinute
				SET @diffMinute1 = @OTEndMinute - @tempEndMinute
				--SELECT @diffMinute AS DifferentMinute	
			END
	
		--Hour Difference
		SET @diffHour = (@tempStartHour - @OTStartHour) + (@OTEndHour - @tempEndHour);
		--SELECT @diffHour AS DifferentHour

		SET @totalOTMinutes = (@diffHour*60) + @diffMinute + @diffMinute1

		SET @durationHours = @totalOTMinutes /60;
		SET @remainingMinutes = @totalOTMinutes %60;

		--SELECT @durationHours AS OTDurationHour , @remainingMinutes AS OTDurationMinutes		

	END
	ELSE
		BEGIN
			--SELECT 'UNVALID'
			SET @durationHours = 0
			SET  @remainingMinutes = 0
			--SELECT @durationHours AS OTDurationHour , @remainingMinutes AS OTDurationMinutes
		END

END -- END OF First IF Statement

ELSE IF @workPolicyExists > 0  AND @assignDayExists = 0 AND @timeintimeoutExists > 0
    BEGIN 
        --Select 'IF the User has No Assign Time'
		--Converting Assgin Time into Minutes
		SET @tempStartHour = CONVERT(INT, SUBSTRING(@startTime, 1, 2))
		SET @tempStartMinute = CONVERT(INT, SUBSTRING(@startTime, 3, 2))
		SET @tempEndHour = CONVERT (INT , SUBSTRING(@EndTime, 1,2))
		SET @tempEndMinute = CONVERT (INT , SUBSTRING(@EndTime , 3,2))

		--Converting OT Time into Minutes
		SELECT
		@endTime= REPLACE(CONVERT (varchar(8),CONVERT(TIME,at.t4), 108),':','')	,
		@startTime = REPLACE(CONVERT (varchar(8),CONVERT(TIME,at.t3), 108),':','')	
		FROM EmployeeWorkPolicyDetail wd INNER JOIN att012 at on wd.n3 = at.syskey
		WHERE  wd.RecordStatus<>4 AND at.RecordStatus<>4 AND wd.n2=1 and @t1 = wd.t5 and wd.n4 = @empSK

		SET @OTStartHour = CONVERT(INT, SUBSTRING(@stime, 1, 2))
		SET @OTStartMinute = CONVERT(INT, SUBSTRING(@stime, 3, 2))
		SET @OTEndHour = CONVERT (INT , SUBSTRING(@etime, 1,2))
		SET @OTEndMinute = CONVERT (INT , SUBSTRING(@etime , 3,2))

        IF(@OTEndHour >= @OTStartHour) 
            BEGIN 
                IF (@OTStartMinute + @OTEndMinute) < 60
                BEGIN
                    SET @totalOTHours = @OTEndHour - @OTStartHour;
                    SET @totalOTMinutes =((@OTEndHour * 60) + @OTEndMinute) - ((@OTStartHour * 60) + @OTStartMinute);
                END

                ELSE 
                BEGIN
                    SET @totalOTHours = @OTEndHour - @OTStartHour;
			        SET @totalOTMinutes =((@OTEndHour * 60) + @OTEndMinute) - ((@OTStartHour * 60) + @OTStartMinute);
                END
            END
        ELSE
            BEGIN  
                SET @durationHours = 0;
				SET @remainingMinutes = 0;
            END
         
		--SELECT @totalOTMinutes
		-- Calculate the final duration in hours and remaining minutes
		SET @durationHours = @totalOTMinutes / 60;
		SET @remainingMinutes = @totalOTMinutes % 60;

    END
FETCH NEXT FROM TimeCur
INTO @t1
END

CLOSE TimeCur
DEALLOCATE TimeCur
	
	SET @count = CONCAT(FORMAT(@durationHours, '00'), FORMAT(@remainingMinutes, '00'));
	--SELECT @count 
	--SELECT @count AS CountDuration;

INSERT INTO #d VALUES (@count);
SELECT @count AS 'duration'; 
DROP TABLE #datearray
DROP TABLE #d
END