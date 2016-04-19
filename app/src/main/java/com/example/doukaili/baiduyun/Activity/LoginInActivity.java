package com.example.doukaili.baiduyun.Activity;

import android.accounts.NetworkErrorException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.doukaili.baiduyun.Data.DribbbleInfo;
import com.example.doukaili.baiduyun.Data.UserInfo;
import com.example.doukaili.baiduyun.R;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dou.Kaili on 2016/4/16.
 */
public class LoginInActivity extends AppCompatActivity {
    private WebView mWebView;
    private ProgressBar mProgressbar;

    private MyHandle mHandle;
    private Mythread mThead;
    private MyGetThread mGetThread;

    private String accessToken;
    private JSONObject userInfo;
    private String returnCodeWneView;
    private final JSONObject requestJson = new JSONObject();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        setupWeb();
        mHandle = new MyHandle();
        mThead = new Mythread();
        mGetThread = new MyGetThread();
    }
    private void initView() {
        mWebView = (WebView)findViewById(R.id.login_web);
        mProgressbar = (ProgressBar)findViewById(R.id.progressbar);
    }
    private void setupWeb() {
        mProgressbar.setVisibility(View.VISIBLE);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("dkl", "LoginActivity.redirect");
                returnCodeWneView = null;
                if (url.startsWith(DribbbleInfo.DRIBBBLE_CALL_BACK)) {
                    if (url.indexOf("code=") != -1) {
                        int startIndex = url.indexOf("code=") + "code=".length();
                        int endIndex = url.indexOf("&state");
                        returnCodeWneView = url.substring(startIndex, endIndex);
                    }
                    if (!TextUtils.isEmpty(returnCodeWneView)) {
                        Log.d("dkl","Authrized Success");
                        requestForAccessToken(returnCodeWneView);
                    } else {
                        Toast.makeText(LoginInActivity.this, "Please try again", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        Log.d("dkl", "LoginActivity.mWebView.Loadurl");
        mWebView.loadUrl(DribbbleInfo.DRIBBBLE_LOGIN_URL);
    }

    private void requestForAccessToken(String returnCode) {
        try {
            requestJson.put("client_id",DribbbleInfo.DRIBBBLE_CLIENT_ID);
            requestJson.put("client_secret",DribbbleInfo.DRIBBBLE_CLIENT_SECRET);
            requestJson.put("code",returnCode);
            requestJson.put("state",DribbbleInfo.mState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mThead.start();
    }
    public static String sendPost(String url,JSONObject params) {
        HttpURLConnection conn = null;
        try {
            URL realURL = new URL(url);
            conn = (HttpURLConnection)realURL.openConnection();
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setRequestProperty("Content_Type","application/x-www-form-urlencoded");

            conn.setDoOutput(true);
            String data = params.toString();
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            out.close();

            int responCode = conn.getResponseCode();
            Log.d("dkl","responCode = "+responCode);
            //if(responCode == 200) {
                InputStream in = conn.getInputStream();
                String response = getStringFromInputStream(in);
                return response;
            //}else  {
            //    throw new NetworkErrorException("response status is" + responCode);
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }
    public static JSONObject sendGet(String url) {
        HttpURLConnection conn = null;
        try {
            URL reaUrl = new URL(url);
            conn = (HttpURLConnection)reaUrl.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(5000);
            conn.setRequestProperty("Authorization"," Bearer "+DribbbleInfo.DRIBBBLE_ACCESSTOKEN);

            int requestCode = conn.getResponseCode();
            if(requestCode == 200) {
                InputStream in = conn.getInputStream();
                String response = getStringFromInputStream(in);
                JSONObject object = new JSONObject(response);
                return object;
            }else  {
                throw new NetworkErrorException("response status is" + requestCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String getStringFromInputStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = is.read(buffer)) != -1) {
            os.write(buffer,0,len);
        }
        is.close();
        String state = os.toString();
        os.close();
        return state;
    }
    private class MyHandle extends Handler {
        public void handleMessage(Message msg) {
            if(msg.what == 0x123) {
                mGetThread.start();
            }
            if(msg.what == 0x111) {
                mProgressbar.setVisibility(View.INVISIBLE);
                setUserInfo();
                Intent intent  = new Intent(LoginInActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }
    }
    private class Mythread extends Thread {
        public Mythread() {}
        public void run() {
            accessToken = sendPost(DribbbleInfo.DRIBBBLE_AUTH_TOKEN,requestJson);
            Log.d("dkl","sendpost"+accessToken);
            mHandle.sendEmptyMessage(0x123);
        }
    }
    private class MyGetThread extends Thread {
        public MyGetThread() {}
        public void run() {
            userInfo = sendGet(DribbbleInfo.REQUEST_MY_INFO);
            Log.d("dkl","senGet"+userInfo);
            mHandle.sendEmptyMessage(0x111);
        }
    }
    private void setUserInfo() {
        UserInfo mUserInfo = UserInfo.getUserInfoInstance(userInfo);
    }
}
