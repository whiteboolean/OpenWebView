package com.open.webview.webviewprocess.webchromeclient;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.open.webview.WebViewCallBack;

public class OpenWebViewChromeClient extends WebChromeClient {
    private static final String TAG = "OpenWebViewChromeClient";
    private WebViewCallBack webViewCallBack;
    public OpenWebViewChromeClient(WebViewCallBack webViewCallBack) {
        this.webViewCallBack = webViewCallBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if(webViewCallBack != null) {
            webViewCallBack.updateTitle(title);
        } else {
            Log.e(TAG, "WebViewCallBack is null.");
        }
        super.onReceivedTitle(view, title);
    }


    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.e(TAG,"consoleMessage:"+consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
