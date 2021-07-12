package com.appsamurai.storylydemo

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appsamurai.storyly.StorylyInit
import com.appsamurai.storylydemo.databinding.ActivityCustomThemeBinding

class CustomThemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomThemeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val STORYLY_INSTANCE_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhY2NfaWQiOjc2MCwiYXBwX2lkIjo0MDUsImluc19pZCI6NDA0fQ.1AkqOy_lsiownTBNhVOUKc91uc9fDcAxfQZtpm3nj40"

        binding.storylyViewDefault.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)

        binding.storylyCustomTheme.storylyInit = StorylyInit(STORYLY_INSTANCE_TOKEN)

        // storyly_custom_theme.setStoryGroupTextColor(Color.parseColor("#f03932"))

        // storyly_custom_theme.setStoryGroupIconBackgroundColor( Color.parseColor("#4CAF50"))

        // val seenBorder = arrayOf(Color.parseColor("#C5ACA5"), Color.parseColor("#000000"))
        // storyly_custom_theme.setStoryGroupIconBorderColorSeen(seenBorder)

        // val notSeenBorder = arrayOf(Color.parseColor("#FB3200"), Color.parseColor("#FFEB3B"))
        // storyly_custom_theme.setStoryGroupIconBorderColorNotSeen(notSeenBorder)

        // storyly_custom_theme.setStoryGroupPinIconColor(Color.parseColor("#3F51B5"))

        // storyly_custom_theme.setStoryItemTextColor(Color.parseColor("#FF0057"))

        // val borderColor = arrayOf(Color.parseColor("#FB3200"), Color.parseColor("#FFEB3B"))
        // storyly_custom_theme.setStoryItemIconBorderColor(borderColor)

        // val progressBar = arrayOf(Color.parseColor("#FB3200"), Color.parseColor("#FFEB3B"))
        // storyly_custom_theme.setStoryItemProgressBarColor(progressBar)

    }
}