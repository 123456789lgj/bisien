package com.bisien.dems.activity.bean;

import java.util.List;

public class EquipmentTypeBean {

    /**
     * code : 0
     * message :
     * data : [{"id":1,"name":"空调","parentId":null},{"id":2,"name":"消防","parentId":null},{"id":3,"name":"配电","parentId":null},{"id":4,"name":"温湿感应器","parentId":null},{"id":5,"name":"水浸感应器","parentId":null},{"id":6,"name":"警铃","parentId":null},{"id":7,"name":"UPS","parentId":null},{"id":8,"name":"机柜","parentId":null},{"id":9,"name":"摄像头","parentId":null},{"id":10,"name":"虚拟设备","parentId":null}]
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
         * id : 1
         * name : 空调
         * parentId : null
         */

        private int id;
        private String name;
        private Object parentId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }
    }
}
