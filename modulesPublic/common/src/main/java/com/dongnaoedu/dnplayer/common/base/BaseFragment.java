package com.dongnaoedu.dnplayer.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dongnaoedu.dnplayer.common.mvp.BasePresenter;
import com.dongnaoedu.dnplayer.common.mvp.IView;
import com.dongnaoedu.dnplayer.common.mvvm.BaseViewModel;
import com.dongnaoedu.dnplayer.common.mvvm.annotation.VM;
import com.dongnaoedu.dnplayer.common.utils.DialogUtil;
import com.dongnaoedu.dnplayer.common.utils.ToastUtil;
import com.dongnaoedu.dnplayer.common.widget.LoadingDialog;
import com.dongnaoedu.dnplayer.libbase.utils.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

public class BaseFragment<B extends ViewBinding> extends Fragment implements IView {

    protected B binding;
    protected LoadingDialog loadingDialog;
    protected Activity mContext;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mContext = getActivity();
        View rootView = inflectRootView();
        //inflectPresenters();
        //inflectViewModels();
        initViews();
        loadData();
        initLiveData();
        return rootView;
    }

    private View inflectRootView() {
        View rootView = null;
        // 使用反射获取泛型类型参数
        Class<?> clz1 = ReflectUtil.analysisClassInfo(this);
        if (clz1 != ViewBinding.class && ViewBinding.class.isAssignableFrom(clz1)) {
            try {
                // 通过反射获取对应的inflate方法
                Method method = clz1.getDeclaredMethod("inflate", LayoutInflater.class);
                method.setAccessible(true);
                binding = (B) method.invoke(null, getLayoutInflater());
                if (binding != null) {
                    rootView = binding.getRoot();
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        if (rootView == null) {
            throw new RuntimeException(
                    "RootView can not be null !");
        }
        return rootView;
    }

    /**
     * 通过反射创建ViewModel，并且调用attach
     */
    @Deprecated
    private void inflectViewModels() {
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                VM inject = field.getAnnotation(VM.class);
                if (inject != null) {
                    field.setAccessible(true);
                    // viewModel = new ViewModelProvider(this).get(MusicViewModel.class);
                    Class<? extends BaseViewModel> clz = (Class<? extends BaseViewModel>) field.getType();
                    BaseViewModel viewModel = new ViewModelProvider(this).get(clz);
                    field.set(this, viewModel);
                    viewModel.attach(this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 不想每次都去attach，而且还有可能是多个presenter：
     * 使用反射来获取所有的presenter，并且attach！
     * 疑问：反射对性能具有一定的影响，有没有更好的方法？*(使用Hilt进行依赖注入，只是写起来稍微麻烦了一些)
     */
    private void inflectPresenters() {
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                Inject inject = field.getAnnotation(Inject.class);
                if (inject != null) {
                    field.setAccessible(true);
                    Object fieldObj = field.get(this);
                    if (fieldObj instanceof BasePresenter) {
                        BasePresenter presenter = (BasePresenter) fieldObj;
                        presenter.attach(this);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void loadData() {

    }

    protected void initViews() {

    }

    protected void initLiveData() {

    }

    @Override
    public void showLoading(String msg) {
        if (TextUtils.isEmpty(msg)) msg = "加载中...";
        if (loadingDialog != null && loadingDialog.isShowing()
                && TextUtils.equals(loadingDialog.getMessage(), msg)) {
            return;
        }
        if (loadingDialog == null) {
            loadingDialog = DialogUtil.showLoading(mContext, msg);
        } else {
            loadingDialog.setType(LoadingDialog.TYPE_LOADING);
            loadingDialog.setMessage(msg);
            if (!mContext.isDestroyed() && !loadingDialog.isShowing()) {
                loadingDialog.show();
            }
        }
    }

    @Override
    public void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void showLoadingLayout() {

    }

    @Override
    public void showSuccessLayout() {

    }

    @Override
    public void showErrorLayout() {

    }

    @Override
    public void showEmptyLayout() {

    }
}
