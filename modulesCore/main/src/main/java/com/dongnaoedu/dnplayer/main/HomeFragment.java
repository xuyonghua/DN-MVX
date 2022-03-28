package com.dongnaoedu.dnplayer.main;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.dongnaoedu.dnplayer.common.base.BaseRecyclerAdapter;
import com.dongnaoedu.dnplayer.common.base.BaseRecyclerFragment;
import com.dongnaoedu.dnplayer.common.data.entity.MusicInfo;
import com.dongnaoedu.dnplayer.libbase.net.exception.ApiException;
import com.dongnaoedu.dnplayer.libbase.utils.Logger;
import com.dongnaoedu.dnplayer.main.contract.MusicContract;
import com.dongnaoedu.dnplayer.main.databinding.FragmentHomeBinding;
import com.dongnaoedu.dnplayer.main.presenter.MusicPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseRecyclerFragment<FragmentHomeBinding>
        implements MusicContract.View {

    @Inject
    MusicPresenter presenter;

    HomeSheetAdapter adapter;

    @Override
    protected void initViews() {
        super.initViews();
        initBanner();
        adapter.addChildClickViewIds(R.id.layout_item);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter a, @NonNull View view, int position) {
                MusicInfo musicInfo = adapter.getItem(position);
                if (view.getId() == R.id.layout_item) {
                    showToast(musicInfo.getMusicName());
                }
            }
        });
    }

    @Override
    protected void requestList(int page) {
        presenter.loadMusics(page);
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
    public void onLoadMusicSuccess(List<MusicInfo> list) {
        requestListFinish(true, list);
    }

    @Override
    public void onLoadMusicFailure(ApiException e) {
        requestListFinish(false, null);
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
