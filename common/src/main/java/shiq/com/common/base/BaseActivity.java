package shiq.com.common.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

/**
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 上下文环境
     */
    protected Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(getLayoutResourceId());

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            getBundleExtras(extras);
        }
        initView();
        initData();
        setListener();
    }

    protected abstract int getLayoutResourceId();

    //用于传递参数
    protected void getBundleExtras(Bundle extras) {

    }


    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听事件
     */
    protected abstract void setListener();


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            // 点击空白位置 隐藏软键盘  ScrollView里无用
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService
                    (INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus()
                    .getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}
