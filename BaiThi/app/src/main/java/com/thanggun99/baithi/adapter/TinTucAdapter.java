package com.thanggun99.baithi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thanggun99.baithi.R;
import com.thanggun99.baithi.model.entity.TinTuc;

import java.util.ArrayList;


public class TinTucAdapter extends RecyclerView.Adapter<TinTucAdapter.ViewHolder> {
    private ArrayList<TinTuc> tinTucList;
    private Context context;
    private OnClickTinTucListener onClickTinTucListener;


    public TinTucAdapter(Context context, ArrayList<TinTuc> tinTucList) {
        this.context = context;
        this.tinTucList = tinTucList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tin_tuc, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TinTuc tinTuc = tinTucList.get(position);

        holder.tvTitle.setText(tinTuc.getTitle());
        holder.tvMoTa.setText(tinTuc.getMoTa());

        Glide.with(context)
                .load(tinTuc.getHinhAnh())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.ivHinhAnh);
    }

    @Override
    public int getItemCount() {
        return (tinTucList != null) ? tinTucList.size() : 0;
    }

    public void setOnClickTinTucListener(OnClickTinTucListener onClickTinTucListener) {
        this.onClickTinTucListener = onClickTinTucListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHinhAnh;
        TextView tvTitle;
        TextView tvMoTa;

        public ViewHolder(View itemView) {
            super(itemView);

            ivHinhAnh = (ImageView) itemView.findViewById(R.id.iv_hinh);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvMoTa = (TextView) itemView.findViewById(R.id.tv_mo_ta);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickTinTucListener.onClickTinTuc(tinTucList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnClickTinTucListener {
        void onClickTinTuc(TinTuc tinTuc);

    }

}
