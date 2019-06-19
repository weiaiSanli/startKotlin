package shiq.com.common.view.recycleview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义设置基类的RecycleView适配器
 * created by shi on 2019/6/17/017
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter
        .BaseViewHolder> {

    protected Context context;
    public List<T> datas = new ArrayList<>();

    public BaseRecyclerAdapter(Context context, List<T> list) {
        if (datas.size() != 0){
            datas.clear();
        }
        this.datas.addAll(list);
        this.context = context;
    }

    // 头部控件
    private View mHeaderView;

    // 底部控件
    private View mFooterView;

    // item 的三种类型
    public static final int ITEM_TYPE_NORMAL = 0X1111; // 正常的item类型
    public static final int ITEM_TYPE_HEADER = 0X1112; // header
    public static final int ITEM_TYPE_FOOTER = 0X1113; // footer

    private boolean isHasHeader = false;
    private boolean isHasFooter = false;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == ITEM_TYPE_FOOTER) {
            // 如果是底部类型，返回底部视图
            return new BaseViewHolder(mFooterView);
        }

        if (viewType == ITEM_TYPE_HEADER) {
            return new BaseViewHolder(mHeaderView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);
//        AutoUtils.autoSize(view); //可以让item的控件寛高赋值为px
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.BaseViewHolder holder, final int position) {

        if (isHasHeader && isHasFooter) {
            // 有头布局和底部时，向前便宜一个，且最后一个不能绑定数据
            if (position == 0 || position == datas.size() + 1) {
                return;
            }
            bindData(holder, position - 1);
        }

        if (position != 0 && isHasHeader && !isHasFooter) {
            // 有顶部，但没有底部
            bindData(holder, position - 1);
        }

        if (!isHasHeader && isHasFooter) {
            // 没有顶部，但有底部
            if (position == datas.size()) {
                return;
            }
            bindData(holder, position);
        }

        if (!isHasHeader && !isHasFooter) {
            // 没有顶部，没有底部
            bindData(holder, position);
        }
    }

    /**
     * 为GridLayoutManager添加header/Footer
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == ITEM_TYPE_HEADER || getItemViewType
                            (position) == ITEM_TYPE_FOOTER ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 为StaggeredGridLayoutManager添加header/Footer
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(BaseRecyclerAdapter.BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams)
                    lp;
            p.setFullSpan(true);
        }
    }

    /**
     * 添加头部视图
     *
     * @param header
     */
    public void setHeaderView(View header) {
        this.mHeaderView = header;
        isHasHeader = true;
        notifyDataSetChanged();
    }

    /**
     * 添加底部视图
     *
     * @param footer
     */
    public void setFooterView(View footer) {
        this.mFooterView = footer;
        isHasFooter = true;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        // 根据索引获取当前View的类型，以达到复用的目的

        // 根据位置的索引，获取当前position的类型
        if (isHasHeader && position == 0) {
            return ITEM_TYPE_HEADER;
        }
        /**
         * 如果RecyclerView自带底部 这里可能要修改 .....2333 有空再测吧 QQ@296335000
         */
        if (isHasHeader && isHasFooter && position == datas.size() + 1) {
            // 有头部和底部时，最后底部的应该等于size+!
            return ITEM_TYPE_FOOTER;
        } else if (!isHasHeader && isHasFooter && position == datas.size()) {
            // 没有头部，有底部，底部索引为size
            return ITEM_TYPE_FOOTER;
        }
        return ITEM_TYPE_NORMAL;
    }

    /**
     * 刷新数据
     *
     * @param listData
     */
    public void refresh(List<T> listData) {
        if (datas.size() != 0){
            datas.clear();
        }
        this.datas.addAll(listData);

        notifyDataSetChanged();
    }

    /**
     * 添加数据
     *
     * @param datas
     */
    public void addData(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 获取子item
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 绑定数据
     *
     * @param holder   具体的viewHolder
     * @param position 对应的索引
     */
    protected abstract void bindData(BaseViewHolder holder, int position);

    @Override
    public int getItemCount() {
        int size = datas.size();
        if (isHasFooter)
            size++;
        if (isHasHeader)
            size++;

        return size;
    }



    /**
     * 封装ViewHolder ,子类可以直接使用
     */
    public class BaseViewHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> mViewMap;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mViewMap = new SparseArray<>();
        }

        /**
         * 获取设置的view
         *
         * @param id
         * @return
         */
        public <T extends View> T getView(int id) {
            View view = mViewMap.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViewMap.put(id, view);
            }
            return (T)view;
        }
        /**
         * 设置TextView的值
         */
        public BaseViewHolder setText(int viewId,String text){
            TextView tv= getView(viewId);
            tv.setText(text);
            return this;
        }
    }

    protected OnRvItemClickListener listener ;
    public void setOnItemClickListener(OnRvItemClickListener listener){
        this.listener = listener ;
    }

    public interface OnRvItemClickListener{
        void onRvClick(View v , int position);
    }

}
