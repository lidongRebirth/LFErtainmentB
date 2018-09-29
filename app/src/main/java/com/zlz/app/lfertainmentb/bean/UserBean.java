package com.zlz.app.lfertainmentb.bean;


import com.zlz.app.lfertainmentb.utils.StringUtil;

import java.util.List;

/**
 * 创建者：张磊
 * 时间：2018/2/6
 * 类描述：
 * 修改人：
 * 修改时间：2018/2/6
 * 修改备注：
 */
public class UserBean {

    private String address;
    private String headimg;
    private String nickname;
    private ClubBean club;
    private AreadressBean areadress;
    private String username;
    private String token;
    private String usersig;
    private String realname;
    private String openid;
    private String job;
    private String gender;
    private String id;
    private int authtype;
    private int teamid;
    private int integral;
    private int currency;
    private int msg;
    private boolean isTourist;
    private List<ThirdUsersBean> thirdUsers;

    public boolean isTourist() {
        return isTourist;
    }

    public void setTourist(boolean tourist) {
        isTourist = tourist;
    }

    public int getMsg() {
        return msg;
    }

    public void setMsg(int msg) {
        this.msg = msg;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public String getUsersig() {
        return usersig;
    }

    public void setUsersig(String usersig) {
        this.usersig = usersig;
    }

    public int getTeamid() {
        return teamid;
    }

    public void setTeamid(int teamid) {
        this.teamid = teamid;
    }

    public int getAuthtype() {
        return authtype;
    }

    public void setAuthtype(int authtype) {
        this.authtype = authtype;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGender() {
        return StringUtil.isEmpty(gender) ? "1" : gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getNickname() {
        if (nickname == null) nickname = "";
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public ClubBean getClub() {
        return club;
    }

    public void setClub(ClubBean club) {
        this.club = club;
    }

    public AreadressBean getAreadress() {
        return areadress;
    }

    public void setAreadress(AreadressBean areadress) {
        this.areadress = areadress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public List<ThirdUsersBean> getThirdUsers() {
        return thirdUsers;
    }

    public void setThirdUsers(List<ThirdUsersBean> thirdUsers) {
        this.thirdUsers = thirdUsers;
    }

    public static class ClubBean {
        /**
         * club : 王
         * id : 8
         * club_qp : 0
         * club_sx : 0
         * updated : 2018-02-01 11:18:29
         * created : 2018-02-01 11:18:29
         */

        private String club;
        private String id;
        private String club_qp;
        private String club_sx;
        private String updated;
        private String created;

        public String getClub() {
            return club;
        }

        public void setClub(String club) {
            this.club = club;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getClub_qp() {
            return club_qp;
        }

        public void setClub_qp(String club_qp) {
            this.club_qp = club_qp;
        }

        public String getClub_sx() {
            return club_sx;
        }

        public void setClub_sx(String club_sx) {
            this.club_sx = club_sx;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }
    }

    public static class AreadressBean {
        /**
         * areaid : 140105
         * created : 2018-02-06 11:03:40
         * id : 2
         * cityid : 140100
         * userid : 2
         * provid : 140000
         * updated : 2018-02-06 11:03:40
         */

        private int areaid;
        private String created;
        private String id;
        private int cityid;
        private String userid;
        private int provid;
        private String updated;

        public int getAreaid() {
            return areaid;
        }

        public void setAreaid(int areaid) {
            this.areaid = areaid;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public int getProvid() {
            return provid;
        }

        public void setProvid(int provid) {
            this.provid = provid;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }
    }

    public static class ThirdUsersBean {
        private String openid;
        private int usertype;

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public int getUsertype() {
            return usertype;
        }

        public void setUsertype(int usertype) {
            this.usertype = usertype;
        }
    }
}
