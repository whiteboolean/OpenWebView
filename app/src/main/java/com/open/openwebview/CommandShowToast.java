package com.open.openwebview;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.open.base.BaseApplication;
import com.open.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.open.webview.command.Command;

import java.util.Map;

@AutoService({Command.class})
public class CommandShowToast implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callback) {
        new Handler(Looper.getMainLooper())
                .post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BaseApplication.sApplication, String.valueOf(parameters.get("message")), Toast.LENGTH_LONG).show();
                    }
                });
    }


}
