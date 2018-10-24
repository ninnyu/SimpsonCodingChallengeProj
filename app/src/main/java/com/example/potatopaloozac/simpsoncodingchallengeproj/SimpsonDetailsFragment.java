package com.example.potatopaloozac.simpsoncodingchallengeproj;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.potatopaloozac.simpsoncodingchallengeproj.model.RelatedTopicsItem;
import com.squareup.picasso.Picasso;

public class SimpsonDetailsFragment extends Fragment {

    private static final String TAG = "SimpDetailsFragTAG";

    private RelatedTopicsItem item;
    private ImageView iv_icon;
    private TextView tv_desc;

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach: attached fragment");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();
        item = b.getParcelable("item");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_simpson_details_layout, container, false);

        iv_icon = v.findViewById(R.id.iv_detailsIcon);
        tv_desc = v.findViewById(R.id.tv_detailsDesc);

        String s = item.getIcon().getURL();
        String[] arr = item.getText().split("-");

        if (!s.isEmpty()) {
            Picasso.get().load(s).placeholder(R.drawable.ic_launcher_foreground).into(iv_icon);
        } else {
            Picasso.get().load(R.drawable.ic_launcher_foreground).into(iv_icon);
        }

        tv_desc.setText(arr[1]);

        return v;
    }
}
