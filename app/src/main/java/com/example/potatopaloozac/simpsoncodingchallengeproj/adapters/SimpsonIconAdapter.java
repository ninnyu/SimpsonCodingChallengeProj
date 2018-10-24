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
import android.widget.ImageView;
import com.example.potatopaloozac.simpsoncodingchallengeproj.R;
import com.example.potatopaloozac.simpsoncodingchallengeproj.SimpsonDetailsFragment;
import com.example.potatopaloozac.simpsoncodingchallengeproj.model.RelatedTopicsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimpsonIconAdapter extends RecyclerView.Adapter<SimpsonIconAdapter.MyViewHolder> {

    private static final String TAG = "SimpsonIconAdapterTAG";

    private List<RelatedTopicsItem> topicsItemList;
    private Context context;

    public SimpsonIconAdapter(Context context, List<RelatedTopicsItem> topicsItemList) {
        this.context = context;
        this.topicsItemList = topicsItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_icon_layout, parent, false);
        return new SimpsonIconAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        String s = topicsItemList.get(position).getIcon().getURL();
        final RelatedTopicsItem item = topicsItemList.get(position);

        Log.d(TAG, "onBindViewHolder: " + s);

        if (!(s.isEmpty() || s == null)) {
            Picasso.get().load(s).placeholder(R.drawable.ic_launcher_foreground).into(holder.iv_icon);
        } else {
            Picasso.get().load(R.drawable.ic_launcher_foreground).into(holder.iv_icon);
        }

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

        private ImageView iv_icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_iconItem);
        }
    }
}
