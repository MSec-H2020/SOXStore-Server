package com.drgnman.management_server_gradle.Repository;

import com.drgnman.management_server_gradle.Entity.Data;
import com.drgnman.management_server_gradle.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
    @Query(value = "SELECT * FROM TOPIC WHERE TOPIC_ID = :topic_id", nativeQuery = true)
    List<Topic> DataSearchByTopicId(@Param("topic_id") String topic_id);
}
