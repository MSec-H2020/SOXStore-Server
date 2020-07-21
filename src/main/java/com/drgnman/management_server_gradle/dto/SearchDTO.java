package com.drgnman.management_server_gradle.dto;

import java.util.List;

public class SearchDTO {
    private String topic_id;                   // トピックID
    private String category;                   // カテゴリ名
    private double location_lat;               // 現在地の位置情報(緯度)
    private double location_lng;               // 現在地の位置情報(経度)
    private List<Double> destination_lat;      // 目的地の位置情報(緯度)
    private List<Double> destination_lng;      // 目的地の位置情報(経度)
    private List<Integer> expect_time;         // データの生存時間(有効時間)
    private double speed;                      // 車の速度
    private double range;                      // 検索(応答)範囲

    public String getTopic_id() {
        return topic_id;
    }
    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
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

    public void setDestination_lat(List<Double> destination_lat) {
        this.destination_lat = destination_lat;
    }
    public List<Double> getDestination_lat() {
        return destination_lat;
    }

    public List<Double> getDestination_lng() {
        return destination_lng;
    }
    public void setDestination_lng(List<Double> destination_lng) {
        this.destination_lng = destination_lng;
    }

    public void setExpect_time(List<Integer> expect_time) {
        this.expect_time = expect_time;
    }
    public List<Integer> getExpect_time() {
        return expect_time;
    }

    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getRange() { return range; }
    public void setRange(double range) { this.range = range; }
}
