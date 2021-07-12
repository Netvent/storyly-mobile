package com.appsamurai.storylydemo

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.View
import com.appsamurai.storyly.external.StorylyLoadingView
import com.appsamurai.storylydemo.databinding.CustomLoadingViewBinding

@SuppressLint("ViewConstructor")
class CustomLoadingView(view: View, context: Context) : StorylyLoadingView(context) {
    private val binding: CustomLoadingViewBinding = CustomLoadingViewBinding.bind(view)

    init {
        addView(view)
    }

    override fun show() {
        Log.d("[Storyly]", "LoadingView:show")
        binding.externalLoading.visibility = View.VISIBLE
    }

    override fun hide() {
        Log.d("[Storyly]", "LoadingView:hide")
        binding.externalLoading.visibility = View.GONE
    }
}