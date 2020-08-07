package com.example.gouwuproject.rcyadapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.gouwuproject.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter {
    private ArrayList<String> list;
    private Context context;

    public HomeAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rcy_home, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String s = list.get(position);
        ViewHolder holder1= (ViewHolder) holder;
        Glide.with(context).load(s).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder1.iv_rcy_home);
        holder1.tv_rcy_home.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv_rcy_home;
        public TextView tv_rcy_home;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv_rcy_home = (ImageView) rootView.findViewById(R.id.iv_rcy_home);
            this.tv_rcy_home = (TextView) rootView.findViewById(R.id.tv_yiqian);
        }

    }
}
