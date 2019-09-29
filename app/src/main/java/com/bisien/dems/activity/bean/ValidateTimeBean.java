package com.bisien.dems.activity.bean;

public class ValidateTimeBean {

    /**
     * code : 0
     * message : 请继续使用
     * data : {"infoId":2,"locationId":"123456","locationName":null,"validityPeriod":"2019-09-02 09:40"}
     */

    private String code;
    private String message;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * infoId : 2
         * locationId : 123456
         * locationName : null
         * validityPeriod : 2019-09-02 09:40
         */

        private int infoId;
        private String locationId;
        private Object locationName;
        private String validityPeriod;

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public String getLocationId() {
            return locationId;
        }

        public void setLocationId(String locationId) {
            this.locationId = locationId;
        }

        public Object getLocationName() {
            return locationName;
        }

        public void setLocationName(Object locationName) {
            this.locationName = locationName;
        }

        public String getValidityPeriod() {
            return validityPeriod;
        }

        public void setValidityPeriod(String validityPeriod) {
            this.validityPeriod = validityPeriod;
        }
    }
}
