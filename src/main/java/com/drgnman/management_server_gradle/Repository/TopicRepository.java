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

    @Query(value = "SELECT * FROM TOPIC WHERE (LOCATION_LAT = :current_lat AND LOCATION_LNG = :current_lng) " +
            "OR (LOCATION_LAT = :dest_lat AND LOCATION_LNG = :dest_lng AND LIFETIME <= :expect_time)", nativeQuery = true)
    List<Object> TopicSearchForComplexLocation(
            @Param("current_lat") double current_lat,
            @Param("current_lng") double current_lng,
            @Param("dest_lat") double dest_lat,
            @Param("dest_lng") double dest_lng,
            @Param("expect_time") int expect_time
    );
    // うまく動かないため、saveメソッドで代用
    // // LifeTimeの更新(Topic ID指定による)
    // @Query(value = "UPDATE TOPIC SET LIFETIME = :lifetime  WHERE TOPIC_ID = :topic_id", nativeQuery = true)
    // Topic TopicUpdateLifetimeByTopicId(@Param("topic_id") String topic_id, @Param("lifetime") int lifetime);

    // // Location, LifeTimeの更新(Topic ID指定による)
    // @Query(value = "UPDATE TOPIC SET LOCATION_LAT = :location_lat, LOCATION_LNG = :location_lng, COVER_DISTANCE = :cover_distance, LIFETIME = :lifetime  WHERE TOPIC_ID = :topic_id", nativeQuery = true)
    // Topic TopicUpdateByTopicId(@Param("topic_id") String topic_id,
    //                            @Param("location_lat") double location_lat,
    //                            @Param("location_lng") double location_lng,
    //                            @Param("cover_distance") double cover_distance,
    //                            @Param("lifetime") int lifetime);
}

