package com.lxisoft.appraisal.service.dto;

import javax.validation.constraints.Size;

public class LateDTO
{
    @Size(max = 50)
    private String type;


    @Size(max=50)
    private String reachedTime;

    private String name;
    public LateDTO() {
        // Empty constructor needed for Jackson.
    }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getReachedTime() {
            return reachedTime;
        }
        public void setReachedTime(String reachedTime) {
            this.reachedTime = reachedTime;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }

        @Override
    public String toString() {
        return "LateDTO{" +
            "type='" + type + '\'' +
            ", reachedTime='" + reachedTime + '\'' +
            "}";

}
}
