package OT_Duration_Policy;

public class calculateOTDurationOTPolicy {

    String assignStartTime = "090000";
    String assignEndTime = "173000";

    public static String calculateOTforAssignDays(String OTstartTime, String OTendTime, String assignStartTime,
            String assignEndTime) {

        double start = Double.parseDouble(assignStartTime);
        double end = Double.parseDouble(assignEndTime);

        double otStart = Double.parseDouble(OTstartTime);
        double otEnd = Double.parseDouble(OTendTime);

        if (start >= otStart && otEnd <= end) {
            

        } else {
            return "0 hours";
        }

        return "10 hours";
    }

    public static String calculateOTforOffDays(String OTstartTime, String OTendTime, String assignStartTime,
            String assignEndTime) {

        double start = Double.parseDouble(assignStartTime);
        double end = Double.parseDouble(assignEndTime);

        double otStart = Double.parseDouble(OTstartTime);
        double otEnd = Double.parseDouble(OTendTime);

        return "10 days";
    }

    public static void main(String[] args) {

    }

}