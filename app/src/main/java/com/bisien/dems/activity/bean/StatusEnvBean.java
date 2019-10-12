package com.bisien.dems.activity.bean;

import java.util.List;

public class StatusEnvBean {


    /**
     * code : 0
     * message :
     * data : [{"id":1007569567830928,"name":"温湿度2","category":4,"houseId":8622681966107648,"signals":[{"id":1009569567830935,"description":"","name":"湿度","equipmentId":1007569567830928,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":1,"unit":"%HR","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":30,"communicationState":null,"visible":true,"displayIndex":2,"code":"d_humi","signalValueToStatus":[]},{"id":1008569567830935,"description":"","name":"温度","equipmentId":1007569567830928,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":2,"unit":"℃","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":30,"communicationState":null,"visible":true,"displayIndex":1,"code":"d_temp","signalValueToStatus":[]}]},{"id":1008570672139622,"name":"烟感1","category":2,"houseId":8622681966107648,"signals":[{"id":1009570672139640,"description":"","name":"1#烟温报警信号","equipmentId":1008570672139622,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":6,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":null,"communicationState":null,"visible":true,"displayIndex":1,"code":"d_smoke","signalValueToStatus":[]}]},{"id":1013569567852118,"name":"温湿度1","category":4,"houseId":8622681966107648,"signals":[{"id":1015569567852122,"description":"","name":"温度","equipmentId":1013569567852118,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":2,"unit":"℃","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":30,"communicationState":null,"visible":true,"displayIndex":1,"code":"d_temp","signalValueToStatus":[]},{"id":1014569567852122,"description":"","name":"湿度","equipmentId":1013569567852118,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":1,"unit":"%HR","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":30,"communicationState":null,"visible":true,"displayIndex":2,"code":"d_humi","signalValueToStatus":[]}]},{"id":1019569567874657,"name":"烟感2","category":2,"houseId":8622681966107648,"signals":[{"id":1020569567874659,"description":"","name":"1#烟温报警信号","equipmentId":1019569567874657,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":6,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":null,"communicationState":null,"visible":true,"displayIndex":1,"code":"d_smoke","signalValueToStatus":[]}]},{"id":1019570672173950,"name":"水浸1","category":5,"houseId":8622681966107648,"signals":[{"id":1020570672173950,"description":"","name":"1#水浸报警信号","equipmentId":1019570672173950,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":3,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":null,"communicationState":null,"visible":true,"displayIndex":1,"code":"d_water","signalValueToStatus":[]}]},{"id":1030570672200916,"name":"定位水浸","category":5,"houseId":8622681966107648,"signals":[{"id":1035570672200983,"description":"","name":"漏水信号","equipmentId":1030570672200916,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":1,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":null,"communicationState":null,"visible":true,"displayIndex":1,"code":"d_water","signalValueToStatus":[]},{"id":1031570672200918,"description":"","name":"设备故障信号","equipmentId":1030570672200916,"parentId":null,"samplerUnitId":null,"channelType":1,"category":1,"channelNo":2,"unit":"","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":null,"communicationState":null,"visible":true,"displayIndex":2,"code":"d_water_mal","signalValueToStatus":[]},{"id":1039570672201043,"description":"","name":"漏水位置信号","equipmentId":1030570672200916,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":3,"unit":"M","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":30,"communicationState":null,"visible":true,"displayIndex":3,"code":"d_water_pos","signalValueToStatus":[]}]}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1007569567830928
         * name : 温湿度2
         * category : 4
         * houseId : 8622681966107648
         * signals : [{"id":1009569567830935,"description":"","name":"湿度","equipmentId":1007569567830928,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":1,"unit":"%HR","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":30,"communicationState":null,"visible":true,"displayIndex":2,"code":"d_humi","signalValueToStatus":[]},{"id":1008569567830935,"description":"","name":"温度","equipmentId":1007569567830928,"parentId":null,"samplerUnitId":null,"channelType":2,"category":1,"channelNo":2,"unit":"℃","upperLimit":1,"lowerLimit":null,"currentValue":null,"showPrecision":null,"updateTime":null,"expression":"","storedPeriod":30,"communicationState":null,"visible":true,"displayIndex":1,"code":"d_temp","signalValueToStatus":[]}]
         */

        private long id;
        private String name;
        private int category;
        private long houseId;
        private List<SignalsBean> signals;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public long getHouseId() {
            return houseId;
        }

        public void setHouseId(long houseId) {
            this.houseId = houseId;
        }

        public List<SignalsBean> getSignals() {
            return signals;
        }

        public void setSignals(List<SignalsBean> signals) {
            this.signals = signals;
        }

        public static class SignalsBean {
            /**
             * id : 1009569567830935
             * description :
             * name : 湿度
             * equipmentId : 1007569567830928
             * parentId : null
             * samplerUnitId : null
             * channelType : 2
             * category : 1
             * channelNo : 1
             * unit : %HR
             * upperLimit : 1.0
             * lowerLimit : null
             * currentValue : null
             * showPrecision : null
             * updateTime : null
             * expression :
             * storedPeriod : 30
             * communicationState : null
             * visible : true
             * displayIndex : 2
             * code : d_humi
             * signalValueToStatus : []
             */

            private long id;
            private String description;
            private String name;
            private long equipmentId;
            private Object parentId;
            private Object samplerUnitId;
            private int channelType;
            private int category;
            private int channelNo;
            private String unit;
            private double upperLimit;
            private Object lowerLimit;
            private Object currentValue;
            private Object showPrecision;
            private Object updateTime;
            private String expression;
            private int storedPeriod;
            private Object communicationState;
            private boolean visible;
            private int displayIndex;
            private String code;
            private List<?> signalValueToStatus;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public long getEquipmentId() {
                return equipmentId;
            }

            public void setEquipmentId(long equipmentId) {
                this.equipmentId = equipmentId;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public Object getSamplerUnitId() {
                return samplerUnitId;
            }

            public void setSamplerUnitId(Object samplerUnitId) {
                this.samplerUnitId = samplerUnitId;
            }

            public int getChannelType() {
                return channelType;
            }

            public void setChannelType(int channelType) {
                this.channelType = channelType;
            }

            public int getCategory() {
                return category;
            }

            public void setCategory(int category) {
                this.category = category;
            }

            public int getChannelNo() {
                return channelNo;
            }

            public void setChannelNo(int channelNo) {
                this.channelNo = channelNo;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public double getUpperLimit() {
                return upperLimit;
            }

            public void setUpperLimit(double upperLimit) {
                this.upperLimit = upperLimit;
            }

            public Object getLowerLimit() {
                return lowerLimit;
            }

            public void setLowerLimit(Object lowerLimit) {
                this.lowerLimit = lowerLimit;
            }

            public Object getCurrentValue() {
                return currentValue;
            }

            public void setCurrentValue(Object currentValue) {
                this.currentValue = currentValue;
            }

            public Object getShowPrecision() {
                return showPrecision;
            }

            public void setShowPrecision(Object showPrecision) {
                this.showPrecision = showPrecision;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public String getExpression() {
                return expression;
            }

            public void setExpression(String expression) {
                this.expression = expression;
            }

            public int getStoredPeriod() {
                return storedPeriod;
            }

            public void setStoredPeriod(int storedPeriod) {
                this.storedPeriod = storedPeriod;
            }

            public Object getCommunicationState() {
                return communicationState;
            }

            public void setCommunicationState(Object communicationState) {
                this.communicationState = communicationState;
            }

            public boolean isVisible() {
                return visible;
            }

            public void setVisible(boolean visible) {
                this.visible = visible;
            }

            public int getDisplayIndex() {
                return displayIndex;
            }

            public void setDisplayIndex(int displayIndex) {
                this.displayIndex = displayIndex;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public List<?> getSignalValueToStatus() {
                return signalValueToStatus;
            }

            public void setSignalValueToStatus(List<?> signalValueToStatus) {
                this.signalValueToStatus = signalValueToStatus;
            }
        }
    }
}
