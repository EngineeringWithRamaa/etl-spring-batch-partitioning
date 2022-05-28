package com.engineeringwithramaa.springbatchpartitioning.batch;

import com.engineeringwithramaa.springbatchpartitioning.entity.LibraryRecord;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LibraryRecordReader {
    @Bean
    @StepScope
    public FlatFileItemReader<LibraryRecord> libraryBeanReader(@Value("${input1}") Resource resource) throws IOException {
        FlatFileItemReader<LibraryRecord> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(resource);
        //System.out.println("File Resource " + resource.getFile());
        flatFileItemReader.setName("library-record-reader");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(libraryBeanlineMapper());

        return flatFileItemReader;
    }

    @Bean
    @StepScope
    public LineMapper<LibraryRecord> libraryBeanlineMapper() {
        DefaultLineMapper<LibraryRecord> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();

        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] {"recordId","resourceType","bnbNumber","name",
                "country","place","publisher","dateOfPublication",
                "physicalDescription","deweyClassification", "languages"});

        defaultLineMapper.setLineTokenizer(lineTokenizer);

        BeanWrapperFieldSetMapper<LibraryRecord> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(LibraryRecord.class);

        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;

    }

}
