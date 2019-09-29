package com.bisien.dems.activity.bean;

import java.util.List;

public class AlarmsBean {

    /**
     * draw : 0
     * recordsTotal : 365
     * recordsFiltered : 365
     * data : [{"id":1891563857688952,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"category":2,"startTime":"2019-07-23 12:54:49","endTime":"2019-07-23 12:54:50","confirmTime":null,"confirmer":null,"confirmRemark":null},{"id":1886563857654631,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"category":2,"startTime":"2019-07-23 12:54:15","endTime":"2019-07-23 12:54:16","confirmTime":null,"confirmer":null,"confirmRemark":null},{"id":1881563857627057,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"category":2,"startTime":"2019-07-23 12:53:47","endTime":null,"confirmTime":"2019-09-11 17:24:49","confirmer":"-1","confirmRemark":"fadsf"},{"id":1876563857605427,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"category":2,"startTime":"2019-07-23 12:53:25","endTime":null,"confirmTime":"2019-09-11 17:13:11","confirmer":"-1","confirmRemark":""},{"id":1863563857511515,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"category":2,"startTime":"2019-07-23 12:51:52","endTime":null,"confirmTime":"2019-09-11 17:13:11","confirmer":"-1","confirmRemark":""}]
     */

    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<DataBean> data;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1891563857688952
         * description : null
         * name : 烟感告警
         * stationName : 局站A
         * houseName : 二楼实验机房
         * equipmentName : 烟温采集器
         * stationId : 8546069125419008
         * houseId : 8622681966107648
         * equipmentId : 1068538191433335
         * alarmId : 1066560585003365
         * triggerValue : 0.0
         * alarmGrade : 2
         * category : 2
         * startTime : 2019-07-23 12:54:49
         * endTime : 2019-07-23 12:54:50
         * confirmTime : null
         * confirmer : null
         * confirmRemark : null
         */

        private long id;
        private Object description;
        private String name;
        private String stationName;
        private String houseName;
        private String equipmentName;
        private long stationId;
        private long houseId;
        private long equipmentId;
        private long alarmId;
        private double triggerValue;
        private int alarmGrade;
        private int category;
        private String startTime;
        private String endTime;
        private Object confirmTime;
        private Object confirmer;
        private Object confirmRemark;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStationName() {
            return stationName;
        }

        public void setStationName(String stationName) {
            this.stationName = stationName;
        }

        public String getHouseName() {
            return houseName;
        }

        public void setHouseName(String houseName) {
            this.houseName = houseName;
        }

        public String getEquipmentName() {
            return equipmentName;
        }

        public void setEquipmentName(String equipmentName) {
            this.equipmentName = equipmentName;
        }

        public long getStationId() {
            return stationId;
        }

        public void setStationId(long stationId) {
            this.stationId = stationId;
        }

        public long getHouseId() {
            return houseId;
        }

        public void setHouseId(long houseId) {
            this.houseId = houseId;
        }

        public long getEquipmentId() {
            return equipmentId;
        }

        public void setEquipmentId(long equipmentId) {
            this.equipmentId = equipmentId;
        }

        public long getAlarmId() {
            return alarmId;
        }

        public void setAlarmId(long alarmId) {
            this.alarmId = alarmId;
        }

        public double getTriggerValue() {
            return triggerValue;
        }

        public void setTriggerValue(double triggerValue) {
            this.triggerValue = triggerValue;
        }

        public int getAlarmGrade() {
            return alarmGrade;
        }

        public void setAlarmGrade(int alarmGrade) {
            this.alarmGrade = alarmGrade;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
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

        public Object getConfirmTime() {
            return confirmTime;
        }

        public void setConfirmTime(Object confirmTime) {
            this.confirmTime = confirmTime;
        }

        public Object getConfirmer() {
            return confirmer;
        }

        public void setConfirmer(Object confirmer) {
            this.confirmer = confirmer;
        }

        public Object getConfirmRemark() {
            return confirmRemark;
        }

        public void setConfirmRemark(Object confirmRemark) {
            this.confirmRemark = confirmRemark;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", description=" + description +
                    ", name='" + name + '\'' +
                    ", stationName='" + stationName + '\'' +
                    ", houseName='" + houseName + '\'' +
                    ", equipmentName='" + equipmentName + '\'' +
                    ", stationId=" + stationId +
                    ", houseId=" + houseId +
                    ", equipmentId=" + equipmentId +
                    ", alarmId=" + alarmId +
                    ", triggerValue=" + triggerValue +
                    ", alarmGrade=" + alarmGrade +
                    ", category=" + category +
                    ", startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    ", confirmTime=" + confirmTime +
                    ", confirmer=" + confirmer +
                    ", confirmRemark=" + confirmRemark +
                    '}';
        }
    }
}
