package com.appsamurai.storylydemo.use_cases

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.appsamurai.storyly.StoryGroup
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyListener
import com.appsamurai.storyly.StorylyView
import com.appsamurai.storylydemo.databinding.ActivityShowBinding


class ShowActivity: AppCompatActivity() {
    private lateinit var binding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val STORYLY_INSTANCE_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40"

        binding.storylyView.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)
        binding.storylyView.visibility = View.GONE

        binding.storylyView.storylyListener = object : StorylyListener {
            var initialLoad = true

            override fun storylyLoaded(storylyView: StorylyView, storyGroupList: List<StoryGroup>) {
                //  for not to re-animate already loaded StorylyView
                if (initialLoad && storyGroupList.isNotEmpty()) {
                    initialLoad = false

                    storylyView.visibility = View.VISIBLE
                }
            }
        }
    }
}