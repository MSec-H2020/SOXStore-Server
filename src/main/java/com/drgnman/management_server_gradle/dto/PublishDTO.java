package com.drgnman.management_server_gradle.dto;

import java.io.Serializable;
import java.util.List;

public class PublishDTO implements Serializable {
    // SOX Info
    private static final long serialVersionUID = 1L;
    private String sox_address;              // SOX Address
    private String sox_user;                 // SOX Username
    private String sox_pass;                 // SOX Password
    private String theme;                    // theme on SmileCityReport => (this means "device" on SOX)
    private String user_id;                  // post user id
    private String user_name;                // marketplace user name
    private String public_key;               // marketplace public key
    private String back_photo;               // base64 URI
    private String smiling_probability;      // publisher smiling_probability
    private String lat;                      // publisher location(lat)
    private String lng;                      // publisher location(lng)
    /// region old variable names
    // private String userid;                // post user id
    // private String latitude;              //
    // private String longitude;             //
    // private String smiledegree;
    // private String imagephoto;            // Base64
    /// endregion

    private List<String> node_name;        // SOX node_name

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

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBack_photo() {
        return back_photo;
    }

    public void setBack_photo(String back_photo) {
        this.back_photo = back_photo;
    }

    public String getSmiling_probability() {
        return smiling_probability;
    }

    public void setSmiling_probability(String smiling_probability) {
        this.smiling_probability = smiling_probability;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    /// region old variable names
    // public String getUserid() {
    //     return userid;
    // }

    // public void setUserid(String userid) {
    //     this.userid = userid;
    // }

    // public String getLatitude() {
    //     return latitude;
    // }

    // public void setLatitude(String latitude) {
    //     this.latitude = latitude;
    // }

    // public String getLongitude() {
    //     return longitude;
    // }

    // public void setLongitude(String longitude) {
    //     this.longitude = longitude;
    // }

    // public String getSmiledegree() {
    //     return smiledegree;
    // }

    // public void setSmiledegree(String smiledegree) {
    //     this.smiledegree = smiledegree;
    // }

    // public String getImagephoto() {
    //     return imagephoto;
    // }

    // public void setImagephoto(String imagephoto) {
    //     this.imagephoto = imagephoto;
    // }
    /// endregion

}
