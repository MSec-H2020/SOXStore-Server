package com.drgnman.management_server_gradle.dto;

import java.util.List;

public class SearchDTO {
    // SOX Info
    private String sox_address;         // SOX Address
    private String sox_user;            // SOX Username
    private String sox_pass;            // SOX Password
    private List<String> node_name;     // SOX node_name
    private String keyword;             // keyword flag

    public String getSox_address() {
        return sox_address;
    }

    public void setSox_address(String sox_address) {
        this.sox_address = sox_address;
    }

    public String getSox_user() {
        return sox_user;
    }

    public void setSox_user(String sox_user) {
        this.sox_user = sox_user;
    }

    public String getSox_pass() {
        return sox_pass;
    }

    public void setSox_pass(String sox_pass) {
        this.sox_pass = sox_pass;
    }

    public List<String> getNode_name() {
        return node_name;
    }

    public void setNode_name(List<String> node_name) {
        this.node_name = node_name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
