// ICallbackFromMainProcessToWebViewProcessInterface.aidl
package com.open.webview;

// Declare any non-default types here with import statements

interface ICallbackFromMainProcessToWebViewProcessInterface {

    void onResult(String callbackname,String response);
}