package com.drgnman.management_server_gradle.service;

import com.drgnman.management_server_gradle.Entity.Topic;
import com.drgnman.management_server_gradle.Repository.TopicRepository;
import com.drgnman.management_server_gradle.dto.TopicDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    TopicRepository repository;

    final static ModelMapper modelMapper = new ModelMapper();

    // 単純なキーワード検索機能
    public TopicDTO simpleSearch (TopicDTO searchObj) {
        try {
            // トピックIDが一致するレコードを一件取得
            Topic topic = repository.TopicSearchByTopicId(searchObj.getTopic_id());
            TopicDTO result = modelMapper.map(topic, TopicDTO.class);
            return result;
        } catch (Exception e) {
            // 検索がコケた場合にはとりあえず何か返しておく
            System.out.println("Error: " + e);
            return searchObj;
        }
    }

    // 複雑検索機能
    public List<TopicDTO> distanceSearch (TopicDTO searchObj) {
        try {
            // 位置情報と検索範囲ベースでトピック検索をするクエリの発行
            List<Object> resultList = repository.TopicSearchForDistance(
                    searchObj.getLocation_lat(), searchObj.getLocation_lng(), searchObj.getCover_distance());
            // return用のオブジェクトの用意
            List<TopicDTO> topicList = new ArrayList<TopicDTO>();

            // Entity結果をDTOオブジェクトに格納 データ加工等の処理はここで実施
            for (int i=0; i < resultList.size(); i++) {
                // Object -> DTOの変換処理
                TopicDTO result = new TopicDTO();
                int length = Array.getLength(resultList.get(i));
                List list = new ArrayList();
                for (int j=0; j<length; j++) {
                    list.add(Array.get(resultList.get(i), j));
                }
                result.setTopic_id((String) list.get(0));        // トピックNo.
                result.setCategory((String) list.get(1));         // カテゴリ名
                result.setLocation_lat((Double) list.get(2));     // 位置情報(緯度)
                result.setLocation_lng((Double) list.get(3));     // 位置情報(経度)
                topicList.add(result);
            }
            return topicList;

        } catch (Exception e) {
            // 検索がコケた場合にはとりあえず何か返しておく
            System.out.println("Error : " + e);
            List<TopicDTO> result = null;
            result.add(searchObj);
            return  result;
        }
    }
}
