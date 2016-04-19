package com.example.doukaili.baiduyun.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doukaili.baiduyun.Activity.ShotDetailActivity;
import com.example.doukaili.baiduyun.Data.Shot;
import com.example.doukaili.baiduyun.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dou.Kaili on 2016/4/19.
 */
    public class DribbbleShotAdapter extends RecyclerView.Adapter<DribbbleShotAdapter.ViewHolder> {
        private List<Shot> mDataSet = new ArrayList<>();

        private Context mContext;
        public static class ViewHolder extends RecyclerView.ViewHolder {

            public View view;
            public CardView mCardView;

            public TextView mName;
            public TextView mTitle;
            public SimpleDraweeView avatar;
            public SimpleDraweeView piece;
            public TextView viewCount;
            public ViewHolder(View v) {
                super(v);
                mName = (TextView)v.findViewById(R.id.shot_name);
                mTitle = (TextView)v.findViewById(R.id.shot_title);
                avatar = (SimpleDraweeView)v.findViewById(R.id.shot_avatar);
                piece = (SimpleDraweeView)v.findViewById(R.id.shot_piece);
                viewCount = (TextView)v.findViewById(R.id.view_count);
                mCardView = (CardView)v.findViewById(R.id.card_view);
            }
        }

        public DribbbleShotAdapter(List<Shot> myDataset,Context context) {
            mDataSet = myDataset;
            mContext = context;
        }

        public DribbbleShotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_card_view, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        public void onBindViewHolder(final ViewHolder holder, int position) {
            Shot temp = mDataSet.get(position);
            holder.mName.setText(temp.getId());
            holder.mTitle.setText(temp.getTitle());
            holder.viewCount.setText(temp.getViews_count()+"");

            String imgStr = temp.getImages()[1];
            Uri imgUri = Uri.parse(imgStr);
            if(imgStr.endsWith(".gif")) {
                setUpGif(imgUri,holder.piece);
            }else {
                holder.piece.setImageURI(imgUri);
            }
            if(temp.getUser() != null) {
                Uri avatarUri = Uri.parse(temp.getUser().getAvatarUrl());
                holder.avatar.setImageURI(avatarUri);
            }
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,ShotDetailActivity.class);
                    mContext.startActivity(intent);
                }
            });
        }
        public int getItemCount() {
            if(mDataSet.size()!=0) {
                Log.d("dkl", "数据集的数量：" + mDataSet.size());
            }else{
                Log.d("dkl","数据集的数量：" + mDataSet.size());
            }
            return mDataSet.size();
        }
        public void setUpGif(Uri imgUri,SimpleDraweeView imageView) {
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imgUri)
                    .build();

            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setAutoPlayAnimations(true)
                    .build();
            imageView.setController(controller);
        }
    }

