package com.lxisoft.appraisal.service.dto;

import javax.validation.constraints.Size;

public class GitDTO
{

    @Size(max = 50)
    private String mark;

    private String date;

    private String name;
    public GitDTO() {
        // Empty constructor needed for Jackson.
    }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        // public String getDate() {
        //     return date;
        // }

        // public void setDate(String date) {
        //     this.date = date;
        // }

        public String getMark() {
            return mark;
        }
        public void setMark(String mark) {
            this.mark = mark;
        }

        @Override
    public String toString() {
        return "GitDTO{" +
            "name='" + name + '\'' +
            ", mark='" + mark + '\'' +
            "}";
    }
}
