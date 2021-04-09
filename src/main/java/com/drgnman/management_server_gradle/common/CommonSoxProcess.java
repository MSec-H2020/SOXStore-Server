package com.drgnman.management_server_gradle.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.drgnman.management_server_gradle.dto.PublishDTO;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;

import jp.ac.keio.sfc.ht.sox.protocol.Device;
import jp.ac.keio.sfc.ht.sox.protocol.Transducer;
import jp.ac.keio.sfc.ht.sox.protocol.TransducerValue;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxConnection;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxDevice;
import jp.ac.keio.sfc.ht.sox.soxlib.event.SoxEvent;
import jp.ac.keio.sfc.ht.sox.soxlib.event.SoxEventListener;

public class CommonSoxProcess {
    // region 接続処理(引数 URLのみ)
    public SoxConnection CreateSoxConnection(String url) throws IOException, XMPPException, SmackException {
        SoxConnection con = null;
        con = new SoxConnection(url, false);
        return con;
    }
    // endregion

    // region 接続処理(引数 URL, ユーザ名, パスワード)
    public SoxConnection CreateSoxConnection(String url, String user_name, String password) throws IOException, XMPPException, SmackException {
        SoxConnection con = new SoxConnection(url, user_name, password, false);
        return con;
    }
    // endregion

    // region デバイス情報を取得する。
    public Device DeviceInfo(SoxConnection con, String device_name) throws Exception {
        // region Subscribeの一連処理
        SoxDevice device = new SoxDevice(con, device_name);

        // Deviceのメタ情報を取得する (今回は使わないかも?)
        // deviceInfoオブジェクト内にそれぞれのpropertyが存在する
        Device deviceInfo = device.getDevice();

        return deviceInfo;
    }
    // endregion

    public String Publish(SoxConnection con, PublishDTO publishDto) throws Exception {
        SoxDevice soxDevice = new SoxDevice(con, publishDto.getTheme());
        List<TransducerValue> valueList = new ArrayList<TransducerValue>();

        TransducerValue username = new TransducerValue();
        username.setId("UserName");
        username.setRawValue(publishDto.getUsername());
        username.setTypedValue(publishDto.getUsername());
        username.setCurrentTimestamp();
        valueList.add(username);

        TransducerValue latitude = new TransducerValue();
        latitude.setId("Latitude");
        latitude.setRawValue(publishDto.getLatitude());
        latitude.setTypedValue(publishDto.getLatitude());
        latitude.setCurrentTimestamp();
        valueList.add(latitude);

        TransducerValue longitude = new TransducerValue();
        longitude.setId("Longitude");
        longitude.setRawValue(publishDto.getLongitude());
        longitude.setTypedValue(publishDto.getLongitude());
        longitude.setCurrentTimestamp();
        valueList.add(longitude);


        TransducerValue smiledegree = new TransducerValue();
        smiledegree.setId("SmileDegree");
        smiledegree.setRawValue(publishDto.getSmiledegree());
        smiledegree.setTypedValue(publishDto.getSmiledegree());
        smiledegree.setCurrentTimestamp();
        valueList.add(smiledegree);

        TransducerValue imagephoto = new TransducerValue();
        imagephoto.setId("ImagePhoto");
        imagephoto.setRawValue(publishDto.getImagephoto());
        imagephoto.setTypedValue(publishDto.getImagephoto());
        imagephoto.setCurrentTimestamp();
        valueList.add(imagephoto);

        soxDevice.publishValues(valueList);

        return "Publish to " + publishDto.getTheme() + " Complete";
    }
}
