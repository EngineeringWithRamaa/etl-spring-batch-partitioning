package com.engineeringwithramaa.springbatchpartitioning.batch;

import com.engineeringwithramaa.springbatchpartitioning.DAO.LibraryRecordDAO;
import com.engineeringwithramaa.springbatchpartitioning.entity.LibraryRecord;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryRecordWriter implements ItemWriter<LibraryRecord> {
    @Autowired
    private LibraryRecordDAO libraryRecordDAO;
    @Override
    public void write(List<? extends LibraryRecord> list) throws Exception {
        libraryRecordDAO.saveAll(list);
    }
}
