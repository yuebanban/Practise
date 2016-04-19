package com.example.doukaili.baiduyun.Fragment;


import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doukaili.baiduyun.Activity.ShotDetailActivity;
import com.example.doukaili.baiduyun.Adapter.DribbbleShotAdapter;
import com.example.doukaili.baiduyun.Data.DribbbleInfo;
import com.example.doukaili.baiduyun.Data.Shot;
import com.example.doukaili.baiduyun.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dou.Kaili on 3/30/2016.
 */
public class FragmentOnline extends Fragment {

    private RecyclerView recyclerView;

    private JSONArray data;
    private List<Shot> shotList = new ArrayList<>();
    public DribbbleShotAdapter myCardAdapter;

    private MyHandle myHandle;
    private MyThread myThread;
    private Map<String,String> request;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container,Bundle savedInstanceState) {
        recyclerView = (RecyclerView)layoutInflater.inflate(
                R.layout.list,container,false);
        setupRecycleView(recyclerView);

        myHandle = new MyHandle();
        myThread = new MyThread();

        myThread.start();
        return recyclerView;
    }

    private JSONArray loadData() {
        String url = DribbbleInfo.REQUEST_SHOTS;
        HttpURLConnection conn = null;
        try {
            URL reaUrl = new URL(url);
            conn = (HttpURLConnection)reaUrl.openConnection();
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(5000);
            conn.setRequestProperty("Authorization"," Bearer "+DribbbleInfo.DRIBBBLE_ACCESSTOKEN);

            int requestCode = conn.getResponseCode();
            Map<String,List<String>> map = conn.getHeaderFields();
            List requsetLink = map.get("Link");
            Log.d("dkl","requestCode = "+requestCode);
            if(requestCode == 200) {
                InputStream in = conn.getInputStream();
                String response = getStringFromInputStream(in);
                JSONArray object = new JSONArray(response);
                return object;
            }else  {
                throw new NetworkErrorException("response status is" + requestCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
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

    private void setupRecycleView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        myCardAdapter = new DribbbleShotAdapter(shotList, getActivity());
        recyclerView.setAdapter(myCardAdapter);
    }

    private void parseResponse() {
        shotList = new ArrayList<>();
        try{
            for(int i=0;i<data.length();i++) {
                JSONObject object = (JSONObject)data.get(i);
                Shot shot = new Shot(object);
                shotList.add(shot);
                Log.d("dkl","data changed" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        myCardAdapter=new DribbbleShotAdapter(shotList,getContext());
        recyclerView.setAdapter(myCardAdapter);
    }

    private class MyHandle extends Handler {
        public void handleMessage(Message msg) {
            if(msg.what == 0x123) {
                parseResponse();
            }
        }
    }
    private class MyThread extends Thread {
        public void run() {
            data = loadData();
            Log.d("dkl","shots = " + data);
            myHandle.sendEmptyMessage(0x123);
        }
    }


}
