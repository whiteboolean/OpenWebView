package com.open.webview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.open.webview.databinding.ActivityWebviewBinding
import com.open.webview.utils.Constants

class WebViewActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityWebviewBinding
    private var url: String? = null
    var title: String? = null
    var isShowTitle: Boolean = false

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        intent?.apply {
            url = getStringExtra(Constants.URL)
            title = getStringExtra(Constants.TITLE)
            isShowTitle = getBooleanExtra(Constants.IS_SHOW_ACTION_BAR, false)
        }

        val beginTransaction = supportFragmentManager.beginTransaction()
        val fragment = WebViewFragment.newInstance(url, true)
        beginTransaction.replace(R.id.webView, fragment).commit()

        if (isShowTitle) viewBinding.titleBar.visibility =
            View.VISIBLE else viewBinding.titleBar.visibility = View.GONE

        viewBinding.tvTitle.text = title


    }

    public fun updateTitle(title:String?){
        viewBinding.tvTitle.text = title
    }
}