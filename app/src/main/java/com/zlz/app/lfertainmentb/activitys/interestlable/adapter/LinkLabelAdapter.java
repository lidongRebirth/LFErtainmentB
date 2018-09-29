package com.zlz.app.lfertainmentb.activitys.interestlable.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.activitys.interestlable.bean.Bean;

import java.util.List;

/**
 *
 */

public class LinkLabelAdapter extends RecyclerView.Adapter {

    private Context context;
    private final int TITLE = 100;
    private final int CONTENT = 200;
    private List<Bean> list;

    public LinkLabelAdapter(Context context, List<Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {         //创建viewholder实例

        RecyclerView.ViewHolder holder = null;
        if (viewType == TITLE) {
            holder = new TitleHolder(LayoutInflater.from(context).inflate(R.layout.adapter_activity_label_title, null));
        } else {
            holder = new LabelHolder(LayoutInflater.from(context).inflate(R.layout.adapter_activity_label_content, null));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {            //对子项数据进行填充
        if (getItemViewType(position) == TITLE) {
            ((TitleHolder) holder).setData(position);
        } else {
            ((LabelHolder) holder).setData(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {              //判断类别
        if (list.get(position).getType()==100) {
            return TITLE;
        } else {
            return CONTENT;
        }
    }
    //标题
    class TitleHolder extends RecyclerView.ViewHolder {

        private final TextView adapterTitle;

        public TitleHolder(View inflate) {
            super(inflate);
            adapterTitle = (TextView) inflate.findViewById(R.id.adater_title);
        }

        public void setData(int position){
            adapterTitle.setText(list.get(position).getName());
        }

    }
    //内容
    private class LabelHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private final LinearLayout llContent;

        public LabelHolder(View inflate) {
            super(inflate);
            textView = (TextView) inflate.findViewById(R.id.textViewContent);
            llContent = (LinearLayout) inflate.findViewById(R.id.llContent);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.leftMargin = 50;
            llContent.setLayoutParams(layoutParams);
        }

        public void setData(final int position){
            textView.setText(list.get(position).getName());
            textView.setSelected(list.get(position).getSelected());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView.setSelected(list.get(position).getSelected()==true?false:true);
                    list.get(position).setSelected(list.get(position).getSelected()==true?false:true);
                }
            });
        }

    }
}
