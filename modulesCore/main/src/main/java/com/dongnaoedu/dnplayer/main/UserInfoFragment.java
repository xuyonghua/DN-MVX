package com.dongnaoedu.dnplayer.main;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dongnaoedu.dnplayer.common.base.BaseFragment;
import com.dongnaoedu.dnplayer.common.constant.RoutePath;
import com.dongnaoedu.dnplayer.main.databinding.FragmentUserBinding;
import com.dongnaoedu.dnplayer.main.vm.UserInfoViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@Route(path = RoutePath.USER_INFO_FRAGMENT)
@AndroidEntryPoint
public class UserInfoFragment extends BaseFragment<FragmentUserBinding> {

    @Inject
    UserInfoViewModel viewModel;

    @Override
    protected void initViews() {
        super.initViews();
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
    }

    @Override
    protected void loadData() {
        super.loadData();
        viewModel.loadUserInfo();
    }

    @Override
    protected void initLiveData() {
        super.initLiveData();
//        viewModel.musicsData.observe(this, new Observer<List<MusicInfo>>() {
//            @Override
//            public void onChanged(List<MusicInfo> list) {
//
//            }
//        });

//        viewModel.userData.observe(this, new Observer<UserInfo>() {
//            @Override
//            public void onChanged(UserInfo userInfo) {
//                //binding.tvName.setText(userInfo.getUserName());
//                binding.setUserinfo(userInfo);
//            }
//        });
    }
}
