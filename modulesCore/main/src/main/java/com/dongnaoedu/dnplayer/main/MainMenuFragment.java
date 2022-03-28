package com.dongnaoedu.dnplayer.main;

import android.view.View;

import com.dongnaoedu.dnplayer.common.base.BaseFragment;
import com.dongnaoedu.dnplayer.common.constant.SimplePage;
import com.dongnaoedu.dnplayer.common.glide.GlideApp;
import com.dongnaoedu.dnplayer.common.utils.PageHelper;
import com.dongnaoedu.dnplayer.main.databinding.FragmentMenuBinding;
import com.jaeger.library.StatusBarUtil;

public class MainMenuFragment extends BaseFragment<FragmentMenuBinding> {

    @Override
    protected void initViews() {
        StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), 0, binding.layoutMenuTop);
        GlideApp.with(this)
                .load(R.mipmap.img_music1)
                .applyAvatar(144 * 2)
                .into(binding.ivAvatar);

        binding.ivAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageHelper.showSimplePage(SimplePage.USER_INFO);
            }
        });
    }

}