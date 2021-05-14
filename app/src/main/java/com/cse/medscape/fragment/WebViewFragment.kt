package com.cse.medscape.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.cse.medscape.R
import com.cse.medscape.databinding.FragmentWebBinding

class WebViewFragment : Fragment(R.layout.fragment_web) {

    private lateinit var binding: FragmentWebBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWebBinding.bind(view)

        val bundle = arguments
        if (bundle != null) {
            val link = bundle.getString("link")?.toString()
            link?.let {
                binding.progress.visibility = View.VISIBLE
                binding.webView.apply {
                    webViewClient = WebViewClient()
                    loadUrl(it)
                    settings.javaScriptEnabled = true
                    canGoBack()
                    webChromeClient = object : WebChromeClient() {
                        override fun onProgressChanged(view: WebView?, newProgress: Int) {
                            super.onProgressChanged(view, newProgress)
                            if (newProgress == 100) {
                                binding.progress.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        } else {
            binding.textView3.visibility = View.VISIBLE
            binding.webView.visibility = View.GONE
        }
    }

}