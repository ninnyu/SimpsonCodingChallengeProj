package com.example.potatopaloozac.simpsoncodingchallengeproj;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import com.example.potatopaloozac.simpsoncodingchallengeproj.adapters.SimpsonAdapter;
import com.example.potatopaloozac.simpsoncodingchallengeproj.adapters.SimpsonIconAdapter;
import com.example.potatopaloozac.simpsoncodingchallengeproj.model.RelatedTopicsItem;
import com.example.potatopaloozac.simpsoncodingchallengeproj.model.Simpson;
import com.example.potatopaloozac.simpsoncodingchallengeproj.utils.ApiService;
import com.example.potatopaloozac.simpsoncodingchallengeproj.utils.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private static final String TAG = "MainActivityTAG";

    private RecyclerView rv_titleList;
    private List<RelatedTopicsItem> topicsItemList;
    private ToggleButton toggleButton;
    private DataListeners.OnSimpsonsListener simpsonsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_titleList = findViewById(R.id.rv_titleList);
        toggleButton = findViewById(R.id.toggleButton);
        topicsItemList = new ArrayList<>();

        simpsonsListener = new DataListeners.OnSimpsonsListener() {
            @Override
            public void showSimpsons(final List<RelatedTopicsItem> topicsItemList) {
                toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            toggleButton.setBackgroundResource(R.drawable.ic_list_24dp);

                            RecyclerView.LayoutManager manager = new GridLayoutManager(MainActivity.this, 3);
                            rv_titleList.setLayoutManager(manager);
                            rv_titleList.setItemAnimator(new DefaultItemAnimator());

                            SimpsonIconAdapter adapter = new SimpsonIconAdapter(MainActivity.this, topicsItemList);
                            rv_titleList.setAdapter(adapter);
                        } else {
                            toggleButton.setBackgroundResource(R.drawable.ic_grid_24dp);
                            showList();
                        }
                    }
                });
                showList();
        }};

        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);

        Call<Simpson> simpsonCall = apiService.getSimpsons("simpsons+characters", "json");
        simpsonCall.enqueue(new Callback<Simpson>() {
            @Override
            public void onResponse(Call<Simpson> call, Response<Simpson> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());
                topicsItemList = response.body().getRelatedTopics();
                simpsonsListener.showSimpsons(topicsItemList);
            }

            @Override
            public void onFailure(Call<Simpson> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void showList() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivity.this);
        rv_titleList.setLayoutManager(manager);
        rv_titleList.setItemAnimator(new DefaultItemAnimator());

        SimpsonAdapter adapter = new SimpsonAdapter(MainActivity.this, topicsItemList);
        rv_titleList.setAdapter(adapter);
    }
}
