package com.open.webview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.open.webview.bean.JsParam;
import com.open.webview.webviewprocess.WebViewProcessCommandDispatcher;
import com.open.webview.webviewprocess.settings.WebViewDefaultSettings;
import com.open.webview.webviewprocess.webchromeclient.OpenWebViewChromeClient;
import com.open.webview.webviewprocess.webviewclient.OpenWebViewClient;

import java.util.Map;

public class BaseWebView extends WebView {
    private static final String TAG = "BaseWebView";

    public BaseWebView(@NonNull Context context) {
        super(context);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    public void init() {
        WebViewProcessCommandDispatcher.getInstance().initAidlConnection();
        WebViewDefaultSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "openwebView");
    }


    public void registerWebViewCallBack(WebViewCallBack webViewCallBack) {
        setWebViewClient(new OpenWebViewClient(webViewCallBack));
        setWebChromeClient(new OpenWebViewChromeClient(webViewCallBack));
    }


    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        Log.i(TAG, jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            final JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
            if (jsParamObject != null) {
                WebViewProcessCommandDispatcher.getInstance().executeCommand(jsParamObject.name, new Gson().toJson(jsParamObject.param),this);
            }
        }
    }

    public void handleCallback(String callbackname, String response) {
        if(!TextUtils.isEmpty(callbackname) && !TextUtils.isEmpty(response)){
            post(new Runnable() {
                @Override
                public void run() {
                    String jscode = "javascript:openjs.callback('" + callbackname + "'," + response + ")";
                    Log.e("xxxxxx", jscode);
                    evaluateJavascript(jscode, null);
                }
            });
        }
    }
}





