package shiq.com.common.base;

/**
 * created by shi on 2019/6/13/013
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by shiqiang on 2017/1/27.
 * <p>
 * Viewpager + Fragment情况下，fragment的生命周期因Viewpager的缓存机制而失去了具体意义
 * 该抽象类自定义一个新的回调方法，当fragment可见状态改变时会触发的回调方法，介绍看下面
 *
 * @see #onFragmentVisibleChange(boolean)
 */
public abstract class ViewPagerBaseFragment extends SupportFragment {

    public Context context;

    /**
     * mRootView是否初始化标志，防止回调函数在mRootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(setResourceId(), container, false);
        }
        setEventBus();
        Bundle bundle = getArguments();//从activity传过来的Bundle
        if (bundle != null) {
            getBundle(bundle);
        }

        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (context == null){
            context = getActivity();
        }
        initPresenter();
        initView();
        initdata();
        setOnClickListener();
    }

    public View findViewById(int id) {
        if (mRootView == null) {
            return null;
        } else {
            return mRootView.findViewById(id);
        }
    }





    protected abstract void setOnClickListener();

    protected abstract void initdata();

    protected abstract void initView();

    protected abstract void initPresenter();


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d("shiq", "setUserVisibleHint() -> isVisibleToUser: " + isVisibleToUser);
        if (mRootView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        /**
         * 如果Fragment不可见,会调取set方法,但是此时isFragmentVisible仍然为true,所以走下面的方法
         */
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }


    protected abstract int setResourceId();

    protected void setEventBus() {

    }


    protected void getBundle(Bundle bundle) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (context == null){
            context = getActivity();
        }
        initVariable();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    /**************************************************************
     *  自定义的回调方法，子类可根据需求重写
     *************************************************************/

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
        Log.w("shiqiang", "onFragmentVisibleChange -> isVisible: " + isVisible);

        if (isVisible) {
            onVisible();
        } else {
            onGone();
        }
    }

    private void onGone() {

    }

    protected abstract void onVisible();


    @Override
    public void onDestroy() {
        super.onDestroy();
        unEventBus();
    }

    protected void unEventBus() {

    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        context.startActivity(new Intent(context, clz));
    }

    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    protected void startActivityForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(getActivity(), clazz);
        getActivity().startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult with bundle
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void startActivityForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        getActivity().startActivityForResult(intent, requestCode);
    }
}

