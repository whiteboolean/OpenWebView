package com.open.webview.command;

import com.open.webview.ICallbackFromMainProcessToWebViewProcessInterface;

import java.util.Map;

public interface Command {
    String name();
    void execute(Map parameters, ICallbackFromMainProcessToWebViewProcessInterface callback);
}
