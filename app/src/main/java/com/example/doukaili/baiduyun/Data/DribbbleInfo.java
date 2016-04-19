package com.example.doukaili.baiduyun.Data;

import java.text.SimpleDateFormat;

/**
 * Created by Dou.Kaili on 2016/4/16.
 */
public class DribbbleInfo {
    public static final String DRIBBBLE_BASE_AUTH = "https://dribbble.com/oauth/authorize";
    public static final String DRIBBBLE_AUTH_TOKEN = "https://dribbble.com/oauth/token";
    public static final String DRIBBBLE_CALL_BACK = "https://www.doukaili.baiduyun";

//    public static final String DRIBBBLE_CLIENT_ID = "75349c965ebf2921cf1aebb3e3e442692441f49df73136f3483b2e0fcd55410d";
//    public static final String DRIBBBLE_CLIENT_SECRET = "33528ad1f9a36832eda52ab7d19e3c382c5d6c4ff993e079a5a4f8aca09ab388";
    public static final String DRIBBBLE_CLIENT_ID = "48a6cf0ea8bf23318bfe2c1151d69904ccfec8c6473cfeef14bcdfe3d909ed33";
    public static final String DRIBBBLE_CLIENT_SECRET = "91d61b8abee623339740dd794ab711a9627833de37e5defed6bc004202cd257d";
    public static final String DRIBBBLE_ACCESSTOKEN = "87611fd4beb855cc2fe203284b0210f1d1b33119ab335eb56144b1c77272f759";
    public static String mState;

    public static final String DRIBBBLE_LOGIN_URL = DRIBBBLE_BASE_AUTH+"?"+
            "client_id=" + DRIBBBLE_CLIENT_ID +
            "&redirect_uri=" + DRIBBBLE_CALL_BACK +
            "&scope=" + "public write comment upload" +
            "&state=" + mState;
    public static final String REQUEST_MY_INFO = "https://api.dribbble.com/v1/user";

    public static final String DRIBBBLE_DATA_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DRIBBBLE_DATA_FORMAT_PATTERN);

    public static final String REQUEST_SHOTS = "https://api.dribbble.com/v1/shots/";

}
