package com.open.common.autoservice;

import android.content.Context;

import androidx.fragment.app.Fragment;

/**
 * 依赖倒置原则
 * 接口下沉到common ， 组件之间不相互依赖
 */
public interface IUserService {
    boolean isLogined();
    void login();
}
