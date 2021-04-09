package com.drgnman.management_server_gradle.Repository;

import com.drgnman.management_server_gradle.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    // region 単純検索
    // 単純検索(Topic ID検索)
    @Query(value = "SELECT * FROM topic WHERE TOPIC_ID = topic:topic_id", nativeQuery = true)
    Topic TopicSearchByTopicId(@Param("topic_id") String topic_id);
    // endregion

    // region 現在地周辺のデータを検索
    // 現在地の周辺イベントを取得するため、経過予測時間は適用しない
    @Query(value = "SELECT TOPIC_ID, CATEGORY, LOCATION_LAT, LOCATION_LNG," +
            " (" +
            "6371 * acos(" +
            "cos(radians(:lat))" +
            "* cos(radians(LOCATION_LAT))" +
            "* cos(radians(LOCATION_LNG) - radians(:lng))" +
            "+ sin(radians(:lat))" +
            "* sin(radians(LOCATION_LAT))" +
            ")" +
            ") AS TEMP_DISTANCE" +
            " FROM topic HAVING TEMP_DISTANCE <= :distance", nativeQuery = true) // SQL
    List<Object> TopicSearchForDistance(
              @Param("lat") double lat,
              @Param("lng") double lng,
              @Param("distance") double distance
    );
    // endregion

    // region 目的地周辺のデータを検索
    // 現在地の座標もしくは目的地の座標と経過予測時間が大きなものを取得するクエリ(検索範囲を追加したversion.)
    @Query(value = "SELECT TOPIC_ID, (" +
            "6371 * acos(" +
            "cos(radians(:dest_lat))" +
            "* cos(radians(LOCATION_LAT))" +
            "* cos(radians(LOCATION_LNG) - radians(:dest_lng))" +
            "+ sin(radians(:dest_lat))" +
            "* sin(radians(LOCATION_LAT))" +
            ")" +
            ") AS TEMP_DISTANCE " +
            " FROM topic WHERE :expect_time < LIFETIME HAVING TEMP_DISTANCE <= :distance", nativeQuery = true) // SQL
    List<Object> TopicSearchForDestinationAndRange(
            @Param("dest_lat") double dest_lat,
            @Param("dest_lng") double dest_lng,
            @Param("distance") double distance,
            @Param("expect_time") int expect_time
    );
    // endregion

    // region 複合検索1(使わないかも)
    // 現在地の座標もしくは目的地の座標と経過予測時間が大きなものを取得するクエリ
    @Query(value = "SELECT TOPIC_ID FROM topic WHERE (LOCATION_LAT = :current_lat AND LOCATION_LNG = :current_lng) " +
            "OR (LOCATION_LAT = :dest_lat AND LOCATION_LNG = :dest_lng AND LIFETIME <= :expect_time)", nativeQuery = true)
    List<Object> TopicSearchForComplexLocation(
            @Param("current_lat") double current_lat,
            @Param("current_lng") double current_lng,
            @Param("dest_lat") double dest_lat,
            @Param("dest_lng") double dest_lng,
            @Param("expect_time") int expect_time
    );
    // endregion
}

