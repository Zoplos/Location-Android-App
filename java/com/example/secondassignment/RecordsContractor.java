package com.example.secondassignment;

public class RecordsContractor {
    private String id;
    private String unix_timestamp;
    private String longitude;
    private String latitude;

    public RecordsContractor(String id, String unix_timestamp, String longitude, String latitude) {
        this.id= id;
        this.unix_timestamp = unix_timestamp;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getUnix_timestamp() {
        return unix_timestamp;
    }

    public void setUnix_timestamp(String unix_timestamp) {
        this.unix_timestamp = unix_timestamp;
    }
}
