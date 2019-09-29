package com.bisien.dems.activity.bean;

import java.util.List;

public class AlarmsHistoryBean {

    /**
     * draw : 10
     * recordsTotal : 23
     * recordsFiltered : 23
     * data : [{"id":1022563858190522,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 13:03:11","endTime":"2019-07-23 13:03:16","confirmTime":"2019-09-09 16:52:50","confirmer":"admin","confirmRemark":"写否则本质上徐1"},{"id":1997563858161576,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 13:02:42","endTime":"2019-07-23 13:02:43","confirmTime":"2019-09-11 16:57:40","confirmer":"admin","confirmRemark":"发是创建和国际化  "},{"id":1996563858150835,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 13:02:31","endTime":"2019-07-23 13:02:32","confirmTime":"2019-09-11 16:57:40","confirmer":"admin","confirmRemark":"发是创建和国际化  "},{"id":1991563858106626,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 13:01:47","endTime":"2019-07-23 13:01:48","confirmTime":"2019-09-11 16:58:20","confirmer":"-1","confirmRemark":""},{"id":1986563858091872,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 13:01:32","endTime":"2019-07-23 13:01:34","confirmTime":"2019-09-11 16:58:50","confirmer":"-1","confirmRemark":""},{"id":1941563858013390,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 13:00:13","endTime":"2019-07-23 13:00:23","confirmTime":"2019-09-11 16:58:50","confirmer":"-1","confirmRemark":""},{"id":1936563858001142,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 13:00:01","endTime":"2019-07-23 13:00:03","confirmTime":"2019-09-11 16:58:50","confirmer":"-1","confirmRemark":""},{"id":1935563857994621,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:59:55","endTime":"2019-07-23 12:59:56","confirmTime":"2019-09-11 16:58:50","confirmer":"-1","confirmRemark":""},{"id":1930563857943271,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:59:03","endTime":"2019-07-23 12:59:05","confirmTime":"2019-09-11 16:58:50","confirmer":"-1","confirmRemark":""},{"id":1925563857927113,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:58:47","endTime":"2019-07-23 12:58:49","confirmTime":"2019-09-11 16:58:50","confirmer":"-1","confirmRemark":""},{"id":1916563857841210,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:57:21","endTime":"2019-07-23 12:57:23","confirmTime":"2019-09-11 16:58:50","confirmer":"-1","confirmRemark":""},{"id":1911563857819588,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:57:00","endTime":"2019-07-23 12:57:01","confirmTime":"2019-09-11 16:58:50","confirmer":"-1","confirmRemark":""},{"id":1902563857762461,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:56:02","endTime":"2019-07-23 12:56:04","confirmTime":"2019-09-11 16:58:50","confirmer":"-1","confirmRemark":""},{"id":1893563857711064,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:55:11","endTime":"2019-07-23 12:55:13","confirmTime":"2019-09-11 16:58:50","confirmer":"-1","confirmRemark":""},{"id":1892563857700295,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:55:00","endTime":"2019-07-23 12:55:08","confirmTime":"2019-09-11 17:34:36","confirmer":"-1","confirmRemark":"kilhjniolkhnuiouhi"},{"id":1891563857688952,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:54:49","endTime":"2019-07-23 12:54:50","confirmTime":"2019-09-19 15:43:30","confirmer":"-1","confirmRemark":"该事件正在处理中!"},{"id":1886563857654631,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:54:15","endTime":"2019-07-23 12:54:16","confirmTime":"2019-09-17 10:43:57","confirmer":"admin","confirmRemark":"dsadas"},{"id":1768563856836543,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:40:37","endTime":"2019-07-23 12:40:38","confirmTime":"2019-09-19 16:01:50","confirmer":"-1","confirmRemark":"延期处理!"},{"id":1763563856815078,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:40:15","endTime":"2019-07-23 12:40:17","confirmTime":"2019-09-19 16:21:06","confirmer":"-1","confirmRemark":"该事件正在处理中!"},{"id":1750563856706443,"description":null,"name":"烟感告警","stationName":"局站A","houseName":"二楼实验机房","equipmentName":"烟温采集器","stationId":8546069125419008,"houseId":8622681966107648,"equipmentId":1068538191433335,"alarmId":1066560585003365,"triggerValue":0,"alarmGrade":2,"startTime":"2019-07-23 12:38:26","endTime":"2019-07-23 12:38:28","confirmTime":"2019-09-23 10:59:36","confirmer":"-1","confirmRemark":"该事件正在处理中!"}]
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
         * id : 1022563858190522
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
         * startTime : 2019-07-23 13:03:11
         * endTime : 2019-07-23 13:03:16
         * confirmTime : 2019-09-09 16:52:50
         * confirmer : admin
         * confirmRemark : 写否则本质上徐1
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
        private String startTime;
        private String endTime;
        private String confirmTime;
        private String confirmer;
        private String confirmRemark;

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

        public String getConfirmTime() {
            return confirmTime;
        }

        public void setConfirmTime(String confirmTime) {
            this.confirmTime = confirmTime;
        }

        public String getConfirmer() {
            return confirmer;
        }

        public void setConfirmer(String confirmer) {
            this.confirmer = confirmer;
        }

        public String getConfirmRemark() {
            return confirmRemark;
        }

        public void setConfirmRemark(String confirmRemark) {
            this.confirmRemark = confirmRemark;
        }
    }
}
