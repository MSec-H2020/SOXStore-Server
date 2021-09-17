package com.drgnman.management_server_gradle.controller;

import com.drgnman.management_server_gradle.Entity.Data;
import com.drgnman.management_server_gradle.Repository.DataRepository;
import com.drgnman.management_server_gradle.dto.DataDTO;
import com.drgnman.management_server_gradle.dto.DeviceDTO;
import com.drgnman.management_server_gradle.dto.PublishDTO;
import com.drgnman.management_server_gradle.dto.SearchDTO;
import com.drgnman.management_server_gradle.service.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.ac.keio.sfc.ht.sox.protocol.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SearchController {
    @Autowired
    SearchService searchService;
    // region SmileCityReport -> SOX用の実装
    @RequestMapping(value="/publish", method = RequestMethod.POST)
    @ResponseBody
    // region Searchメソッド
    public String publish(
            // @RequestParam(value = "server_address", defaultValue = "") String server_address,
            // @RequestParam(value = "sox_user", defaultValue = "") String sox_user,
            // @RequestParam(value = "sox_pass", defaultValue = "") String sox_pass,
            // @RequestParam(value = "theme", defaultValue = "") String theme,
            // @RequestParam(value = "username", defaultValue = "") String username,
            // @RequestParam(value = "latitude", defaultValue = "") String latitude,
            // @RequestParam(value = "longitude", defaultValue = "") String longitude,
            // @RequestParam(value = "smiledegree", defaultValue = "") String smiledegree,
            // @RequestParam(value = "imagephoto", defaultValue = "") String imagephoto
            @RequestBody PublishDTO publishObj
            ) throws JsonProcessingException {
        // TOPIC DTOオブジェクトの生成

        // 戻り値格納用の変数
        // soxへの接続情報とデバイス名を仮で直書き
        // PublishDTO publishObj = new PublishDTO();
        publishObj.setSox_address("sox.jn.sfc.keio.ac.jp");
        publishObj.setSox_user("SmileCityReportUser");
        publishObj.setSox_pass("!msec2021");
        publishObj.setTheme("SmileCityReportDevice1");
        // publishObj.setUser_name("keioUser1");
        // publishObj.setPublic_key("0x25a09c97819e842c72220520c58f46e01779f12a8d401f7d1fad7a165ba1f008");
        String result_msg = searchService.publish(publishObj);

        return result_msg;
    }
    // endregion
    // endregion

    @RequestMapping("/search")
    @ResponseBody
    // region Searchメソッド
    public String search(
            @RequestParam(value = "server_address", defaultValue = "") String server_address,
            @RequestParam(value = "sox_user", defaultValue = "")  String sox_user,
            @RequestParam(value = "sox_pass", defaultValue = "")  String sox_pass,
            @RequestParam(value = "keyword", defaultValue = "")   String keyword,
            @RequestParam(value = "node_name", defaultValue = "") List<String> node_name) throws JsonProcessingException {
            // @RequestParam(value = "db_address", defaultValue = "") String db_address,
            // @RequestParam(value = "db_user", defaultValue = "") String db_user,
            // @RequestParam(value = "db_pass", defaultValue = "") String db_pass) throws JsonProcessingException {
        // TOPIC DTOオブジェクトの生成
        SearchDTO searchObj = new SearchDTO();

        // 戻り値格納用の変数
        searchObj.setSox_address(server_address);
        searchObj.setSox_user(sox_user);
        searchObj.setSox_pass(sox_pass);
        searchObj.setNode_name(node_name);
        searchObj.setKeyword(keyword);
        String result_msg = searchService.search(searchObj);

        return result_msg;
    }
    // endregion

    // region sox上の全てのノードをサブスクライブするためのend point
    @RequestMapping("/search-all")
    @ResponseBody
    // region Searchメソッド
    public String searchAll(
            @RequestParam(value = "server_address", defaultValue = "") String server_address,
            @RequestParam(value = "sox_user", defaultValue = "") String sox_user,
            @RequestParam(value = "sox_pass", defaultValue = "") String sox_pass) throws JsonProcessingException {
        // @RequestParam(value = "db_address", defaultValue = "") String db_address,
        // @RequestParam(value = "db_user", defaultValue = "") String db_user,
        // @RequestParam(value = "db_pass", defaultValue = "") String db_pass) throws JsonProcessingException {
        // TOPIC DTOオブジェクトの生成
        SearchDTO searchObj = new SearchDTO();

        // 戻り値格納用の変数
        searchObj.setSox_address(server_address);
        searchObj.setSox_user(sox_user);
        searchObj.setSox_pass(sox_pass);
        List<String> nodeList = searchService.getAllDevices(searchObj);
        searchObj.setNode_name(nodeList);
        String result_msg = searchService.search(searchObj);

        return result_msg;
    }
    // endregion

    // region SOX上の登録デバイス名を全て取得する
    @RequestMapping("/getAllDevicesFromSOX")
    @ResponseBody
    // region Searchメソッド
    public String getAllDevices(
            @RequestParam(value = "server_address", defaultValue = "") String server_address,
            @RequestParam(value = "sox_user", defaultValue = "") String sox_user,
            @RequestParam(value = "sox_pass", defaultValue = "") String sox_pass) throws JsonProcessingException {
        // TOPIC DTOオブジェクトの生成
        SearchDTO searchObj = new SearchDTO();

        // 戻り値格納用の変数
        searchObj.setSox_address(server_address);
        searchObj.setSox_user(sox_user);
        searchObj.setSox_pass(sox_pass);
        List<String> nodeList = searchService.getAllDevices(searchObj);
        String json = "";
        for (String node: nodeList) {
            json += node + ", \n";
        }
        return json;
    }
    // endregion

    // region SOXから特定デバイスの情報を取得する
    @RequestMapping("/getDeviceDetailFromSOX")
    @ResponseBody
    public String getAllDevices(
            @RequestParam(value = "server_address", defaultValue = "") String server_address,
            @RequestParam(value = "sox_user", defaultValue = "") String sox_user,
            @RequestParam(value = "sox_pass", defaultValue = "") String sox_pass,
            @RequestParam(value = "node_name", defaultValue = "") List<String> node_name) throws JsonProcessingException {
            // TOPIC DTOオブジェクトの生成
        SearchDTO searchObj = new SearchDTO();

        String json = "";
        ObjectMapper mapper = new ObjectMapper();
        // 戻り値格納用の変数
        searchObj.setSox_address(server_address);
        searchObj.setSox_user(sox_user);
        searchObj.setSox_pass(sox_pass);
        searchObj.setNode_name(node_name);
        List<DeviceDTO> deviceDetailList = searchService.getDeviceDetail(searchObj);
        Map<String, List<DeviceDTO>> dataMap = new HashMap<>();
        dataMap.put("deviceDetailList", deviceDetailList);
        json = mapper.writeValueAsString(dataMap);
        // json = json.substring(0, json.length()-1);

        return json;
    }
    // endregion

    // region SOXから特定デバイスの情報を取得する
    @RequestMapping("/getDeviceData")
    @ResponseBody
    // public String getAllDevices(@RequestParam(value = "node_name", defaultValue = "") List<String> node_name) throws JsonProcessingException {
    public String getDeviceData(@RequestParam(value = "node_name", defaultValue = "") String node_name,
                                @RequestParam(value = "time_from", defaultValue = "0.0") double time_to,
                                @RequestParam(value = "time_to", defaultValue = "0.0") double time_from)throws JsonProcessingException {
        // TOPIC DTOオブジェクトの生成

        List<Data> data = searchService.getDeviceData(node_name, time_to, time_from);
        Map<String, List<Data>> dataMap = new HashMap<>();
        dataMap.put("DeviceData", data);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        json = mapper.writeValueAsString(dataMap);
        return json;
    }
    // endregion

    // region SOXから特定デバイスの情報を取得する
    @RequestMapping("/getAllDeviceFromStoreDB")
    @ResponseBody
    // public String getAllDevices(@RequestParam(value = "node_name", defaultValue = "") List<String> node_name) throws JsonProcessingException {
    public String getAllDevices(@RequestParam(value = "node_name", defaultValue = "") String node_name) throws JsonProcessingException {
        // TOPIC DTOオブジェクトの生成

        List<String> topicNameList= searchService.getAllDevicesFromStoreDB();
        String json = "";
        for (String node: topicNameList) {
            json += node + ", \n";
        }
        return json;
    }
    // endregion

}
