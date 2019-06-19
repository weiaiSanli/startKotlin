package shiq.com.common.view.recycleview;

/**
 * created by shi on 2019/6/17/017
 */

import android.content.Context;
import android.support.v7.widget.*;
import android.util.AttributeSet;
import android.view.View;
import shiq.com.common.R;

/**
 * 自定义可以上拉加载更多的RecyclerView  可以设置为空时的布局
 */
public class MyRecyclerView extends RecyclerView {

    private Context mContext;

    private LoadMoreListener loadMoreListener;
    //是否可加载更多
    private boolean canLoadMore = true;

    private RecyclerView.Adapter mAdapter;

    private Adapter mFooterAdapter;

    private boolean isLoadingData = false;
    //加载更多布局
    private LoadingMoreFooter loadingMoreFooter;
    //空数据布局
    private View emptyView;
    private Adapter oldAdapter;
    private boolean isLoading = false; //判断上拉滑动

    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LoadingMoreFooter footView = new LoadingMoreFooter(mContext);

        //设置自定义加载中和到底了效果
        ProgressView progressView = new ProgressView(context);
        progressView.setIndicatorId(ProgressView.BallPulse);
        progressView.setIndicatorColor(getResources().getColor(R.color.refreshColor));
        footView.addFootLoadingView(progressView);
        //设置条目添加 删除动画
        setItemAnimator(new DefaultItemAnimator());

        addFootView(footView);
        footView.setGone();
    }

//    //点击监听
//    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
//        if (mFooterAdapter != null && mFooterAdapter instanceof FooterAdapter) {
//            ((FooterAdapter) mFooterAdapter).setOnItemClickListener(onItemClickListener);
//        }
//    }
//
//    //长按监听
//    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener listener) {
//        if (mFooterAdapter != null && mFooterAdapter instanceof FooterAdapter) {
//            ((FooterAdapter) mFooterAdapter).setOnItemLongClickListener(listener);
//        }
//    }

    /**
     * 底部加载更多视图
     *
     * @param view
     */
    public void addFootView(LoadingMoreFooter view) {
        loadingMoreFooter = view;
    }

    //设置底部加载中效果
    public void setFootLoadingView(View view) {
        if (loadingMoreFooter != null) {
            loadingMoreFooter.addFootLoadingView(view);
        }
    }

    //设置底部到底了布局
    public void setFootEndView(View view) {
        if (loadingMoreFooter != null) {
            loadingMoreFooter.addFootEndView(view);
        }
    }

    //下拉刷新后初始化底部状态
    public void refreshComplete() {
        if (loadingMoreFooter != null) {
            loadingMoreFooter.setGone();
        }
        isLoadingData = false;
    }

    public void loadMoreComplete() {
        if (loadingMoreFooter != null) {
            loadingMoreFooter.setGone();
        }
        isLoadingData = false;
    }

    public void loadNoFooter() {
        if (loadingMoreFooter != null) {
            loadingMoreFooter.setNoFoot();
        }
        isLoadingData = false;
    }

    //到底了
    public void loadMoreEnd() {
        if (loadingMoreFooter != null) {
            loadingMoreFooter.setEnd();
        }
    }

    //设置是否可加载更多
    public void setCanLoadMore(boolean flag) {
        canLoadMore = flag;
    }

    //设置加载更多监听
    public void setLoadMoreListener(LoadMoreListener listener) {
        loadMoreListener = listener;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mAdapter != null&&mDataObserver!=null) {
            mAdapter.unregisterAdapterDataObserver(mDataObserver);
        }
        mAdapter = adapter;

        mFooterAdapter = new FooterAdapter(this, loadingMoreFooter, adapter);
        super.setAdapter(mFooterAdapter);
        if (mAdapter != null && !mAdapter.hasObservers()) {
            mAdapter.registerAdapterDataObserver(mDataObserver);
        }
        checkIfEmpty();
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        isLoading = dy > 0;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        if (isLoading && state == RecyclerView.SCROLL_STATE_IDLE && loadMoreListener != null &&
                !isLoadingData
                && canLoadMore) {
            LayoutManager layoutManager = getLayoutManager();
            int lastVisibleItemPosition;
            if (layoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                lastVisibleItemPosition = last(into);
            } else {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
            }

            if (layoutManager.getChildCount() > 0
                    && lastVisibleItemPosition >= layoutManager.getItemCount() - 1) {
                if (loadingMoreFooter != null) {
                    loadingMoreFooter.setVisible();
                }
                isLoadingData = true;
                loadMoreListener.onLoadMore();
            }
        }
    }

    //取到最后的一个节点
    private int last(int[] lastPositions) {
        int last = lastPositions[0];
        for (int value : lastPositions) {
            if (value > last) {
                last = value;
            }
        }
        return last;
    }

    private RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver
            () {
        @Override
        public void onChanged() {
            mFooterAdapter.notifyDataSetChanged();
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mFooterAdapter.notifyItemRangeInserted(positionStart, itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mFooterAdapter.notifyItemRangeChanged(positionStart, itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mFooterAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mFooterAdapter.notifyItemRangeRemoved(positionStart, itemCount);
            checkIfEmpty();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mFooterAdapter.notifyItemMoved(fromPosition, toPosition);
            checkIfEmpty();
        }
    };

    private void checkIfEmpty() {
        if (emptyView != null && getAdapter() != null) {
            final boolean emptyViewVisible = getAdapter().getItemCount() == 1;
            emptyView.setVisibility(emptyViewVisible ? VISIBLE : GONE);
            setVisibility(emptyViewVisible ? GONE : VISIBLE);
        }
    }

    /**
     * 设置RecyclerView为空时的布局
     *
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
        checkIfEmpty();
    }

    /**
     * 获取RecyclerView为空时的布局
     *
     * @return
     */
    public View getEmptyView() {
        return emptyView;
    }
}

