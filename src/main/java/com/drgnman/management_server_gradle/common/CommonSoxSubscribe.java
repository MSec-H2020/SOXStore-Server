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
import java.util.List;

public class CommonSoxSubscribe implements SoxEventListener {

    TopicRepository topicRepository = null;
    DataRepository dataRepository = null;
    CommonCalculation cmnCalc = new CommonCalculation();
    CommonInstance cmnIns = new CommonInstance();
    public void CommonSoxSubscribe(SoxConnection con, TopicRepository topicRepository,DataRepository dataRepository, String device_name) throws Exception {
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
       Data data = new Data();
       for (TransducerValue value : values) {
           System.out.println("TransducerValue[id:" + value.getId()
                   + ", rawValue:" + value.getRawValue() + ", typedValue:"
                   + value.getTypedValue() + ", timestamp:"
                   + value.getTimestamp() + "]");
           // トピックIDの設定
           data.setTopic_id(soxEvent.getDevice().getId());
           // データIDの設定(データIDが存在しない場合は、トピックIDを割り当てる)
           if (value.getId() != null) {
               data.setData_id(value.getId());
           } else {
               data.setData_id(soxEvent.getDevice().getId());
           }
           // 位置情報の格納(transducer名がlatitude, longitudeの場合は設定する。
           if (cmnIns.lat.equals(value.getId())) {
               data.setLocation_lat(Double.parseDouble(value.getRawValue()));
           } else if (cmnIns.lng.equals(value.getId())) {
               data.setLocation_lng(Double.parseDouble(value.getRawValue()));
           } else if (cmnIns.range.equals(value.getId())) {
               data.setCover_distance(Double.parseDouble(value.getRawValue()));
           } else {
               // 上記以外の場合、データ本体として値をセットする
               data. setValue(value.getRawValue());
           }
           // timestampの設定
           if (data.getTimestamp() == null) data.setPub_timestamp(value.getTimestamp());
       }
       // dataの保存
       dataRepository.save(data);

       // topicの情報更新
       int ave_time = cmnCalc.calcAverage(dataRepository, soxEvent.getDevice().getId());
       Topic topic = topicRepository.TopicSearchByTopicId(soxEvent.getDevice().getId());
       topic.setLocation_lat(data.getLocation_lat());
       topic.setLocation_lng(data.getLocation_lng());
       topic.setCover_distance(data.getCover_distance());
       topic.setLifetime(ave_time);

       topicRepository.save(topic);
   }
}
