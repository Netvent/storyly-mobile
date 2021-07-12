package com.appsamurai.storylydemo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.StorylyView;

public class CustomThemeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_theme);

        String STORYLY_INSTANCE_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";

        StorylyView storyly_view_default = findViewById(R.id.storyly_view_default);
        storyly_view_default.setStorylyInit(new StorylyInit(STORYLY_INSTANCE_TOKEN));


        StorylyView storyly_custom_theme = findViewById(R.id.storyly_custom_theme);
        storyly_custom_theme.setStorylyInit(new StorylyInit(STORYLY_INSTANCE_TOKEN));

        // storyly_custom_theme.setStoryGroupTextColor(Color.parseColor("#f03932"));

        // storyly_custom_theme.setStoryGroupIconBackgroundColor(Color.parseColor("#4CAF50"));

        // Integer[] seenBorder = {Color.parseColor("#C5ACA5"), Color.parseColor("#000000")};
        // storyly_custom_theme.setStoryGroupIconBorderColorSeen(seenBorder);

        // Integer[] notSeenBorder = {Color.parseColor("#FB3200"), Color.parseColor("#FFEB3B")};
        // storyly_custom_theme.setStoryGroupIconBorderColorNotSeen(notSeenBorder);

        // storyly_custom_theme.setStoryGroupPinIconColor(Color.parseColor("#3F51B5"));

        // storyly_custom_theme.setStoryItemTextColor(Color.parseColor("#FF0057"));

        // Integer[] borderColor = {Color.parseColor("#FB3200"), Color.parseColor("#FFEB3B")};
        // storyly_custom_theme.setStoryItemIconBorderColor(borderColor);

        // Integer[] progressBar = {Color.parseColor("#FB3200"), Color.parseColor("#FFEB3B")};
        // storyly_custom_theme.setStoryItemProgressBarColor(progressBar);
    }
}
