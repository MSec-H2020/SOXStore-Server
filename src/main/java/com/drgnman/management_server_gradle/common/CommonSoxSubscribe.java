package com.drgnman.management_server_gradle.common;

import jp.ac.keio.sfc.ht.sox.protocol.Device;
import jp.ac.keio.sfc.ht.sox.protocol.TransducerValue;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxConnection;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxDevice;
import jp.ac.keio.sfc.ht.sox.soxlib.event.SoxEvent;
import jp.ac.keio.sfc.ht.sox.soxlib.event.SoxEventListener;

import java.util.List;

public class CommonSoxSubscribe implements SoxEventListener {
   public void CommonSoxSubscribe(String url, String user_name,
           String password, String device_name) throws Exception {

      CommonSoxProcess commonSoxProc = new CommonSoxProcess();

      // region Subscribeの一連処理
      SoxConnection con = commonSoxProc.CreateSoxConnection(url, user_name, password);
      SoxDevice subscribeDevice = new SoxDevice(con, device_name);

      // Deviceのメタ情報を取得する (今回は使わないかも?)
      // deviceInfoオブジェクト内にそれぞれのpropertyが存在する
      // Device deviceInfo = subscribeDevice.getDevice();

       subscribeDevice.subscribe();
       subscribeDevice.addSoxEventListener(this);
      // endregion
   }

   @Override
   public void handlePublishedSoxEvent(SoxEvent soxEvent) {
       List<TransducerValue> values = soxEvent.getTransducerValues();
       // subscribeした値への処理を追記すること
       for (TransducerValue value : values) {
           System.out.println("TransducerValue[id:" + value.getId()
                   + ", rawValue:" + value.getRawValue() + ", typedValue:"
                   + value.getTypedValue() + ", timestamp:"
                   + value.getTimestamp() + "]");
       }
   }
}
