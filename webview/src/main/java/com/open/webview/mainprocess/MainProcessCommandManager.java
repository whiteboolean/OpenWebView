package com.open.webview.mainprocess;

import android.os.RemoteException;

import com.google.gson.Gson;
import com.open.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.open.webview.command.Command;
import com.open.webview.IWebViewProcessToMainProcessInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class MainProcessCommandManager extends IWebViewProcessToMainProcessInterface.Stub {
    private static final String TAG = "MainProcessCommandManag";
    private static MainProcessCommandManager sInstance;

    private static HashMap<String, Command> mCommands = new HashMap<>();

    public static MainProcessCommandManager getInstance() {
        if (sInstance == null) {
            synchronized (MainProcessCommandManager.class) {
                sInstance = new MainProcessCommandManager();
            }
        }
        return sInstance;
    }

    private MainProcessCommandManager(){
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class);
        for (Command command : serviceLoader) {
            if (!mCommands.containsKey(command.name())){
                mCommands.put(command.name(),command);
            }
        }
    }

    public void executeCommand(String commandName, Map params,ICallbackFromMainProcessToWebViewProcessInterface callback) {
        mCommands.get(commandName).execute(params,callback);
    }



    @Override
    public void handleWebCommand(String commandName, String jsonParams, ICallbackFromMainProcessToWebViewProcessInterface callback) throws RemoteException {
        executeCommand(commandName, new Gson().fromJson(jsonParams, Map.class),callback);
    }
}
