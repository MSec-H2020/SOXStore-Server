package com.drgnman.management_server_gradle.dto;

public class SearchDTO {
    private String topic_id;         // トピックID
    private String category;         // カテゴリ名
    private double location_lat;     // 現在地の位置情報(緯度)
    private double location_lng;     // 現在地の位置情報(経度)
    private double destination_lat;  // 目的地の位置情報(経度)
    private double destination_lng;  // 目的地の位置情報(経度)
    private int expect_time;         // データの生存時間(有効時間)
    private double range;            // 検索(応答)範囲

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

    public double getDestination_lat() { return destination_lat; }
    public void setDestination_lat(double destination_lat) { this.destination_lat = destination_lat; }

    public double getDestination_lng() { return destination_lng; }
    public void setDestination_lng(double destination_lng) { this.destination_lng = destination_lng; }

    public int getExpect_time() { return expect_time; }
    public void setExpect_time(int expect_time) { this.expect_time = expect_time; }

    public double getRange() { return range; }
    public void setRange(double range) { this.range = range; }
}
