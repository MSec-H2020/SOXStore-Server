package com.drgnman.management_server_gradle.service;

import com.drgnman.management_server_gradle.Entity.Topic;
import com.drgnman.management_server_gradle.Repository.DataRepository;
import com.drgnman.management_server_gradle.Repository.TopicRepository;
import com.drgnman.management_server_gradle.common.CommonSoxProcess;
import com.drgnman.management_server_gradle.common.CommonSoxSubscribe;
import jp.ac.keio.sfc.ht.sox.protocol.Device;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SoxSyncService {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    DataRepository dataRepository;
    // region デバイス名一覧取得テスト
    CommonSoxProcess soxproc = new CommonSoxProcess();
    CommonSoxSubscribe subscriber = new CommonSoxSubscribe();

    public void soxSync(String url) {
        try {
            //
            SoxConnection con = soxproc.CreateSoxConnection(url);
            List<String> nodeList = con.getAllSensorList();

            for (String node: nodeList) {
                System.out.println(node);
                Device device = soxproc.DeviceInfo(con, node);
                Topic topic = new Topic();
                topic.setTopic_id(device.getId());
                topic.setCategory(device.getDeviceType().toString());
                topic.setAddress1("");
                topic.setAddress2("");
                // 現状、初期値が全くないため、仮置き
                topic.setLifetime(1000);
                topic.setLocation_lat(0.0);
                topic.setLocation_lng(0.0);
                topic.setCover_distance(1000);
                topicRepository.save(topic);

                subscriber.CommonSoxSubscribe(con, topicRepository, dataRepository,device.getId());
            }

        System.out.println(nodeList.size());

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
        }

        // region Subscribe用コード
        // subscriber.CommonSoxSubscribe("ssm-soxfire.ht.sfc.keio.ac.jp","ssmuser",
        //         "ssm2020", "probeDevice");
        // endregion

    }
    // endregion
}
