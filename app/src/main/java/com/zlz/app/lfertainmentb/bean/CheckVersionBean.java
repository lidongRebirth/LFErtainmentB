package com.zlz.app.lfertainmentb.bean;

/**
 * Created by Administrator on 2017/12/26.
 */

public class CheckVersionBean {

    /**
     * startFlag : 2
     * startImgUrl : http://apk.zhangfangyuan.com/serverimg/adimg/startup/startup2.png
     * created : 2018-01-31 17:59:47
     * resversion : 1
     * version : 0.1
     * appShowInfo : .

     1.4个菜单修改
     2.修改部分机型bug
     3.功能完善

     * newsid : 1
     * startImgGoUrl : null
     * downloadPath : http://192.168.1.192/file/tongzonghui.apk
     * resupdateFlag : 0
     * appType : 0
     * serverIp : 192.168.1.196
     * modified : 2018-02-09 10:34:03
     * id : 4
     */

    private int startFlag;
    private String startImgUrl;
    private String created;
    private String resversion;
    private String version;
    private String appShowInfo;
    private String newsid;
    private Object startImgGoUrl;
    private String downloadPath;
    private int resupdateFlag;
    private int appType;
    private String serverIp;
    private String modified;
    private int id;
    private int msg;

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public int getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(int startFlag) {
        this.startFlag = startFlag;
    }

    public String getStartImgUrl() {
        return startImgUrl;
    }

    public void setStartImgUrl(String startImgUrl) {
        this.startImgUrl = startImgUrl;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getResversion() {
        return resversion;
    }

    public void setResversion(String resversion) {
        this.resversion = resversion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppShowInfo() {
        return appShowInfo;
    }

    public void setAppShowInfo(String appShowInfo) {
        this.appShowInfo = appShowInfo;
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public Object getStartImgGoUrl() {
        return startImgGoUrl;
    }

    public void setStartImgGoUrl(Object startImgGoUrl) {
        this.startImgGoUrl = startImgGoUrl;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public int getResupdateFlag() {
        return resupdateFlag;
    }

    public void setResupdateFlag(int resupdateFlag) {
        this.resupdateFlag = resupdateFlag;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
