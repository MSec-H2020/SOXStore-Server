package com.drgnman.management_server_gradle.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                    // Data.No (Auto Incrementする)
    private String topic_id;           // トピック名(Topicのtopic_idと=になる)
    private double location_lat;       // 位置情報(緯度)
    private double location_lng;       // 位置情報(経度)
    private String value;              // データ本体
    private String pub_datetime;       // publishされた時間
    private String datetime;           // データが追加された時間

    public int getId() {
        return id;
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


    public String getPub_datetime() {
        return pub_datetime;
    }
    public void setPub_datetime(String pub_datetime) {
        this.pub_datetime = pub_datetime;
    }

    public String getDatetime() {
        return datetime;
    }
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

}
