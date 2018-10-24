package com.example.potatopaloozac.simpsoncodingchallengeproj.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.potatopaloozac.simpsoncodingchallengeproj.R;
import com.example.potatopaloozac.simpsoncodingchallengeproj.SimpsonDetailsFragment;
import com.example.potatopaloozac.simpsoncodingchallengeproj.model.RelatedTopicsItem;

import java.util.List;
import java.util.zip.Inflater;

public class SimpsonAdapter extends RecyclerView.Adapter<SimpsonAdapter.MyViewHolder> {

    private static final String TAG = "SimpsonAdapterTAG";

    private List<RelatedTopicsItem> topicsItemList;
    private Context context;

    public SimpsonAdapter(Context context, List<RelatedTopicsItem> topicsItemList) {
        this.context = context;
        this.topicsItemList = topicsItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        String[] arr = topicsItemList.get(position).getText().split("-");
        final RelatedTopicsItem item = topicsItemList.get(position);

        holder.tv_title.setText(arr[0]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Clicked on item");
                SimpsonDetailsFragment fragment = new SimpsonDetailsFragment();

                Bundle b = new Bundle();
                b.putParcelable("item", item);
                fragment.setArguments(b);

                ((Activity) context).getFragmentManager().beginTransaction().replace(R.id.main_activity, fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return topicsItemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_titleItem);
        }
    }
}
