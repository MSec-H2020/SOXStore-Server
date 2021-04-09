package com.drgnman.management_server_gradle.dto;

import jp.ac.keio.sfc.ht.sox.protocol.Transducer;

import java.util.List;

public class DeviceDTO {
    private String topic_id;            // トピックID
    private String category;            // カテゴリ名

    private List<Transducer> transducers;   // transducer

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

    public List<Transducer> getTransducers() {
        return transducers;
    }

    public void setTransducers(List<Transducer> transducers) {
        this.transducers = transducers;
    }

}
