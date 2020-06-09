package com.drgnman.management_server_gradle.dto;

public class TopicDTO {
    // トピックNo.
    private int topic_id;
    // トピック名
    private String topic_name;
    // カテゴリ名
    private String category;
    // 位置情報(緯度)
    private double location_lat;
    // 位置情報(経度)
    private double location_lng;
    // 住所(県名)
    private String address1;
    // 住所(市名)
    private String address2;
    // データの生存時間(有効時間)
    private int lifetime;
    // 検索(応答)範囲
    private double cover_distance;

    public int getTopic_id() {
        return topic_id;
    }
    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }


    public String getTopic_name() {
        return topic_name;
    }
    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
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

    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public int getLifetime() {
        return lifetime;
    }
    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public double getCover_distance() {
        return cover_distance;
    }
    public void setCover_distance(double cover_distance) {
        this.cover_distance = cover_distance;
    }

}
