package com.dongnaoedu.dnplayer.main;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.dongnaoedu.dnplayer.common.base.BaseRecyclerAdapter;
import com.dongnaoedu.dnplayer.common.base.BaseRecyclerFragment;
import com.dongnaoedu.dnplayer.common.data.entity.MusicInfo;
import com.dongnaoedu.dnplayer.common.mvvm.annotation.VM;
import com.dongnaoedu.dnplayer.libbase.utils.Logger;
import com.dongnaoedu.dnplayer.main.databinding.FragmentHomeBinding;
import com.dongnaoedu.dnplayer.main.vm.MusicViewModel;
import com.dongnaoedu.dnplayer.main.vm.TestViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragmentV2 extends BaseRecyclerFragment<FragmentHomeBinding> {

    HomeSheetAdapter adapter;

    @Inject
    MusicViewModel musicViewModel;

    @Inject
    TestViewModel testViewModel;

    @Override
    protected void initViews() {
        super.initViews();
        initBanner();
        Logger.i("HomeFragmentV2 initViews: testViewModel=" + testViewModel);
//        viewModel = new ViewModelProvider(this).get(MusicViewModel.class);
//        viewModel.attach(this);

        musicViewModel.musicsData.observe(this, new Observer<List<MusicInfo>>() {
            @Override
            public void onChanged(List<MusicInfo> list) {
                requestListFinish(true, list);
            }
        });
    }

    @Override
    protected void requestList(int page) {
        musicViewModel.loadMusics(page);
    }

    @Override
    protected BaseRecyclerAdapter getRecyclerAdapter() {
        adapter = new HomeSheetAdapter();
        return adapter;
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return binding.recyclerView;
    }

    @Override
    protected void loadData() {
        refreshList();
    }

    private void initBanner() {
        ArrayList<Integer> imgs = new ArrayList<>();
        imgs.add(R.mipmap.img_banner1);
        imgs.add(R.mipmap.img_banner2);
        imgs.add(R.mipmap.img_banner3);
        binding.bannerView.setAdapter(new HomeBannerAdapter(), imgs);
        binding.bannerView.setAutoPlay(true);
    }

    @Override
    protected int getGridLayoutColumns() {
        return 3;
    }

    @Override
    protected boolean isGridLayout() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.i("HomeFragment onResume: ");
    }

}
