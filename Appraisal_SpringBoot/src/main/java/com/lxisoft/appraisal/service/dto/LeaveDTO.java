package com.lxisoft.appraisal.service.dto;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import com.lxisoft.appraisal.domain.Leave;

import io.github.jhipster.config.JHipsterDefaults.Cache.Infinispan.Local;
public class LeaveDTO
{

    private long userExtraId;

    @Size(max = 50)
    private String type;


    @Size(max=50)
    private LocalDate leaveDate;

    public LeaveDTO() {
        // Empty constructor needed for Jackson.
    }
    public LeaveDTO(Leave leave)
        {
            this.userExtraId=leave.getId();
            this.leaveDate= leave.getDate();
            this.type= leave.getType();
        }

        public Long getuserExtraId() {
            return userExtraId;
        }

        public void setuserExtraId(Long userExtraId) {
            this.userExtraId = userExtraId;
        }
        public LocalDate getleaveDate() {
            return leaveDate;
        }
        public void setleaveDate(LocalDate leaveDate) {
            this.leaveDate = leaveDate;
        }
        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }

        @Override
    public String toString() {
        return "UserDTO{" +
            "type='" + type + '\'' +
            ", leaveDate='" + leaveDate + '\'' +
            ",userExtraId='" + userExtraId + '\'' +
            "}";
    }
}