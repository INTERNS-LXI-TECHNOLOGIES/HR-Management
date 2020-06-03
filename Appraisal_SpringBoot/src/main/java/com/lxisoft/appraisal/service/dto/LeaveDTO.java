package com.lxisoft.appraisal.service.dto;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import com.lxisoft.appraisal.domain.Leave;

import io.github.jhipster.config.JHipsterDefaults.Cache.Infinispan.Local;
public class LeaveDTO
{



    @Size(max = 50)
    private String type;


    @Size(max=50)
    private String leaveDate;

    public LeaveDTO() {
        // Empty constructor needed for Jackson.
    }

        // public Long getuserExtraId() {
        //     return userExtraId;
        // }

        // public void setuserExtraId(Long userExtraId) {
        //     this.userExtraId = userExtraId;
        // }
        public String getleaveDate() {
            return leaveDate;
        }
        public void setleaveDate(String leaveDate) {
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
        return "LeaveDTO{" +
            "type='" + type + '\'' +
            ", leaveDate='" + leaveDate + '\'' +

            "}";
    }
}
