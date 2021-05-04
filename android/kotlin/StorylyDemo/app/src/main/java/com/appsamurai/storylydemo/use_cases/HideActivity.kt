package com.appsamurai.storylydemo.use_cases

import android.animation.LayoutTransition
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.appsamurai.storyly.StoryGroup
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storyly.StorylyListener
import com.appsamurai.storyly.StorylyView
import com.appsamurai.storylydemo.databinding.ActivityHideBinding


class HideActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHideBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHideBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val STORYLY_INSTANCE_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40"

        binding.storylyView.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)

        binding.storylyView.storylyListener = object : StorylyListener {
            var storylyLoaded = false

            override fun storylyLoaded(
                storylyView: StorylyView,
                storyGroupList: List<StoryGroup>
            ) {
                if (storyGroupList.isNotEmpty()) {
                    storylyLoaded = true
                }
            }

            override fun storylyLoadFailed(
                storylyView: StorylyView,
                errorMessage: String
            ) {
                // if cached before not hide
                if (!storylyLoaded) {
                    Handler(mainLooper).postDelayed({
                        removeStorylyView()
                    }, 200)
                }
            }
        }
    }

    private fun removeStorylyView() {
        binding.storylyViewHolder.layoutTransition.addTransitionListener(object: LayoutTransition.TransitionListener{
            override fun startTransition(transition: LayoutTransition?, container: ViewGroup?, view: View?, transitionType: Int) {}

            override fun endTransition(transition: LayoutTransition?, container: ViewGroup?, view: View?, transitionType: Int) {
                if  (binding.storylyView.visibility != View.GONE) {
                    binding.storylyView. visibility = View.GONE
                    binding.storylyViewHolder.layoutTransition.removeTransitionListener(this)
                }
            }
        })
        binding.storylyViewHolder.removeView(binding.storylyViewFrame)
        binding.storylyViewFrame.removeView(binding.storylyView)
    }
}