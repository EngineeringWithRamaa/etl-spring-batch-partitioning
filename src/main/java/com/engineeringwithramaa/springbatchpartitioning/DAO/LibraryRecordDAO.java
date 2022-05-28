package com.engineeringwithramaa.springbatchpartitioning.DAO;

import com.engineeringwithramaa.springbatchpartitioning.entity.LibraryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryRecordDAO extends JpaRepository<LibraryRecord, Long> {
}
