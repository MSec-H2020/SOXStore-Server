package com.drgnman.management_server_gradle.controller;

import com.drgnman.management_server_gradle.dto.TopicDTO;
import com.drgnman.management_server_gradle.service.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    SearchService searchService;
    @RequestMapping("/search")
    @ResponseBody

    public String search(@RequestParam(value = "topicId", defaultValue = "") String topicId,
                           @RequestParam(value = "topicName", defaultValue = "")String topicName,
                           @RequestParam(value = "category", defaultValue = "") String category,
                           @RequestParam(value = "lat", defaultValue = "") String lat,
                           @RequestParam(value = "lng", defaultValue = "") String lng,
                           @RequestParam(value = "distance", defaultValue = "") String distance,
                           @RequestParam(value = "mode", defaultValue ="simple") String mode) throws JsonProcessingException {
        // TOPIC DTOオブジェクトの生成
        TopicDTO searchObj = new TopicDTO();

        // 戻り値格納用の変数
        // TopicDTO result = null;
        TopicDTO result = null;
        List<TopicDTO> resultList;
        String json;

        // RequestParamの設定
        if (!("".equals(topicId))) searchObj.setTopic_id(Integer.parseInt(topicId));
        searchObj.setTopic_name(topicName);
        searchObj.setCategory(category);
        if (!("".equals(lat))) searchObj.setLocation_lat(Double.parseDouble(lat));
        if (!("".equals(lng))) searchObj.setLocation_lng(Double.parseDouble(lng));
        if (!("".equals((distance)))) searchObj.setCover_distance(Double.parseDouble(distance));

        ObjectMapper mapper = new ObjectMapper();
        // mode="simple"の場合, simpleSearch mode="discover"の場合、distanceSearch
        if ("simple".equals(mode)) {
            result = searchService.simpleSearch(searchObj);
            Map<String, TopicDTO> topicMap = new HashMap<>();
            topicMap.put("testData", result);
            json = mapper.writeValueAsString(topicMap);
        } else {
            resultList =  searchService.distanceSearch(searchObj);
            Map<String, List<TopicDTO>> topicMap = new HashMap<>();
            topicMap.put("testData", resultList);
            json = mapper.writeValueAsString(topicMap);
        }

        return json;
        // return result;
    }
}
