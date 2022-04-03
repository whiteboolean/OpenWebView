package com.open.webview.webviewprocess.webviewclient;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.open.webview.WebViewCallBack;

/**
 *
 */
public class OpenWebViewClient  extends WebViewClient {
    private static final String TAG = "OpenWebViewClient";

    private WebViewCallBack mWebViewCallBack;

    public OpenWebViewClient(WebViewCallBack callBack) {
        this.mWebViewCallBack = callBack;

    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
      if (mWebViewCallBack!=null){
          mWebViewCallBack.pageStarted(url);
      }else{
          Log.d(TAG,"--- webViewCallBack is null ---");
      }
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (mWebViewCallBack!=null){
            mWebViewCallBack.pageFinished(url);
        }else{
            Log.e(TAG,"--- webViewCallBack is null --- ");
        }
        super.onPageFinished(view, url);
    }


    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        if (mWebViewCallBack!=null){
            mWebViewCallBack.onError();
        }else{
            Log.e(TAG,"null");
        }
        super.onReceivedError(view, request, error);
    }
}
