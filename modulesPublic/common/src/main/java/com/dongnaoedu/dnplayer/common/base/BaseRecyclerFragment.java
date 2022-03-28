package com.dongnaoedu.dnplayer.common.base;

import com.chad.library.adapter.base.listener.OnLoadMoreListener;
import com.dongnaoedu.dnplayer.common.widget.CustomLoadMoreView;

import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public abstract class BaseRecyclerFragment<B extends ViewBinding> extends BaseFragment<B> {

    protected static final int STATE_NONE = 0;// 无任何动作
    protected static final int STATE_REFRESH = 1;// 刷新状态
    protected static final int STATE_LOAD_MORE = 2;// 加载更多状态
    protected int mLoadState = STATE_NONE;// 加载状态

    protected BaseRecyclerAdapter mRecyclerAdapter;
    protected RecyclerView mRecyclerView;

    protected boolean isShowFootFinal = true;// 最终是否显示加载更多完成
    protected boolean hasMoreData = false;// 是否还有更多数据
    protected boolean lastHasMoreData = hasMoreData;// 最后一次是否还有更多数据
    protected boolean isErr = false;// 是否出错
    protected int pageSize = 10;// 一页加载的条数
    protected int currentPage = 1;// 当前加载的页数

    @Override
    protected void initViews() {
        super.initViews();
        mRecyclerAdapter = getRecyclerAdapter();
        mRecyclerView = getRecyclerView();
        if (mRecyclerAdapter == null || mRecyclerView == null) {
            throw new RuntimeException(
                    "mRecyclerAdapter or mRecyclerView can not be null !");
        }
        initRecyclerView();
        initLoadMore();
    }

    protected void initRecyclerView() {
        if (isGridLayout()) {
            // 布局管理器对象 参数1.上下文 2.规定一行显示几列的参数常量
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, getGridLayoutColumns()) {
                @Override
                public boolean canScrollVertically() {
                    return BaseRecyclerFragment.this.canScrollVertically();
                }
            };
            // 设置RecycleView显示的方向是水平还是垂直 GridLayout.HORIZONTAL水平  GridLayout.VERTICAL默认垂直
            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
            // 设置布局管理器， 参数gridLayoutManager对象
            mRecyclerView.setLayoutManager(gridLayoutManager);
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false) {
                @Override
                public boolean canScrollVertically() {
                    return BaseRecyclerFragment.this.canScrollVertically();
                }
            });
        }
        //mRecyclerView.setItemViewCacheSize(1000);//防止复用导致position出现错乱问题
        mRecyclerView.setItemAnimator(null);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    private void initLoadMore() {
        mRecyclerAdapter.getLoadMoreModule().setLoadMoreView(new CustomLoadMoreView());
        mRecyclerAdapter.getLoadMoreModule().setEnableLoadMore(isCanLoadMore());
        //当自动加载开启，同时数据不满一屏时，是否继续执行自动加载更多(默认为true)
        //mRecyclerAdapter.getLoadMoreModule().setEnableLoadMoreIfNotFullPage(false);
        if (isCanLoadMore()) {
            mRecyclerAdapter.getLoadMoreModule().setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    if (isCanLoadMore() && hasMoreData &&
                            mLoadState == STATE_NONE) {
                        loadMore();
                    }
                }
            });
        }
    }

    /**
     * 加载数据列表结束
     * 有分页的情况
     *
     * @param isSuccess
     * @param list
     */
    protected void requestListFinish(boolean isSuccess, List list) {
        if (getActivity() == null) {
            return;
        }
        // 加载第一页的时候当作是刷新状态
        if (currentPage == 1) mLoadState = STATE_REFRESH;
        if (mLoadState == STATE_REFRESH) {
            refreshFinish();
            if (isSuccess) {
                if (list != null && list.size() > 0) {
                    showSuccessLayout();
                    currentPage = 2;
                    mRecyclerAdapter.setList(list);
                    if (list.size() >= pageSize) {
                        hasMoreData = true;
                        mRecyclerAdapter.getLoadMoreModule().loadMoreComplete();
                    } else {
                        hasMoreData = false;
                        // 第一页如果不够一页就不显示没有更多数据布局
                        mRecyclerAdapter.getLoadMoreModule().loadMoreEnd(true);
                    }
                } else {
                    mRecyclerAdapter.setList(list);
                    isErr = true;
                    showEmptyLayout();
                }
            } else {
                isErr = true;
                if (mRecyclerAdapter.getData().size() > 0) {
                    // 刷新失败
                    // 页面已经有数据了，直接提示加载失败即可
                    hasMoreData = lastHasMoreData;
                } else {
                    showErrorLayout();
                }
            }
        } else if (mLoadState == STATE_LOAD_MORE) {
            if (isSuccess) {
                if (list != null && list.size() > 0) {
                    // 加载更多成功,页数加1
                    currentPage = currentPage + 1;
                    mRecyclerAdapter.addData(list);
                    if (list.size() >= pageSize) {
                        hasMoreData = true;
                        mRecyclerAdapter.getLoadMoreModule().loadMoreComplete();
                    } else {
                        hasMoreData = false;
                        // 加载结束，显示没有更多数据
                        mRecyclerAdapter.getLoadMoreModule().loadMoreEnd(!isShowFootFinal);
                    }
                } else {
                    mRecyclerAdapter.getLoadMoreModule().loadMoreEnd(!isShowFootFinal);
                }
            } else {
                isErr = true;
                mRecyclerAdapter.getLoadMoreModule().loadMoreFail();
            }
        }
        mLoadState = STATE_NONE;
    }

    /**
     * 刷新列表数据
     */
    protected void refreshList() {
        isErr = false;
        lastHasMoreData = hasMoreData;
        hasMoreData = false;
        mLoadState = STATE_REFRESH;
        requestList(1);
    }

    protected void loadMore() {
        // 解决CoordinatorLayout+RecyclerView加载更多时自动滑动的问题
        // 调用RecyclerView.stopScroll()停止后续滚动即可
        mRecyclerView.stopScroll();
        isErr = false;
        mLoadState = STATE_LOAD_MORE;
        requestList(currentPage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.getData().clear();
            mRecyclerAdapter = null;
        }
    }

    protected void refreshFinish() {

    }

    //========================Base Set===========================

    protected abstract void requestList(int page);

    protected abstract BaseRecyclerAdapter getRecyclerAdapter();

    protected abstract RecyclerView getRecyclerView();

    protected boolean isGridLayout() {
        return false;
    }

    protected int getGridLayoutColumns() {
        return 2;
    }

    protected boolean canScrollVertically() {
        return true;
    }

    protected boolean isCanLoadMore() {
        return true;
    }

}
