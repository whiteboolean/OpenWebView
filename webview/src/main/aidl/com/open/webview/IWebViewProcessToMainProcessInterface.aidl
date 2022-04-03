// IWebViewProcessToMainProcessInterface.aidl
package com.open.webview;

import com.open.webview.ICallbackFromMainProcessToWebViewProcessInterface;
// Declare any non-default types here with import statements

interface IWebViewProcessToMainProcessInterface {

    void handleWebCommand(String commandName,String jsonParams,in ICallbackFromMainProcessToWebViewProcessInterface callback);
}