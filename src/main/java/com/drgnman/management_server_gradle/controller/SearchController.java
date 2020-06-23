package com.drgnman.management_server_gradle.controller;

import com.drgnman.management_server_gradle.dto.DataDTO;
import com.drgnman.management_server_gradle.dto.SearchDTO;
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
    public String search(
            @RequestParam(value = "topicId", defaultValue = "") String topicId,
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
        if (!("".equals(topicId))) searchObj.setTopic_id(topicId);
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

    @RequestMapping("/search/complex")
    @ResponseBody
    public String complexSearch(
            // @RequestParam(value = "topicId", defaultValue = "") String topicId,
            // @RequestParam(value = "category", defaultValue = "") String category,
            @RequestParam(value = "lat", defaultValue = "") String lat,
            @RequestParam(value = "lng", defaultValue = "") String lng,
            @RequestParam(value = "dest_lat", defaultValue = "") String dest_lat,
            @RequestParam(value = "dest_lng", defaultValue = "") String dest_lng,
            @RequestParam(value = "expect_time", defaultValue = "") String expect_time) throws JsonProcessingException {
            // @RequestParam(value = "distance", defaultValue = "") String distance,
            // @RequestParam(value = "mode", defaultValue ="simple") String mode) throws JsonProcessingException {

        // TOPIC DTOオブジェクトの生成
        SearchDTO searchObj = new SearchDTO();

        // RequestParamの設定
        // if (!("".equals(topicId))) searchObj.setTopic_id(topicId);
        // searchObj.setCategory(category);
        if (!("".equals(lat))) searchObj.setLocation_lat(Double.parseDouble(lat));
        if (!("".equals(lng))) searchObj.setLocation_lng(Double.parseDouble(lng));
        if (!("".equals(dest_lat))) searchObj.setDestination_lat(Double.parseDouble(dest_lat));
        if (!("".equals(dest_lng))) searchObj.setDestination_lat(Double.parseDouble(dest_lng));
        if (!("".equals(expect_time))) searchObj.setExpect_time(Integer.parseInt(expect_time));
        // if (!("".equals((distance)))) searchObj.setCover_distance(Double.parseDouble(distance));

        ObjectMapper mapper = new ObjectMapper();

        // 戻り値格納用の変数
        List<DataDTO> resultList;
        String json;

        resultList = searchService.complexSearch(searchObj);
        Map<String, List<DataDTO>> topicMap = new HashMap<>();
        topicMap.put("testData", resultList);
        json = mapper.writeValueAsString(topicMap);

        return json;
    }
}
