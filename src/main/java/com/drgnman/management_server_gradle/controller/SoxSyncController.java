package com.drgnman.management_server_gradle.controller;


import com.drgnman.management_server_gradle.common.CommonSoxProcess;
import com.drgnman.management_server_gradle.service.SoxSyncService;
import jp.ac.keio.sfc.ht.sox.soxlib.SoxConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SoxSyncController {
    @Autowired
    SoxSyncService soxSyncService;

    @RequestMapping("/soxsync")
    @ResponseBody
    public String index() {
        // soxSyncService.soxSync("ssm-soxfire.ht.sfc.keio.ac.jp");
        soxSyncService.soxSync("localhost");

        return "Hello World!";
    }
}
