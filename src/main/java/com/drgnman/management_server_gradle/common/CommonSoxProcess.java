package com.drgnman.management_server_gradle.common;

import java.io.IOException;
import java.util.List;

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
}
