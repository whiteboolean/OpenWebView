package com.open.common.autoservice;

import android.content.Context;
import android.webkit.WebViewFragment;

import androidx.fragment.app.Fragment;

/**
 * 依赖倒置原则
 * 接口下沉到common ， 组件之间不相互依赖
 */
public interface IWebViewService {
    void startWebViewActivity(Context context, String url, String title,Boolean isShowActionBar);
    Fragment getWebViewFragment(String url,boolean canRefresh);
    void startDemoHtml(Context context);

}
