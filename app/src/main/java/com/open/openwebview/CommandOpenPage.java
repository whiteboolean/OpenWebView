package com.open.openwebview;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.google.auto.service.AutoService;
import com.open.base.BaseApplication;
import com.open.webview.ICallbackFromMainProcessToWebViewProcessInterface;
import com.open.webview.command.Command;

import java.util.Map;

@AutoService({Command.class})
public class CommandOpenPage  implements Command {
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callback) {
        String targetClass = String.valueOf(parameters.get("target_class"));
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.sApplication, targetClass));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.sApplication.startActivity(intent);
        }
    }

}
