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
    @Query(value = "SELECT * FROM DATA WHERE TOPIC_ID = :topic_id", nativeQuery = true)
    List<Data> DataSearchByTopicId(@Param("topic_id") String topic_id);

    // TOPIC_IDﾆ紐づくデータの中で最新の1件の取得
    @Query(value = "SELECT * FROM DATA WHERE TOPIC_ID = :topic_id ORDER BY ID DESC LIMIT 1", nativeQuery = true)
    Data DataSearchByTopicIdLimit1(@Param("topic_id") String topic_id);

    // TOPIC_IDに紐づく全レコードのタイムスタンプデータだけ全取得
    @Query(value = "SELECT ID, UNIX_TIMESTAMP(PUB_TIMESTAMP), UNIX_TIMESTAMP(TIMESTAMP) FROM DATA WHERE TOPIC_ID = :topic_id ORDER BY ID DESC", nativeQuery = true)
    List<Object> TimestampOfSearchByTopicId(@Param("topic_id") String topic_id);
}
