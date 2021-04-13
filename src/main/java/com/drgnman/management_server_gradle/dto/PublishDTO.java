package com.drgnman.management_server_gradle.dto;

import java.io.Serializable;
import java.util.List;

public class PublishDTO implements Serializable {
    // SOX Info
    private static final long serialVersionUID = 1L;
    private String sox_address;         // SOX Address
    private String sox_user;            // SOX Username
    private String sox_pass;            // SOX Password
    private String theme;               // theme on SmileCityReport => (this means "device" on SOX)
    private String userid;            // post user id
    private String username;            // post user name
    private String latitude;            //
    private String longitude;           //
    private String smiledegree;
    private String imagephoto;          // Base64

    private List<String> node_name;     // SOX node_name

    public PublishDTO() {
    }

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

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getSmiledegree() {
        return smiledegree;
    }

    public void setSmiledegree(String smiledegree) {
        this.smiledegree = smiledegree;
    }

    public String getImagephoto() {
        return imagephoto;
    }

    public void setImagephoto(String imagephoto) {
        this.imagephoto = imagephoto;
    }

}
