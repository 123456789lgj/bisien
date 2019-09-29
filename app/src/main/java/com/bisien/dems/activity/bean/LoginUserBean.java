package com.bisien.dems.activity.bean;

public class LoginUserBean {

    /**
     * code : -1
     * message : 用户名或密码错误！
     * data : {"id":-1,"description":"用户名或密码错误！","name":null,"role":null,"password":null,"telephone":null,"email":null,"userQQ":null,"address":null,"expiredTime":null,"status":null,"parentId":null,"departmentId":null}
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
         * id : -1
         * description : 用户名或密码错误！
         * name : null
         * role : null
         * password : null
         * telephone : null
         * email : null
         * userQQ : null
         * address : null
         * expiredTime : null
         * status : null
         * parentId : null
         * departmentId : null
         */

        private int id;
        private String description;
        private Object name;
        private Object role;
        private Object password;
        private Object telephone;
        private Object email;
        private Object userQQ;
        private Object address;
        private Object expiredTime;
        private Object status;
        private Object parentId;
        private Object departmentId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getRole() {
            return role;
        }

        public void setRole(Object role) {
            this.role = role;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public Object getTelephone() {
            return telephone;
        }

        public void setTelephone(Object telephone) {
            this.telephone = telephone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getUserQQ() {
            return userQQ;
        }

        public void setUserQQ(Object userQQ) {
            this.userQQ = userQQ;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getExpiredTime() {
            return expiredTime;
        }

        public void setExpiredTime(Object expiredTime) {
            this.expiredTime = expiredTime;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public Object getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(Object departmentId) {
            this.departmentId = departmentId;
        }
    }
}
