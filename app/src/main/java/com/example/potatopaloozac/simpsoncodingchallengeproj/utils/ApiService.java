package com.example.potatopaloozac.simpsoncodingchallengeproj.utils;

import com.example.potatopaloozac.simpsoncodingchallengeproj.model.GameOfThrones;
import com.example.potatopaloozac.simpsoncodingchallengeproj.model.RelatedTopicsItem;
import com.example.potatopaloozac.simpsoncodingchallengeproj.model.Simpson;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    // http://api.duckduckgo.com/?q=simpsons+characters&format=json
    @GET("/")
    Call<Simpson> getSimpsons(@Query(value = "q", encoded = true) String query,
                                        @Query("format") String format);

    // http://api.duckduckgo.com/?q=game+of+thrones+characters&format=json
    @GET("/")
    Call<GameOfThrones> getGame(@Query("q") String query,
                                @Query("format") String format);
}
