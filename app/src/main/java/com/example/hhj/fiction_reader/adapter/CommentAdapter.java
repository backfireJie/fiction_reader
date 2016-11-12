package com.example.hhj.fiction_reader.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hhj.fiction_reader.R;
import com.example.hhj.fiction_reader.bean.CommentBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by XX on 2016/10/10.
 */
public class CommentAdapter extends  RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    private List<CommentBean> mDatas;
    private LayoutInflater mInflater;
    private Context mContext;

    private MyOnCommentListener mListener;
    public void setMyOnCommentListener(MyOnCommentListener mListener){
        this.mListener = mListener;
    }


    public CommentAdapter(List<CommentBean> mDatas){
        this.mDatas = mDatas;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        View view = mInflater.inflate(R.layout.template_comment,parent,false);

        return new ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentBean commentBean = getData(position);
        holder.sdvComment.setImageURI(Uri.parse(commentBean.getImgUrl()));
        holder.tvCommentTitle.setText(commentBean.getTitle());
        holder.tvCommentBrief.setText(commentBean.getBrief());
        holder.tvCommentTime.setText(commentBean.getTime());

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


   //
    public CommentBean getData(int position){
        return mDatas.get(position);
    }
    //增加数据
    public void addData(List<CommentBean> datas){
        addData(0,datas);
    }
    public void addData(int positon,List<CommentBean> datas){
        if(datas != null && datas.size() > 0){
            mDatas.addAll(datas);
            notifyItemInserted(positon);
        }
    }
    //在头部增加数据

    //获取数据大小
    public int getDataSize(){
        return mDatas.size();
    }

   public void clearDatas(){
        mDatas.clear();
        notifyItemRemoved(mDatas.size());
    }







    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private SimpleDraweeView sdvComment;
        private TextView tvCommentTitle;
        private TextView tvCommentBrief;
        private TextView tvCommentTime;
        private MyOnCommentListener mListener;

        public ViewHolder(View itemView,MyOnCommentListener mListener) {
            super(itemView);
            this.mListener = mListener;
            itemView.setOnClickListener(this);
            sdvComment =  (SimpleDraweeView) itemView.findViewById(R.id.sdv_comment);
            tvCommentTitle = (TextView) itemView.findViewById(R.id.tv_comment_title);
            tvCommentBrief = (TextView) itemView.findViewById(R.id.tv_comment_brief);
            tvCommentTime =  (TextView) itemView.findViewById(R.id.tv_comment_time);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onItemClick(v,getLayoutPosition());
            }
        }
    }

    public interface MyOnCommentListener{
        void onItemClick(View view ,int position);
    }


}
