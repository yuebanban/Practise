package com.example.doukaili.baiduyun;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dou.Kaili on 4/12/2016.
 */
public class ShotDetailActivity extends AppCompatActivity {
    private ListView mListView;
    private List<Map<String,Object>> mData;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_detail);
        initView();
        final Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar_detail);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mListView.setAdapter(new MyListAdapter(this));

    }
    private void initView() {
        mListView = (ListView)findViewById(R.id.list_detail);
    }
    private void initData() {
        mData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for(int i=0;i<10;i++)
        {
            map = new HashMap<String, Object>();
            map.put("img", R.drawable.avatar);
            map.put("Name", "doudou");
            map.put("Comment", "Cool...");
            mData.add(map);
        }
    }

    static class ViewHolder
    {
        public ImageView img;
        public TextView name;
        public TextView comment;
    }

    private class MyListAdapter extends BaseAdapter{
        private LayoutInflater mLayoutInflacter;
        public MyListAdapter(Context context){
            mLayoutInflacter = LayoutInflater.from(context);
        }
        public int getCount() {
            return mData.size();
        }
        public Object getItem(int position) {
            return position;
        }
        public long getItemId(int position) {
            return position;
        }
        public View getView(int position,View convertView,ViewGroup parent) {
            if(convertView == null) {
                convertView = mLayoutInflacter.inflate(R.layout.item_comment_view,null);
            }
            return convertView;
        }
    }
}
