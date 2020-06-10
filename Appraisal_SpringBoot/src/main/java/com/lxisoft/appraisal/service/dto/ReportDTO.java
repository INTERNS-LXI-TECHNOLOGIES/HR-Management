package com.lxisoft.appraisal.service.dto;

import javax.validation.constraints.Size;

public class ReportDTO
{

    @Size(max = 50)
    private String type;


    @Size(max=50)
    private String reportTime;

    private String name;
    public ReportDTO() {
        // Empty constructor needed for Jackson.
    }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getReportTime() {
            return reportTime;
        }
        public void setReportTime(String reportTime) {
            this.reportTime = reportTime;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }

        @Override
    public String toString() {
        return "ReportDTO{" +
            "type='" + type + '\'' +
            ", reportTime='" + reportTime + '\'' +
            ", name='" + name + '\'' +
            "}";
    }
}
