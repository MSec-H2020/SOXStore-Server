package com.drgnman.management_server_gradle.controller;

import com.drgnman.management_server_gradle.common.CommonSoxProcess;
import com.drgnman.management_server_gradle.common.CommonSoxSubscribe;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TopController {
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        // CommonSoxSubscribe subscriber= new CommonSoxSubscribe();
        CommonSoxProcess soxproc = new CommonSoxProcess();

        try {
            // デバッグ用に使って良い。全部完了したらここの関数は最後に消しておくこと
            // region Subscribeテスト
            // subscriber.CommonSoxSubscribe("ssm-soxfire.ht.sfc.keio.ac.jp","ssmuser",
            //         "ssm2020", "probeDevice");
            // endregion

            // region デバイス名一覧取得テスト
            // SoxConnection con = soxproc.CreateSoxConnection("ssm-soxfire.ht.sfc.keio.ac.jp");
            // List<String> nodeList = con.getAllSensorList();

            // for(String node:nodeList){
            //     System.out.println(node);
            // }
            // System.out.println(nodeList.size());
            // endregion

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello World!";
    }
}
