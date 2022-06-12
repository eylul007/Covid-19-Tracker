package com.yicit;

public class deneme {
    private String id;
    private Double distance;

    public deneme(String id, double distance) {
        this.id = id;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
