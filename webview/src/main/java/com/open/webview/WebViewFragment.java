package com.open.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.open.base.loadsir.ErrorCallback;
import com.open.base.loadsir.LoadingCallback;
import com.open.webview.databinding.FragmentWebviewBinding;
import com.open.webview.utils.Constants;

public class WebViewFragment extends Fragment implements WebViewCallBack {

    private static final String TAG = "WebViewFragment";

    private String mUrl;
    private boolean mCanNativeRefresh;
    private FragmentWebviewBinding binding;
    private LoadService mLoadSir;
    private boolean mIsError;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public static WebViewFragment newInstance(String url, boolean canNativeRefresh) {
        WebViewFragment fragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.URL, url);
        bundle.putBoolean(Constants.can_native_refresh, canNativeRefresh);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mUrl = bundle.getString(Constants.URL);
            mCanNativeRefresh = bundle.getBoolean(Constants.can_native_refresh, false);
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false);
        binding.webView.registerWebViewCallBack(this);
        binding.webView.loadUrl(mUrl);
        Log.d(TAG, "URL：" + mUrl);
        mLoadSir = LoadSir.getDefault()
                .register(binding.srl,
                        (Callback.OnReloadListener) v -> {
                            mLoadSir.showCallback(LoadingCallback.class);
                            binding.webView.reload();
                        });
        binding.srl.setEnableRefresh(mCanNativeRefresh);
        binding.srl.setEnableLoadMore(false);
        return mLoadSir.getLoadLayout();
    }

    @Override
    public void pageStarted(String url) {
        if (mLoadSir != null) {
            mLoadSir.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void pageFinished(String url) {
        if (mIsError) {
            binding.srl.setEnableRefresh(true);
        } else {
            binding.srl.setEnableRefresh(mCanNativeRefresh);
        }
        Log.d(TAG, "pageFinished");
        binding.srl.finishRefresh();

        if (mLoadSir != null) {
            if (mIsError) {
                mLoadSir.showCallback(ErrorCallback.class);
            } else {
                mLoadSir.showSuccess();
            }
        }
        mIsError = false;
    }

    @Override
    public void onError() {
        Log.e(TAG, "onError ");
        mIsError = true;
        binding.srl.setEnableRefresh(true);
        binding.srl.finishRefresh();
        mLoadSir.showCallback(ErrorCallback.class);
    }

    @Override
    public void updateTitle(String title) {
        if (getActivity() instanceof WebViewActivity) {
            ((WebViewActivity) getActivity()).updateTitle(title);
        }
    }
}
