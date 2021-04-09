package com.drgnman.management_server_gradle.Repository;

import com.drgnman.management_server_gradle.Entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<Data, Integer> {
    // TOPIC_IDﾆ紐づく全レコードの取得
    @Query(value = "SELECT * FROM data WHERE TOPIC_ID = :topic_id", nativeQuery = true)
    List<Data> DataSearchByTopicId(@Param("topic_id") String topic_id);

    // TOPIC_IDﾆ紐づくデータの中で最新の1件の取得
    @Query(value = "SELECT * FROM data WHERE TOPIC_ID = :topic_id ORDER BY TOPIC_ID DESC LIMIT 1", nativeQuery = true)
    Data DataSearchByTopicIdLimit1(@Param("topic_id") String topic_id);

    // TOPIC_IDﾆ紐付く最新レコードのtimestampを検索する
    @Query(value = "SELECT * FROM data WHERE TOPIC_ID = :topic_id ORDER BY TOPIC_ID DESC LIMIT 1", nativeQuery = true)
    Data TimestampSearchByTopicIdLimit1(@Param("topic_id") String topic_id);

    // TOPIC_IDとTimestampに紐付く最新レコードを検索する
    @Query(value = "SELECT * FROM data WHERE TOPIC_ID = :topic_id AND PUB_TIMESTAMP = :pub_timestamp ORDER BY TOPIC_ID DESC", nativeQuery = true)
    List<Data> DataSearchByTopicIDAndTimeStamp(@Param("topic_id") String topic_id,
                                               @Param("pub_timestamp") String pub_timestamp);

    // TOPIC_IDとTIMESTAMPの時間でデータの検索を行う(From-To)
    @Query(value = "SELECT * FROM data WHERE TOPIC_ID = :topic_id AND UNIX_TIMESTAMP(PUB_TIMESTAMP) >= :time_from AND UNIX_TIMESTAMP(PUB_TIMESTAMP) <= :time_to", nativeQuery=true)
    List<Data> TimestampSearchByTopicIdAndTimeFromTo(@Param("topic_id") String topic_id,
                                               @Param("time_from") double time_from,
                                               @Param("time_to") double time_to);

    // TOPIC_IDとTIMESTAMPの時間でデータの検索を行う(Only From)
    @Query(value = "SELECT * FROM data WHERE TOPIC_ID = :topic_id AND UNIX_TIMESTAMP(PUB_TIMESTAMP) >= :time_from", nativeQuery=true)
    List<Data> TimestampSearchByTopicIdAndTimeFrom(@Param("topic_id") String topic_id,
                                                        @Param("time_from") double time_from);

    // TOPIC_IDに紐づく全レコードのタイムスタンプデータだけ全取得
    @Query(value = "SELECT ID, UNIX_TIMESTAMP(PUB_, UNIX_TIMESTAMP(TIMESTAMP) FROM data WHERE TOPIC_ID = :topic_id ORDER BY ID DESC", nativeQuery = true)
    List<Object> TimestampOfSearchByTopicId(@Param("topic_id") String topic_id);
}
