package com.bisien.dems.activity.bean;

public class LocationBean {
    /**
     * locationId : 123456789
     * locationName : 草堂加速器
     */

    private String locationId;
    private String locationName;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "LocationBean{" +
                "locationId='" + locationId + '\'' +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
