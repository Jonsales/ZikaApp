package com.example.saemi.hackadengue.services;

/**
 * Created by alalvespereira on 04/05/15.
 */
public class Local {

    String id;
    String latitude;
    String longitude;
    String local;
    String resource_uri;

    public Local() {
    }

    public Local(String id, String latitude, String longitude, String local, String resource_uri) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.local = local;
        this.resource_uri = resource_uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getResource_uri() {
        return resource_uri;
    }

    public void setResource_uri(String resource_uri) {
        this.resource_uri = resource_uri;
    }
}