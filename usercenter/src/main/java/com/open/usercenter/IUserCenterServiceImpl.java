package com.open.usercenter;

import android.content.Intent;

import com.google.auto.service.AutoService;
import com.open.base.BaseApplication;
import com.open.common.autoservice.IUserService;

@AutoService({IUserService.class})
public class IUserCenterServiceImpl implements
        IUserService {

    @Override
    public boolean isLogined() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.sApplication, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.sApplication.startActivity(intent);
    }
}
