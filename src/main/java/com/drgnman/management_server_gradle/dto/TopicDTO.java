package com.drgnman.management_server_gradle.dto;

public class TopicDTO {
    private String topic_id;            // トピックID
    private String category;         // カテゴリ名

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

}
