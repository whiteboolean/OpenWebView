package com.open.openwebview;

import android.os.RemoteException;
import android.util.Log;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.open.base.autoservice.BaseServiceLoader;
import com.open.common.autoservice.IUserService;
import com.open.common.eventbus.LoginEvent;
import com.open.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.open.webview.command.Command;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

@AutoService({Command.class})
public class CommandLogin implements Command {

    IUserService iUserService = BaseServiceLoader.load(IUserService.class);
    ICallbackFromMainProcessToWebViewProcessInterface callback;
    String callbacknameFromNativeJs;
    public CommandLogin(){
        EventBus.getDefault().register(this);
    }
    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callback) {
        Log.d("commandLogin",new Gson().toJson(parameters));
        if (iUserService!=null && !iUserService.isLogined()){
            iUserService.login();
            this.callback = callback;
            this.callbacknameFromNativeJs = (String) parameters.get("callbackname");
        }

    }


    @Subscribe
    public void onMessageEvent(LoginEvent event){
        Log.d("CommandLogin",event.userName);
        HashMap map = new HashMap();
        map.put("accountName",event.userName);
        if (this.callback!=null ){
            try {
                this.callback.onResult(callbacknameFromNativeJs,
                        new Gson().toJson(map));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
