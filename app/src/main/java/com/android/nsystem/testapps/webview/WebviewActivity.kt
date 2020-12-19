package com.android.nsystem.testapps.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.nsystem.testapps.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {

    companion object {

        private const val WEBVIEW_URL = "file:///android_asset/webview.html"
    }

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun openWebView() = with(binding) {
        webview.settings.javaScriptEnabled = true
        webview.loadUrl(WEBVIEW_URL)
    }
}