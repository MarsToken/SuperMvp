package com.ly.supermvp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ly.supermvp.R;
import com.ly.supermvp.model.entity.OpenApiPicture;
import com.ly.supermvp.utils.GlideUtil;
import com.ly.supermvp.widget.RatioImageView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <Pre>
 *     美图大全瀑布流适配器
 * </Pre>
 *
 * @author 刘阳
 * @version 1.0
 *          <p/>
 *          Create by 2016/3/21 16:36
 * @see https://github.com/liuyanggithub/SuperMvp
 */
public class PictureGridAdapter extends RecyclerView.Adapter{
    private List<OpenApiPicture> mList;
    private Activity context;

    private OnImageClickListener mOnImageClickListener;

    public PictureGridAdapter(List<OpenApiPicture> mList, Activity context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture_grid, parent,false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ItemViewHolder viewHolder = (ItemViewHolder) holder;
//            PictureBody pictureBody = mList.get(position);
            String url = mList.get(position).getImg();
            GlideUtil.loadImage(context, url, viewHolder.iv_picture);
            viewHolder.tv_title.setText(mList.get(position).getTime());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_picture)
        RatioImageView iv_picture;
        @BindView(R.id.tv_title)
        TextView tv_title;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            iv_picture.setOriginalSize(50, 50);
            iv_picture.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mOnImageClickListener != null) {
                mOnImageClickListener.onImageClick(v, this.getPosition());
            }
        }
    }

    public void setOnImageClickListener(OnImageClickListener onItemClickListener) {
        this.mOnImageClickListener = onItemClickListener;
    }

    /**
     * 点击条目图片接口
     */
    public interface OnImageClickListener {
        void onImageClick(View view, int position);
    }
}
