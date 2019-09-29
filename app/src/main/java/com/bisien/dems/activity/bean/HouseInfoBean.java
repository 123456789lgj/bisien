package com.bisien.dems.activity.bean;

public class HouseInfoBean {
    private long housesId;
    private String houseName;
    public HouseInfoBean(long housesId,String houseName){
        this.housesId = housesId;
        this.houseName = houseName;
    }
    public long getHousesId() {
        return housesId;
    }

    public void setHousesId(long housesId) {
        this.housesId = housesId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
