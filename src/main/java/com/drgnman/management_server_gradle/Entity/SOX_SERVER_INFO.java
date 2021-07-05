package com.drgnman.management_server_gradle.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class SOX_SERVER_INFO {
    @Id
    private String url;
    private String user_name;
    private String password;

    // @OneToMany(mappedBy="soxServerInfo", cascade=CascadeType.ALL)
    // private List<Topic> topics;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // public List<Topic> getTopics() {
    //     return topics;
    // }

    // public void setTopics(List<Topic> topics) {
    //     this.topics = topics;
    // }
}