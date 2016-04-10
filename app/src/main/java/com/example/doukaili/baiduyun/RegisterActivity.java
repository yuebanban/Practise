package com.example.doukaili.baiduyun;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dou.Kaili on 3/31/2016.
 */
public class RegisterActivity extends Activity {

    private Button button;
    static private EditText responseCode;
//    MyThread myThread;
//    private MyHandler myHandler;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        button = (Button)findViewById(R.id.login);
        responseCode = (EditText)findViewById(R.id.response);

//        myHandler = new MyHandler(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            try {
//                myThread = new MyThread();
//                myThread.start();
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }catch (Exception e) {

            }
            }
        });
    }

//    private static class MyHandler extends Handler {
//
//        private final WeakReference<RegisterActivity> mActivity;
//
//        public MyHandler(RegisterActivity activity) {
//            mActivity = new WeakReference<RegisterActivity>(activity);
//        }
//
//        public void handleMessage(Message msg) {
//            if(mActivity.get()!=null) {
//                if(msg.what == 0x123) {
//                    responseCode.setText(response);
//                }
//            }
//        }
//    }

    public String get() {
        String response = null;
        String path = " protected String doInBackground(String... params) {";
        HttpURLConnection connection = null;
        try{
            URL url = new URL(path);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);
            int responseCode = connection.getResponseCode();
            if(responseCode == 200) {
                InputStream is = connection.getInputStream();
                response = getStringFromInputStream(is);
            }else {
                throw new NetworkErrorException("response status is"+ responseCode);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return response;
    }

    public void post(String path, String json) {
        HttpURLConnection conn = null;
        try{
            URL url = new URL(path);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            byte[] content = json.getBytes("utf-8");
            out.write(content, 0, content.length);
            out.flush();
            out.close();
            InputStream is = conn.getInputStream();
            getStringFromInputStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
    }

    private  String getStringFromInputStream(InputStream is) throws IOException{
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len =-1;
        while((len = is.read(buffer))!=-1) {
            os.write(buffer,0,len);
        }
        is.close();
        String state = os.toString();
        os.close();
        return state;
    }

//    class MyThread extends Thread {
//        public void run() {
//            get();
//            myHandler.sendEmptyMessage(0x123);
//        }
//    }
    class DownTask extends AsyncTask<String,Integer,String> {
        Context mContext;
        public DownTask(Context cx) {
            mContext = cx;
        }
        protected String doInBackground(String... params) {
            return RegisterActivity.this.get();
        }

        protected void onPreExecute() {

        }
        protected void onPostExecute(String result) {
            RegisterActivity.responseCode.setText(result);
        }
    }

}
