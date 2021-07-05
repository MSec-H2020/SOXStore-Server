package com.drgnman.management_server_gradle.Entity;

import javax.persistence.Id;
import java.io.Serializable;

public class CompositeKeyTopic implements Serializable {
    private String topic_id;            // トピックID(名称)
    private String server_name;
}
