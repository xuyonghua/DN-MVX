package com.dongnaoedu.dnplayer.common.base;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dongnaoedu.dnplayer.common.R;
import com.dongnaoedu.dnplayer.common.constant.RoutePath;
import com.dongnaoedu.dnplayer.common.databinding.ActivitySimpleBinding;
import com.dongnaoedu.dnplayer.common.utils.ViewUtil;
import com.dongnaoedu.dnplayer.libbase.utils.Logger;
import com.jaeger.library.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.hilt.android.AndroidEntryPoint;

/**
 * 标准的Fragment容器Activity
 * 使用ARouter对组件化中Fragment的支持
 */
@Route(path = RoutePath.SIMPLE_PAGE_ACTIVITY)
@AndroidEntryPoint
public class SimplePageActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySimpleBinding binding;

    @Autowired
    String path;

    @Autowired
    String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySimpleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, binding.toolbar.tvTitle);
        ARouter.getInstance().inject(this);
        Class<?> clz = ARouter.getInstance().build(path).navigation().getClass();
        Logger.i("SimplePageActivity onCreate: clz=" + clz);
        if (clz != null && Fragment.class.isAssignableFrom(clz)) {
            // 添加到Activity的容器中的布局当中
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.layout_content, (Class<? extends Fragment>) clz, null)
                    .commit();
        } else {
            throw new RuntimeException(
                    "Can not find Fragment class!");
        }
        initViews();
    }

    private void initViews() {
        binding.toolbar.tvTitle.setText(title);
        ViewUtil.setOnClick(this, binding.toolbar.ivBack);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_back) {
            finish();
        }
    }

}