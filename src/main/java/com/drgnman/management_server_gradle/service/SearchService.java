package com.drgnman.management_server_gradle.service;

import com.drgnman.management_server_gradle.Entity.Data;
import com.drgnman.management_server_gradle.Entity.Topic;
import com.drgnman.management_server_gradle.Repository.DataRepository;
import com.drgnman.management_server_gradle.Repository.TopicRepository;
import com.drgnman.management_server_gradle.common.CommonDBConnector;
import com.drgnman.management_server_gradle.common.CommonSoxProcess;
import com.drgnman.management_server_gradle.dto.DeviceDTO;
import com.drgnman.management_server_gradle.dto.PublishDTO;
import com.drgnman.management_server_gradle.dto.SearchDTO;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxConnection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {
    @Autowired
    SoxSyncService soxSyncService;
    @Autowired
    DataRepository dataRepository;
    @Autowired
    TopicRepository topicRepository;
    // @Autowired
    // CommonDBConnector commonDBConnector;

    final static ModelMapper modelMapper = new ModelMapper();

    // region SOXにSubscribeを行う
    public String publish (PublishDTO publishObj) {
        try {
            CommonSoxProcess commonSoxProcess = new CommonSoxProcess();
            SoxConnection con = commonSoxProcess.CreateSoxConnection(publishObj.getSox_address(), publishObj.getSox_user(), publishObj.getSox_pass());
            String publishConfirm = commonSoxProcess.Publish(con, publishObj);
            return publishConfirm;
        } catch (Exception e) {
            // 検索がコケた場合にはとりあえず何か返しておく
            System.out.println("Error: " + e);
            return "failed";
        }
    }
    // endregion

    // region SOXにSubscribeを行う
    public String search (SearchDTO searchObj) {
        try {
            soxSyncService.soxSync(searchObj.getSox_address(), searchObj.getSox_user(), searchObj.getSox_pass(), searchObj.getNode_name(), searchObj.getKeyword());
            return "success";
        } catch (Exception e) {
            // 検索がコケた場合にはとりあえず何か返しておく
            System.out.println("Error: " + e);
            return "failed";
        }
    }
    // endregion

    // region SOXにSubscribeを行う
    public List<String> getAllDevices (SearchDTO searchObj) {
        try {
            List<String> nodeList = soxSyncService.soxGetAllDevices(searchObj.getSox_address(), searchObj.getSox_user(), searchObj.getSox_pass());
            return nodeList;
        } catch (Exception e) {
            // 検索がコケた場合にはとりあえず何か返しておく
            System.out.println("Error: " + e);
            return null;
        }
    }
    // endregion

    // region SOXからデバイスの詳細情報を取得する
    public List<DeviceDTO> getDeviceDetail (SearchDTO searchObj) {
        try {
            List<DeviceDTO> deviceList = soxSyncService.soxGetDeviceDetail(searchObj.getSox_address(), searchObj.getSox_user(), searchObj.getSox_pass(), searchObj.getNode_name());
            return deviceList;
        } catch (Exception e) {
            // 検索がコケた場合にはとりあえず何か返しておく
            System.out.println("Error: " + e);
            return null;
        }
    }
    // endregion

    // region DBからTopicIDに応じた最新のデータをとってくる
    public List<Data> getDeviceData (String nodeName, double time_from, double time_to) {
        try {
            List<Data> result = null;
            if (time_to > 0 && time_from > 0) {
                // time_toとtime_fromが設定された場合、データを検索する
                result = dataRepository.TimestampSearchByTopicIdAndTimeFromTo(nodeName, time_from, time_to);
                if (result == null) System.out.println("=====Data not Found!!=====");
            } else if (time_from > 0) {
                result = dataRepository.TimestampSearchByTopicIdAndTimeFrom(nodeName, time_from);
                if (result == null) System.out.println("=====Data not Found!!=====");
            }
            else {
                // topic_idから対象データの最新時間を取ってくる
                Data data = dataRepository.TimestampSearchByTopicIdLimit1(nodeName);
                result = dataRepository.DataSearchByTopicIDAndTimeStamp(nodeName, data.getPub_timestamp());
            }
            // 対象データの最新データだけを取ってくる
            return result;
        } catch (Exception e) {
            // 検索がコケた場合にはとりあえず何か返しておく
            System.out.println("Error: " + e);
            return null;
        }
    }

    // endregion

    // region DB上に登録されているデバイスを全て取得する
    public List<String> getAllDevicesFromStoreDB () {
        List<Topic> topicList = topicRepository.findAll();
        List<String> resultList = new ArrayList<>();
        for (Topic topic : topicList) {
            resultList.add(topic.getTopic_id());
        }
        return resultList;
    }
    // endregion

}
