package com.example.doukaili.baiduyun;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
        recyclerView.setAdapter(new MyAdapter(mDataSet,getActivity()));
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private String[] mDataSet ;
        private Context mContext;
        public static class ViewHolder extends RecyclerView.ViewHolder {

            public View view;
            public TextView mName;
            public TextView mTitle;
            public CardView mCardView;
            public ViewHolder(View v) {
                super(v);
                mName = (TextView)v.findViewById(R.id.shot_name);
                mCardView = (CardView)v.findViewById(R.id.card_view);
            }
        }

        public MyAdapter(String[] myDataset,Context context) {
            mDataSet = myDataset;
            mContext = context;
        }

        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_card_view, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mName.setText(mDataSet[position]);
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,ShotDetailActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
        public int getItemCount() {
            return mDataSet.length;
        }
    }
}
