package com.appsamurai.storylydemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appsamurai.storyly.*
import com.appsamurai.storyly.analytics.StorylyEvent
import com.appsamurai.storylydemo.databinding.ActivityEventHandlingBinding

class EventHandlingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventHandlingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventHandlingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val STORYLY_INSTANCE_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40"

        binding.storylyView.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
        binding.storylyView.storylyListener = object : StorylyListener {
            override fun storylyLoaded(
                storylyView: StorylyView,
                storyGroupList: List<StoryGroup>
            ) {
            }

            override fun storylyLoadFailed(
                storylyView: StorylyView,
                errorMessage: String
            ) {
            }

            override fun storylyStoryShown(storylyView: StorylyView) {
            }

            override fun storylyStoryDismissed(storylyView: StorylyView) {
            }

            override fun storylyActionClicked(
                storylyView: StorylyView,
                story: Story
            ) {
                // story.media.actionUrl is important field
            }

            override fun storylyUserInteracted(
                storylyView: StorylyView,
                storyGroup: StoryGroup,
                story: Story,
                storyComponent: StoryComponent
            ) {
            }

            override fun storylyEvent(
                storylyView: StorylyView,
                event: StorylyEvent,
                storyGroup: StoryGroup?,
                story: Story?,
                storyComponent: StoryComponent?
            ) {
            }
        }
    }
}