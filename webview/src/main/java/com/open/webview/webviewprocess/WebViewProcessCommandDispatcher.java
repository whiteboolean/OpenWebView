package com.open.webview.webviewprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.open.base.BaseApplication;
import com.open.webview.BaseWebView;
import com.open.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.open.webview.IWebViewProcessToMainProcessInterface;
import com.open.webview.mainprocess.MainProcessCommandService;

public class WebViewProcessCommandDispatcher implements ServiceConnection {

    private static WebViewProcessCommandDispatcher sInstance;
    private IWebViewProcessToMainProcessInterface iWebViewProcessToMainProcessInterface;

    private WebViewProcessCommandDispatcher(){

    }

    public static WebViewProcessCommandDispatcher getInstance(){
        if (sInstance == null){
            synchronized (WebViewProcessCommandDispatcher.class){
                sInstance = new WebViewProcessCommandDispatcher();
            }
        }
        return sInstance;
    }

    public void initAidlConnection(){
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessCommandService.class);
        BaseApplication.sApplication.bindService(intent,this, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        iWebViewProcessToMainProcessInterface =
                IWebViewProcessToMainProcessInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        iWebViewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        ServiceConnection.super.onBindingDied(name);
        iWebViewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    public void executeCommand(String commandName, String params, BaseWebView baseWebView){
        if (iWebViewProcessToMainProcessInterface !=null){
            try {
                iWebViewProcessToMainProcessInterface.handleWebCommand(commandName, params, new ICallbackFromMainProcessToWebViewProcessInterface.Stub() {
                    @Override
                    public void onResult(String callbackname, String response) throws RemoteException {
                        baseWebView.handleCallback(callbackname, response);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
