package com.drgnman.management_server_gradle.Repository;

import com.drgnman.management_server_gradle.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    @Query(value = "SELECT TOPIC_ID, CATEGORY, LOCATION_LAT, LOCATION_LNG," +
            " (" +
            "6371 * acos(" +
            "cos(radians(35.6804067))" +
            "* cos(radians(LOCATION_LAT))" +
            "* cos(radians(LOCATION_LNG) - radians(139.7550152))" +
            "+ sin(radians(35.6804067))" +
            "* sin(radians(LOCATION_LAT))" +
            ")" +
            ") AS TEMP_DISTANCE" +
            " FROM TOPIC HAVING TEMP_DISTANCE <= 3.0", nativeQuery = true) // SQL
    List<Object> TopicSearchForDistance(
              @Param("lat") double lat,
              @Param("lng") double lng,
              @Param("distance") double distance
    );

    // 単純検索(Topic ID検索)
    @Query(value = "SELECT * FROM TOPIC WHERE TOPIC_ID = :topic_id", nativeQuery = true)
    Topic TopicSearchByTopicId(@Param("topic_id") String topic_id);
}

