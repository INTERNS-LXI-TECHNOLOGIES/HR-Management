package com.lxisoft.appraisal.service.dto;

import javax.validation.constraints.Size;

public class JiraDTO {

    @Size(max = 50)
    private String hour;

    private String date;

    private String name;
    public JiraDTO() {
        // Empty constructor needed for Jackson.
    }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHour() {
            return hour;
        }
        public void setHour(String hour) {
            this.hour = hour;
        }
        // public String getDate() {
        //     return date;
        // }

        // public void setDate(String date) {
        //     this.date = date;
        // }

        @Override
    public String toString() {
        return "JiraDTO{" +
            "name='" + name + '\'' +
            ", hour='" + hour + '\'' +
            "}";
    }


}
