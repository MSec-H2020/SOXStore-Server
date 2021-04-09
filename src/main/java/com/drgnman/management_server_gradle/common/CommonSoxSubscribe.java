package com.drgnman.management_server_gradle.common;

import com.drgnman.management_server_gradle.Entity.Data;
import com.drgnman.management_server_gradle.Entity.Topic;
import com.drgnman.management_server_gradle.Repository.DataRepository;
import com.drgnman.management_server_gradle.Repository.TopicRepository;
import jp.ac.keio.sfc.ht.sox.protocol.TransducerValue;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxConnection;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxDevice;
import jp.ac.keio.sfc.ht.sox.soxlib.event.SoxEvent;
import jp.ac.keio.sfc.ht.sox.soxlib.event.SoxEventListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CommonSoxSubscribe implements SoxEventListener {

    TopicRepository topicRepository = null;
    DataRepository dataRepository = null;
    // Connection db_con = null;
    // PreparedStatement ps = null;
    // CommonDBConnector dbconnector = new CommonDBConnector();
    // String db_url = null;
    // String db_user = null;
    // String db_pass = null;
    // String sql = "INSERT INTO topic values(?, ?)";

    public CommonSoxSubscribe(SoxConnection con, TopicRepository topicRepository,DataRepository dataRepository, String device_name) throws Exception {
    // public void CommonSoxSubscribe(SoxConnection con, String device_name, String db_url, String db_user, String db_pass) throws Exception {
        // this.db_url = db_url;
        // this.db_user = db_user;
        // this.db_pass = db_pass;

        // region Subscribeの一連処理
        SoxDevice subscribeDevice = new SoxDevice(con, device_name);
        this.topicRepository = topicRepository;
        this.dataRepository = dataRepository;
        subscribeDevice.subscribe();
        subscribeDevice.addSoxEventListener(this);
        // endregion
   }

   @Override
   public void handlePublishedSoxEvent(SoxEvent soxEvent) {
        List<TransducerValue> values = soxEvent.getTransducerValues();
       // subscribeした値への処理を追記すること
       for (TransducerValue value : values) {
           Data data = new Data();
           System.out.println("TransducerValue[id:" + value.getId()
                   + ", rawValue:" + value.getRawValue() + ", typedValue:"
                   + value.getTypedValue() + ", timestamp:"
                   + value.getTimestamp() + "]");
           // トピックIDの設定
           data.setTopic_id(soxEvent.getDevice().getId());
           // データIDの設定(データIDが存在しない場合は、トピックIDを割り当てる)
           if (value.getId() != null) {
               data.setTransducer_id(value.getId());
           } else {
               data.setTransducer_id(soxEvent.getDevice().getId());
           }
           // とりあえず全部、valueに突っ込ませる
           data.setValue(value.getRawValue());
           // timestampの設定
           data.setPub_timestamp(value.getTimestamp());

           dataRepository.save(data);

       }
       // dataの保存
       // dataRepository.save(data);

       // region jdbc ver.
       // if (db_con == null) db_con = dbconnector.dbConnection(db_url, db_user, db_pass);
       // try {
       //     ps = db_con.prepareStatement(sql);
       //     ps.setString(1, data.getTopic_id());
       //     ps.setString(2, data.getData_id());
       //     ps.setString(3, data.getValue());
       //     int result = ps.executeUpdate();
       //     db_con.commit();
       //     db_con.setAutoCommit(false);
       // } catch (SQLException throwables) {
       //     throwables.printStackTrace();
       // }
       // endregion
   }
}
