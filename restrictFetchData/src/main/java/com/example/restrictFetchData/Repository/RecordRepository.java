package com.example.restrictFetchData.Repository;

import com.example.restrictFetchData.Entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record,Long> {
}
