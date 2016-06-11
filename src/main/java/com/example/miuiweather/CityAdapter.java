package com.example.miuiweather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kidcats on 2016/6/4.
 */
public class CityAdapter extends AnimRecyclerViewAdapter<CityAdapter.CityViewHolder> {
    private Context mContext;
    private List<String> mCityList;
    private OnRecycleviewItemClick mListener;

    public CityAdapter(Context context, List<String> cityList) {
        this.mContext = context;
        this.mCityList = cityList;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityViewHolder(LayoutInflater.from(mContext).inflate(R.layout.city_recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.itemCity.setText( mCityList.get(position));
        holder.itemCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(holder.itemCity,mCityList.get(position));
            }
        });
        super.onBindViewHolder(holder, position);
    }


    public void setOnItemClickListener(OnRecycleviewItemClick listener){
        this.mListener=listener;
    }

    @Override
    public int getItemCount() {
        return mCityList.size();
    }

    interface OnRecycleviewItemClick {
        void onItemClick(View view, String name);
    }

    class CityViewHolder extends RecyclerView.ViewHolder {
        private TextView itemCity;


        public CityViewHolder(View itemView) {
            super(itemView);
            itemCity = (TextView) itemView.findViewById(R.id.item_city);
        }
    }
}
