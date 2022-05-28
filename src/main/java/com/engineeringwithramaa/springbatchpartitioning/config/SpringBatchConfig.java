package com.engineeringwithramaa.springbatchpartitioning.config;

import com.engineeringwithramaa.springbatchpartitioning.entity.LibraryRecord;
import com.engineeringwithramaa.springbatchpartitioning.partition.LibraryRecordRangePartitioner;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    StepBuilderFactory stepBuilderFactory;
    @Autowired
    ItemReader<LibraryRecord> LibraryRecordReader;
    @Autowired
    ItemProcessor<LibraryRecord, LibraryRecord> LibraryRecordProcessor;
    @Autowired
    ItemWriter<LibraryRecord> LibraryRecordWriter;

    @Bean
    public LibraryRecordRangePartitioner libraryRecordRangePartitioner() {
        return new LibraryRecordRangePartitioner();
    }

    @Bean
    public PartitionHandler libraryRecordPartitionerHandler() {
        TaskExecutorPartitionHandler taskExecutorPartitionHandler = new TaskExecutorPartitionHandler();
        taskExecutorPartitionHandler.setGridSize(5);
        taskExecutorPartitionHandler.setTaskExecutor(libraryRecordTaskExecutor());
        taskExecutorPartitionHandler.setStep(libraryRecordSlaveStep());
        return taskExecutorPartitionHandler;
    }

    @Bean
    public Step libraryRecordSlaveStep() {
        return stepBuilderFactory.get("Slave Step")
                .<LibraryRecord, LibraryRecord>chunk(10000)
                .reader(LibraryRecordReader)
                .processor(LibraryRecordProcessor)
                .writer(LibraryRecordWriter)
                .build();
    }

    @Bean
    public Step libraryRecordMasterStep() {
        return stepBuilderFactory.get("Master Step")
                .partitioner(libraryRecordSlaveStep().getName(), libraryRecordRangePartitioner())
                .partitionHandler(libraryRecordPartitionerHandler())
                .build();
    }

    @Bean
    public Job runLibraryRecordJob() {
        return jobBuilderFactory.get("Import Library Records")
                .flow(libraryRecordMasterStep()).end().build();

    }

    @Bean
    public TaskExecutor libraryRecordTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setQueueCapacity(5);
        return taskExecutor;
    }


}
