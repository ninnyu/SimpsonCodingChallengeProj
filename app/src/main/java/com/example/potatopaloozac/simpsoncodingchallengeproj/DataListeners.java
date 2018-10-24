package com.example.potatopaloozac.simpsoncodingchallengeproj;

import com.example.potatopaloozac.simpsoncodingchallengeproj.model.RelatedTopicsItem;

import java.util.List;

public interface DataListeners {

    interface OnSimpsonsListener {
        void showSimpsons(List<RelatedTopicsItem> topicsItemList);
    }
}
