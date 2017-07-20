package com.example.dontworry.warehouse.fragment.magazine;

/**
 * Created by Don't worry on 2017/7/11.
 */

public class MagazineInfo {
    private String taid;
    private String topic_name;
    private String topic_url;
    private String cat_name;
    private String cover_img;
    private String addtime;

    public MagazineInfo() {
    }

    public MagazineInfo(String addtime ,String taid, String topic_name, String topic_url, String cat_name, String cover_img) {
        this.addtime = addtime;
        this.taid = taid;
        this.topic_name = topic_name;
        this.topic_url = topic_url;
        this.cat_name = cat_name;
        this.cover_img = cover_img;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }
    public String getTaid() {
        return taid;
    }

    public void setTaid(String taid) {
        this.taid = taid;
    }

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public String getTopic_url() {
        return topic_url;
    }

    public void setTopic_url(String topic_url) {
        this.topic_url = topic_url;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCover_img() {
        return cover_img;
    }

    public void setCover_img(String cover_img) {
        this.cover_img = cover_img;
    }

}
