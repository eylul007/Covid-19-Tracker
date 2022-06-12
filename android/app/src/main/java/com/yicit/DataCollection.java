package com.yicit;

public class DataCollection {

    private String BeaconId;
    private Double distance;

    public DataCollection(String beaconId, Double distance) {
        BeaconId = beaconId;
        this.distance = distance;
    }

    public String getBeaconId() {
        return BeaconId;
    }

    public void setBeaconId(String beaconId) {
        BeaconId = beaconId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
