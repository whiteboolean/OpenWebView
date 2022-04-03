package com.open.openwebview;

import com.kingja.loadsir.core.LoadSir;
import com.open.base.BaseApplication;
import com.open.base.loadsir.CustomCallback;
import com.open.base.loadsir.ErrorCallback;
import com.open.base.loadsir.LoadingCallback;
import com.open.base.loadsir.TimeoutCallback;

import java.sql.Time;

public class WebViewApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 初始化loadSir
         */
        LoadSir.beginBuilder()
                .addCallback(new TimeoutCallback())
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
