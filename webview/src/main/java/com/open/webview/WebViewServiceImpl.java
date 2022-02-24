package com.open.webview;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;

import com.google.auto.service.AutoService;
import com.open.common.autoservice.IWebViewService;
import com.open.webview.utils.Constants;

@AutoService(IWebViewService.class)
public class WebViewServiceImpl  implements IWebViewService {
    @Override
    public void startWebViewActivity(Context context, String url, String title
    ,Boolean isShowActionBar) {
        if (context !=null){
            Intent intent = new Intent(context,WebViewActivity.class);
            intent.putExtra(Constants.URL,url);
            intent.putExtra(Constants.TITLE,title);
            intent.putExtra(Constants.IS_SHOW_ACTION_BAR,isShowActionBar);
            context.startActivity(intent);
        }
    }
}
