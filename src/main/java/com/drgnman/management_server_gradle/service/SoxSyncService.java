package com.drgnman.management_server_gradle.service;

import com.drgnman.management_server_gradle.Entity.Topic;
import com.drgnman.management_server_gradle.Repository.DataRepository;
import com.drgnman.management_server_gradle.Repository.TopicRepository;
import com.drgnman.management_server_gradle.common.CommonDBConnector;
import com.drgnman.management_server_gradle.common.CommonSoxProcess;
import com.drgnman.management_server_gradle.common.CommonSoxSubscribe;
import com.drgnman.management_server_gradle.dto.DeviceDTO;
import jp.ac.keio.sfc.ht.sox.protocol.Device;
import jp.ac.keio.sfc.ht.sox.protocol.Transducer;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoxSyncService {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    DataRepository dataRepository;
    // region デバイス名一覧取得テスト
    CommonSoxProcess soxproc = new CommonSoxProcess();
    // CommonDBConnector dbconnector = new CommonDBConnector();
    // Connection db_con = null;
    // PreparedStatement ps = null;
    // String sql = "INSERT INTO topic values(?, ?)";

    // public void soxSync(String url, String user, String pass, String node, String db_url, String db_user, String db_pass) throws SQLException {
    public void soxSync(String url, String user, String pass, List<String> nodeList) throws SQLException {
        try {
            SoxConnection con = soxproc.CreateSoxConnection(url, user, pass);

            for(String node : nodeList) {
                Device device = soxproc.DeviceInfo(con, node);
                Topic topic = new Topic();
                topic.setTopic_id(device.getId());
                topic.setCategory(device.getDeviceType().toString());
                topicRepository.save(topic);
            }

            List<Topic> topicList = topicRepository.findAll();
            for(Topic tp: topicList) {
                new CommonSoxSubscribe(con, topicRepository, dataRepository, tp.getTopic_id());
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (XMPPException.XMPPErrorException xmppErrorException) {
            xmppErrorException.printStackTrace();
        } catch (XMPPException xmppException) {
            xmppException.printStackTrace();
        } catch (SmackException.NotConnectedException notConnectedException) {
            notConnectedException.printStackTrace();
        } catch (SmackException.NoResponseException noResponseException) {
            noResponseException.printStackTrace();
        } catch (SmackException smackException) {
            smackException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            // db_con.rollback();
        }

    }
    // endregion

    // region soxGetAllDevices
    public List<String> soxGetDevices(String url, String user, String pass) throws SQLException {
        List<String> nodeList = null;
        try {
            SoxConnection con = soxproc.CreateSoxConnection(url, user, pass);
            nodeList = con.getAllSensorList();

            List<Topic> topicList = topicRepository.findAll();
            for(Topic tp: topicList) {
                new CommonSoxSubscribe(con, topicRepository, dataRepository, tp.getTopic_id());
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (XMPPException.XMPPErrorException xmppErrorException) {
            xmppErrorException.printStackTrace();
        } catch (XMPPException xmppException) {
            xmppException.printStackTrace();
        } catch (SmackException.NotConnectedException notConnectedException) {
            notConnectedException.printStackTrace();
        } catch (SmackException.NoResponseException noResponseException) {
            noResponseException.printStackTrace();
        } catch (SmackException smackException) {
            smackException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            // db_con.rollback();
        }
        return nodeList;
    }
    //endregion


    // region
    public List<DeviceDTO> soxGetDeviceDetail(String url, String user, String pass, List<String> nodeList) throws SQLException {
        List<DeviceDTO> deviceDtoList = new ArrayList<DeviceDTO>();
        try {
            SoxConnection con = soxproc.CreateSoxConnection(url, user, pass);

            for (String node: nodeList) {
                DeviceDTO deviceDto = new DeviceDTO();
                Device device = soxproc.DeviceInfo(con, node);
                deviceDto = new DeviceDTO();
                deviceDto.setTopic_id(device.getId());
                deviceDto.setCategory(device.getDeviceType().toString());
                deviceDto.setTransducers(device.getTransducers());
                deviceDtoList.add(deviceDto);
            }
            List<Topic> topicList = topicRepository.findAll();
            for(Topic tp: topicList) {
                new CommonSoxSubscribe(con, topicRepository, dataRepository, tp.getTopic_id());
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (XMPPException.XMPPErrorException xmppErrorException) {
            xmppErrorException.printStackTrace();
        } catch (XMPPException xmppException) {
            xmppException.printStackTrace();
        } catch (SmackException.NotConnectedException notConnectedException) {
            notConnectedException.printStackTrace();
        } catch (SmackException.NoResponseException noResponseException) {
            noResponseException.printStackTrace();
        } catch (SmackException smackException) {
            smackException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            // db_con.rollback();
        }

        return deviceDtoList;
    }
    // endregion
}
