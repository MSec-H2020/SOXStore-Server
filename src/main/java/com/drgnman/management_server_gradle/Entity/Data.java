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
    private String data_id;            // データ名(SOXFire上でいう各Transducer名, ない場合はtopic_id=data_idとすること)
    private double location_lat;       // 位置情報(緯度)
    private double location_lng;       // 位置情報(経度)
    private double cover_distance;      // 検索範囲
    private String value;              // データ本体
    private String pub_timestamp;      // publishされた時間
    private String timestamp;          // データが追加された時間

    public int getId() {
        return id;
    }

    public String getTopic_id() {
        return topic_id;
    }
    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getData_id() {
        return data_id;
    }
    public void setData_id(String data_id) {
        this.data_id = data_id;
    }

    public double getCover_distance() { return cover_distance; }
    public void setCover_distance(double cover_distance) { this.cover_distance = cover_distance; }

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

    public String getPub_timestamp() {
        return pub_timestamp;
    }
    public void setPub_timestamp(String pub_timestamp) {
        this.pub_timestamp = pub_timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
