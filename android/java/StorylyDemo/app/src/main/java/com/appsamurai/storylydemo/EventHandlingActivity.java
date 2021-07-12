package com.appsamurai.storylydemo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.appsamurai.storyly.Story;
import com.appsamurai.storyly.StoryComponent;
import com.appsamurai.storyly.StoryGroup;
import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.StorylyListener;
import com.appsamurai.storyly.StorylyView;
import com.appsamurai.storyly.analytics.StorylyEvent;

import java.util.List;

public class EventHandlingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_handling);

        StorylyView storyly_view = findViewById(R.id.storyly_view);

        String STORYLY_INSTANCE_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";

        storyly_view.setStorylyInit(new StorylyInit(STORYLY_INSTANCE_TOKEN));
        storyly_view.setStorylyListener(new StorylyListener() {
            @Override
            public void storylyActionClicked(@NonNull StorylyView storylyView,
                                                @NonNull Story story) {
                // story.media.actionUrl is important field
            }

            @Override
            public void storylyLoaded(@NonNull StorylyView storylyView,
                                      @NonNull List<StoryGroup> storyGroupList) {

            }

            @Override
            public void storylyLoadFailed(@NonNull StorylyView storylyView,
                                          @NonNull String errorMessage) {

            }

            @Override
            public void storylyStoryShown(@NonNull StorylyView storylyView) {

            }

            @Override
            public void storylyStoryDismissed(@NonNull StorylyView storylyView) {

            }

            @Override
            public void storylyUserInteracted(@NonNull StorylyView storylyView,
                                              @NonNull StoryGroup storyGroup,
                                              @NonNull Story story,
                                              @NonNull StoryComponent storyComponent) {

            }

            @Override
            public void storylyEvent(@NonNull StorylyView storylyView,
                                     @NonNull StorylyEvent storylyEvent,
                                     @Nullable StoryGroup storyGroup,
                                     @Nullable Story story,
                                     @Nullable StoryComponent storyComponent) {
            }
        });
    }
}
