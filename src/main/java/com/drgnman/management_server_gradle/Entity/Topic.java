package com.drgnman.management_server_gradle.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@IdClass(CompositeKeyTopic.class)
public class Topic {
    @Id
    private String topic_id;            // トピックID(名称)
    private String category;            // カテゴリ名
    @Id
    private String server_name;

    // @ManyToOne
    // @JoinColumn(name="url")
    // private SOX_SERVER_INFO soxServerInfo;


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

    public String getServer_name() {
        return server_name;
    }
    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    // public SOX_SERVER_INFO getSoxServerInfo() {
    //     return soxServerInfo;
    // }

    // public void setSoxServerInfo(SOX_SERVER_INFO soxServerInfo) {
    //     this.soxServerInfo = soxServerInfo;
    // }
}
