package com.engineeringwithramaa.springbatchpartitioning.controller;

import com.engineeringwithramaa.springbatchpartitioning.DTO.UserResponseDTO;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/etl/partition-batch")
public class LibraryRecordController {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job;

    @GetMapping
    public ResponseEntity<UserResponseDTO> startBatchPartitioning() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("Time Stamp ", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(maps);

        JobExecution execution = jobLauncher.run(job, jobParameters);

        Date endTime = execution.getEndTime();
        Date startTime = execution.getStartTime();

        long differenceInMilliSeconds
                = Math.abs(endTime.getTime() - startTime.getTime());

        long differenceInHours
                = (differenceInMilliSeconds / (60 * 60 * 1000)) % 24;

        long differenceInMinutes
                = (differenceInMilliSeconds / (60 * 1000)) % 60;

        long differenceInSeconds
                = (differenceInMilliSeconds / 1000) % 60;

        String jobFeedback = execution.getStatus().toString().toLowerCase() + " in under " + differenceInHours + " hr "
                              + differenceInMinutes + " mins " + differenceInSeconds + " secs";

        UserResponseDTO userResponse = new UserResponseDTO(execution.getJobId().toString(),
                                       execution.getStatus().toString(),
                                        jobFeedback, startTime.toString(), endTime.toString());

        return ResponseEntity.ok(userResponse);
    }
}
