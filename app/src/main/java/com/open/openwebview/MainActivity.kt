package com.open.openwebview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.open.base.autoservice.BaseServiceLoader
import com.open.common.autoservice.IWebViewService

/**
 * webView:
 * 1.高可用 ： webView出了问题不影响主进程
 * 2.高扩展 ： Html页面经常和native通信，增加功能方便
 *
 * 一些情况下不能用webView： 对流畅性要求很高的模块
 *
 * 顶部 ActionBar
 * 下面 WebView
 * 四化： 模块化+ 层次化+组件化+ 控件化
 *
 *
 * cc: 无接口下沉，字符串匹配
 *
 *
 *      --------------------------------------
 *     |                App                 |
 *     --------------------------------------
 *     |  组件1  | 组件2 | 组件3 | 组件WebView |
 *     --------------------------------------
 *     |                Common              |
 *     --------------------------------------
 *     |                NetWork             |
 *     --------------------------------------
 *     |                Base                |
 *     --------------------------------------
 *
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btnWebView)
        button.setOnClickListener {
            val webViewService  = BaseServiceLoader.load(IWebViewService::class.java)
//            webViewService?.startWebViewActivity(this, "https://www.baidu.com","百度",true)
            webViewService?.startDemoHtml(this)
        }
    }
}