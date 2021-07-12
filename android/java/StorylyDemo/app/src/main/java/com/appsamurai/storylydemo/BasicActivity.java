package com.appsamurai.storylydemo;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.appsamurai.storyly.StorylyInit;
import com.appsamurai.storyly.StorylyView;

public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout storylyViewHolder = findViewById(R.id.storyly_view_holder);

        String STORYLY_INSTANCE_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40";

        StorylyView storyly_view = findViewById(R.id.storyly_view);
        storyly_view.setStorylyInit(new StorylyInit(STORYLY_INSTANCE_TOKEN));

        StorylyView storylyView = new StorylyView(this);
        storylyView.setStorylyInit(new StorylyInit(STORYLY_INSTANCE_TOKEN));
        storylyViewHolder.addView(storylyView);
    }
}
