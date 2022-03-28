package com.dongnaoedu.dnplayer.main;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dongnaoedu.dnplayer.common.base.BasePageAdapter;
import com.dongnaoedu.dnplayer.common.constant.RoutePath;
import com.dongnaoedu.dnplayer.main.databinding.ActivityMainBinding;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import dagger.hilt.android.AndroidEntryPoint;

@Route(path = RoutePath.MAIN_ACTIVITY)
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private final String[] titles = new String[]{"首页", "视频", "广场", "我的"};
    private final List<Fragment> tabFragmentList = new ArrayList<>();

    ActivityMainBinding binding;

    /*@Inject
    RxPermissions rxPermissions;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        StatusBarUtil.setTranslucentForImageView(this, 0, binding.content.tabLayout);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.layout_main_menu, MainMenuFragment.class, null)
                .commit();

        initViewPager();

//        Disposable d = rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        Logger.i("RxPermissions accept: " + aBoolean);
//                    }
//                });

    }

    private void initViewPager() {
        binding.content.viewPager.setOffscreenPageLimit(4);
        for (String title : titles) {
            binding.content.tabLayout.addTab(binding.content.tabLayout.newTab().setText(title));
        }
        tabFragmentList.add(new HomeFragmentV2());
        tabFragmentList.add(new HomeFragment());
        tabFragmentList.add(new UserInfoFragment());
        tabFragmentList.add(new HomeFragment());
        binding.content.viewPager.setAdapter(new BasePageAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, tabFragmentList, titles));
        // 设置TabLayout和ViewPager联动
        binding.content.tabLayout.setupWithViewPager(binding.content.viewPager, false);
    }

}