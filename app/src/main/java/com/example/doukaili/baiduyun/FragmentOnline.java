package com.example.doukaili.baiduyun;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Dou.Kaili on 3/30/2016.
 */
public class FragmentOnline extends Fragment {

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container,Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView)layoutInflater.inflate(
                R.layout.list,container,false);
        setupRecycleView(recyclerView);
        return recyclerView;
    }

    private void setupRecycleView(RecyclerView recyclerView) {
        String[] mDataSet = {"nihao","wobuhao","henbuhao",};
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new MyAdapter(mDataSet));
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataSet ;
        public static class ViewHolder extends RecyclerView.ViewHolder {

            public View view;
            public TextView mTextView;
            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView)v.findViewById(R.id.list_textview);
            }
        }

        public MyAdapter(String[] myDataset) {
            mDataSet = myDataset;
        }

        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.list_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mTextView.setText(mDataSet[position]);
        }
        public int getItemCount() {
            return mDataSet.length;
        }
    }
}
