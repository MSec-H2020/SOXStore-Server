package com.drgnman.management_server_gradle.service;

import com.drgnman.management_server_gradle.Entity.Data;
import com.drgnman.management_server_gradle.Entity.Topic;
import com.drgnman.management_server_gradle.Repository.DataRepository;
import com.drgnman.management_server_gradle.Repository.TopicRepository;
import com.drgnman.management_server_gradle.dto.DataDTO;
import com.drgnman.management_server_gradle.dto.SearchDTO;
import com.drgnman.management_server_gradle.dto.TopicDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    DataRepository dataRepository;

    final static ModelMapper modelMapper = new ModelMapper();

    // 単純なキーワード検索機能
    public TopicDTO simpleSearch (TopicDTO searchObj) {
        try {
            // トピックIDが一致するレコードを一件取得
            Topic topic = topicRepository.TopicSearchByTopicId(searchObj.getTopic_id());
            TopicDTO result = modelMapper.map(topic, TopicDTO.class);
            return result;
        } catch (Exception e) {
            // 検索がコケた場合にはとりあえず何か返しておく
            System.out.println("Error: " + e);
            return searchObj;
        }
    }

    // 距離による検索機能
    public List<TopicDTO> distanceSearch (TopicDTO searchObj) {
        try {
            // 位置情報と検索範囲ベースでトピック検索をするクエリの発行
            List<Object> resultList = topicRepository.TopicSearchForDistance(
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
                result.setTopic_id((String) list.get(0));        // トピックID
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

    // 複雑検索機能
    public List<DataDTO> complexSearch (SearchDTO searchObj) {
        try {
            // トピックIDが一致するレコードを全件取得
            // そのトピックごとに最新のDataRecordを取得

            List<Object> resultList = topicRepository.TopicSearchForComplexLocation(
                    searchObj.getLocation_lat(),
                    searchObj.getLocation_lng(),
                    searchObj.getDestination_lat(),
                    searchObj.getDestination_lng(),
                    searchObj.getExpect_time());

            // DataObjectを格納する用
            List<DataDTO>  dataList = new ArrayList<DataDTO>();

            // 取得したTOPICから該当するするデータを取得する
            for (int i=0; i < resultList.size(); i++) {
                String topic_id = (String ) Array.get(resultList.get(i), 0);     // トピックIDの取得
                Data data = dataRepository.DataSearchByTopicIdLimit1(topic_id);
                DataDTO dataDTO = modelMapper.map(data, DataDTO.class);
                dataList.add(dataDTO);
            }

            return dataList;

        } catch (Exception e) {
            // 検索がコケた場合には空のリストを返しておく
            System.out.println("Error: " + e);
            List<DataDTO>  dataList = new ArrayList<DataDTO>();
            return dataList;
        }
    }
}
