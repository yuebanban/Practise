package com.example.doukaili.baiduyun.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.doukaili.baiduyun.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dou.Kaili on 4/12/2016.
 */
public class ShotDetailActivity extends AppCompatActivity {
    private RecyclerView mRecycleView;
    private LinearLayoutManager mLayoutManager;
    private List<Map<String,Object>> mData;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_detail);
        initView();
        initData();
        final Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar_detail);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //mRecycleView.setAdapter(new MyListAdapter(this));
        //fixListView();

    }
    private void initView() {
        //mListView = (ListView)findViewById(R.id.list_detail);
        mRecycleView = (RecyclerView)findViewById(R.id.recycle_detail);
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(new MyRecycleAdapter());

    }
    public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.ViewHolder> {
        public MyRecycleAdapter() {
        }
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comment_view,viewGroup,false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.mName.setText("meme");
        }
        public int getItemCount() {
            return 10;
        }
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mName;
            public ViewHolder(View view) {
                super(view);
                mName = (TextView)view.findViewById(R.id.comment_name);
            }
        }
    }
    private void initData() {
        mData = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for(int i=0;i<10;i++)
        {
            map = new HashMap<String, Object>();
            map.put("img", R.drawable.avatar);
            map.put("Name", i);
            map.put("Comment", "Cool...");
            mData.add(map);
        }
    }

//    private void fixListView() {
//        ListAdapter listAdapter = mListView.getAdapter();
//        if(listAdapter == null) {
//            return;
//        }
//        int totalHeight = 0;
//        for(int i=0,len=listAdapter.getCount();i<len;i++) {
//            View listItem = listAdapter.getView(i,null,mListView);
//            Log.d("dkl","fised"+i);
//            listItem.measure(0,0);
//            totalHeight += listItem.getMeasuredHeight();
//        }
//        ViewGroup.LayoutParams params = mListView.getLayoutParams();
//        params.height = totalHeight + (mListView.getDividerHeight()*(listAdapter.getCount()-1));
//        mListView.setLayoutParams(params);
//    }

//    static class ViewHolder
//    {
//        public ImageView img;
//        public TextView name;
//        public TextView comment;
//    }
//
//    private class MyListAdapter extends BaseAdapter{
//        private LayoutInflater mLayoutInflacter;
//        private List<Map<String,Object>> data = new ArrayList<>();
//        public MyListAdapter(Context context){
//            mLayoutInflacter = LayoutInflater.from(context);
//            data = mData;
//        }
//        public int getCount() {
//            return data.size();
//        }
//        public Object getItem(int position) {
//            return data.get(position);
//        }
//        public long getItemId(int position) {
//            return position;
//        }
//        public View getView(int position,View convertView,ViewGroup parent) {
//
//            if(convertView == null) {
//                Log.d("dkl","The new list item"+position);
//                convertView = mLayoutInflacter.inflate(R.layout.item_comment_view,null);
//            }
//            else {
//                Log.d("dkl","The list item"+position);
//
//            }
//            return convertView;
//        }
//    }
}
