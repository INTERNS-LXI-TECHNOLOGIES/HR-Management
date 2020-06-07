package com.lxisoft.appraisal.service.dto;

import javax.validation.constraints.Size;
public class LeaveDTO
{



    @Size(max = 50)
    private String type;


    @Size(max=50)
    private String leaveDate;

    private String name;
    public LeaveDTO() {
        // Empty constructor needed for Jackson.
    }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
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
            ", name='" + name + '\'' +
            "}";
    }

		public Object getId() {
			return null;
		}


}
