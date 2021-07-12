package com.appsamurai.storylydemo.use_cases;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

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
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HideActivity extends AppCompatActivity {
    StorylyView storylyView;
    LinearLayout storylyViewHolder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide);
        getSupportActionBar().hide();

        storylyView = findViewById(R.id.storyly_view);
        storylyViewHolder = findViewById(R.id.storyly_view_holder);

        String STORYLY_INSTANCE_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";

        storylyView.setStorylyInit(new StorylyInit(STORYLY_INSTANCE_TOKEN));

        storylyView.setStorylyListener(new StorylyListener() {
            boolean storylyLoaded = false;

            @Override
            public void storylyLoaded(@NotNull StorylyView storylyView, @NotNull List<StoryGroup> list) {
                if (list.size() > 0) {
                    storylyLoaded = true;
                }
            }

            @Override
            public void storylyLoadFailed(@NotNull StorylyView storylyView, @NotNull String errorMessage) {
                // if cached before not hide
                if (!storylyLoaded) {
                    storylyView.setVisibility(View.GONE);
                }
            }

            @Override
            public void storylyActionClicked(@NotNull StorylyView storylyView, @NotNull Story story) { }

            @Override
            public void storylyStoryShown(@NotNull StorylyView storylyView) { }

            @Override
            public void storylyStoryDismissed(@NotNull StorylyView storylyView) { }

            @Override
            public void storylyUserInteracted(@NotNull StorylyView storylyView, @NotNull StoryGroup storyGroup, @NotNull Story story, @NotNull StoryComponent storyComponent) { }

            @Override
            public void storylyEvent(@NotNull StorylyView storylyView, @NotNull StorylyEvent storylyEvent, @Nullable StoryGroup storyGroup, @Nullable Story story, @Nullable StoryComponent storyComponent) { }
        });
    }
}
