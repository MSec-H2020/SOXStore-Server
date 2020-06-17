package com.drgnman.management_server_gradle.dto;

public class DataDTO {
    private int id;                    // Data.No (Auto Incrementする)
    private String topic_id;           // トピック名(Topicのtopic_idと=になる)
    private double location_lat;       // 位置情報(緯度)
    private double location_lng;       // 位置情報(経度)
    private String value;              // データ本体
    private int pub_timestamp;      // publishされた時間
    private int timestamp;          // データが追加された時間

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTopic_id() {
        return topic_id;
    }
    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public double getLocation_lat() {
        return location_lat;
    }
    public void setLocation_lat(double location_lat) {
        this.location_lat = location_lat;
    }

    public double getLocation_lng() {
        return location_lng;
    }
    public void setLocation_lng(double location_lng) {
        this.location_lng = location_lng;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public int getPub_timestamp() {
        return pub_timestamp;
    }
    public void setPub_timestamp(int pub_timestamp) {
        this.pub_timestamp = pub_timestamp;
    }

    public int getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
