package com.appsamurai.storylydemo.use_cases;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.appsamurai.storyly.Story;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryGroup;
import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.StorylyListener;
import com.appsamurai.storyly.StorylyView;
import com.appsamurai.storyly.analytics.StorylyEvent;
import com.appsamurai.storylydemo.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ShowActivity extends AppCompatActivity {
    StorylyView storylyView;
    LinearLayout storylyViewHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getSupportActionBar().hide();

        storylyViewHolder = findViewById(R.id.storyly_view_holder);
        storylyView = findViewById(R.id.storyly_view);

        String STORYLY_INSTANCE_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";

        storylyView.setStorylyInit(new StorylyInit(STORYLY_INSTANCE_TOKEN));
        storylyView.setVisibility(View.GONE);

        storylyView.setStorylyListener(new StorylyListener() {
            boolean initialLoad = true;

            @Override
            public void storylyLoaded(@NotNull StorylyView storylyView, @NotNull List<StoryGroup> storyGroupList) {
                //  for not to re-animate already loaded StorylyView
                if (initialLoad && storyGroupList.size() > 0) {
                    initialLoad = false;

                    storylyView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void storylyLoadFailed(@NotNull StorylyView storylyView, @NotNull String s) { }

            @Override
            public void storylyActionClicked(@NotNull StorylyView storylyView, @NotNull Story story) { }

            @Override
            public void storylyStoryShown(@NotNull StorylyView storylyView) { }

            @Override
            public void storylyStoryDismissed(@NotNull StorylyView storylyView) { }

            @Override
            public void storylyUserInteracted(@NotNull StorylyView storylyView, @NotNull StoryGroup storyGroup, @NotNull Story story, @NotNull StoryComponent storyComponent) { }

            @Override
            public void storylyEvent(@NotNull StorylyView storylyView, @NotNull StorylyEvent storylyEvent, @org.jetbrains.annotations.Nullable StoryGroup storyGroup, @org.jetbrains.annotations.Nullable Story story, @org.jetbrains.annotations.Nullable StoryComponent storyComponent) { }
        });
    }
}
