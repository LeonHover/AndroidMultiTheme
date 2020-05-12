package io.github.leonhover.theme.samples;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.leonhover.theme.MultiTheme;
import io.github.leonhover.theme.base.widget.CoverImageView;

/**
 * Created by wangzongliang on 2016/11/8.
 */

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder> {

    private static final String TAG = "ThemeAdapter";

    private Context context;

    private List<ThemeInfo> themeInfoList;

    public ThemeAdapter(Context context) {
        this.context = context;
    }

    public void setThemeInfoList(List<ThemeInfo> themeInfoList) {
        this.themeInfoList = themeInfoList;
    }

    @Override
    public ThemeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_theme_adpater, null);
        return new ThemeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ThemeViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder position=" + position);
        ThemeInfo themeInfo = this.themeInfoList.get(position);
        holder.coverImageView.setImageResource(themeInfo.imgRes);
        holder.themeTitle.setText(themeInfo.titleRes);
        holder.themeDescription.setText(themeInfo.descriptionRes);
    }

    @Override
    public void onViewAttachedToWindow(ThemeViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        MultiTheme.applyTheme(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return this.themeInfoList == null ? 0 : this.themeInfoList.size();
    }

    protected static class ThemeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CoverImageView coverImageView;
        public TextView themeTitle;
        public TextView themeDescription;

        public ThemeViewHolder(View itemView) {
            super(itemView);
            coverImageView = (CoverImageView) itemView.findViewById(R.id.theme_img);
            themeTitle = (TextView) itemView.findViewById(R.id.theme_title);
            themeDescription = (TextView) itemView.findViewById(R.id.theme_description);
            coverImageView.setOnClickListener(this);
            themeTitle.setOnClickListener(this);
            themeDescription.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            //按照列表顺序的主题Index= position
            MultiTheme.setAppTheme(this.getAdapterPosition());
        }
    }


}
