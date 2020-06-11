package com.drgnman.management_server_gradle.service;

import com.drgnman.management_server_gradle.common.CommonSoxProcess;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoxSyncService {
    // region デバイス名一覧取得テスト
    CommonSoxProcess soxproc = new CommonSoxProcess();

    public void soxSync(String url) {
        try {
            //
            SoxConnection con = soxproc.CreateSoxConnection(url);
            List<String> nodeList = con.getAllSensorList();

            // 取得したノード名ごとに何か処理を行わせる
            for (String node : nodeList) {
                System.out.println(node);

                List<String> words = new ArrayList<>();
                BreakIterator boundary = BreakIterator.getWordInstance();
                boundary.setText(node);
                for (int start = boundary.first(), end = boundary.last();
                     end != BreakIterator.DONE; start = end, end = boundary.next()) {
                    words.add(node.substring(start, end));
                }
                for (String word: words) {
                    System.out.println(word);
                }
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
        }

        // region Subscribe用コード
        // subscriber.CommonSoxSubscribe("ssm-soxfire.ht.sfc.keio.ac.jp","ssmuser",
        //         "ssm2020", "probeDevice");
        // endregion

    }
    // endregion
}
