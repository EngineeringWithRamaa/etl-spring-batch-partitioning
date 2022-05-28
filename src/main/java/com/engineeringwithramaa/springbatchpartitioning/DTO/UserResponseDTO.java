package com.engineeringwithramaa.springbatchpartitioning.DTO;

public class UserResponseDTO {
    private String jobId;
    private String status;
    private String feedback;
    private String startTime;
    private String endTime;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String jobId, String status, String feedback, String startTime, String endTime) {
        this.jobId = jobId;
        this.status = status;
        this.feedback = feedback;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "jobId='" + jobId + '\'' +
                ", status='" + status + '\'' +
                ", feedback='" + feedback + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
