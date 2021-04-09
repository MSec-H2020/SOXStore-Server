package com.drgnman.management_server_gradle.dto;

public class DataDTO {
    private String topic_id;           // トピック名(Topicのtopic_idと=になる)
    private String transducer_id;      // データ名(SOXFire上でいう各Transducer名, ない場合はtopic_id=data_idとすること)
    private String value;              // データ本体
    private String timestamp;          // publishされた時間

    public String getTopic_id() {
        return topic_id;
    }
    public void setTopic_id(String topic_id) { this.topic_id = topic_id; }
    public String getTransducer_id() {
        return transducer_id;
    }
    public void setTransducer_id(String transducer_id) {
        this.transducer_id = transducer_id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
