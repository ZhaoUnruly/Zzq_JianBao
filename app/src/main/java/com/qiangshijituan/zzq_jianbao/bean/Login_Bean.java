package com.qiangshijituan.zzq_jianbao.bean; /**
 * Copyright 2016 bejson.com
 */

/**
 * Auto-generated: 2016-12-14 19:43:13
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Login_Bean {

    private String status;
    private String info;
    private Data data;
    private String token;
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getInfo() {
        return info;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public Data getData() {
        return data;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getToken() {
        return token;
    }

    /**
     * Copyright 2016 bejson.com
     */

    /**
     * Auto-generated: 2016-12-14 19:43:13
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Data {

        private int id;
        private String name;
        private int state;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setState(int state) {
            this.state = state;
        }
        public int getState() {
            return state;
        }

    }

}