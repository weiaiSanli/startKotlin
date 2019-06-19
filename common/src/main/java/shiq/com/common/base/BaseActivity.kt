package shiq.com.common.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import me.yokeyword.fragmentation.SupportActivity

/**
 * Activity基类
 */
abstract class BaseActivity : SupportActivity() {

    /**
     * 上下文环境
     */
    protected lateinit var context: Context

    protected abstract fun getLayoutResourceId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(getLayoutResourceId())

        val extras = intent.extras
        if (extras != null) {
            getBundleExtras(extras)
        }
        initView()
        initData()
        setListener()
    }

    //kotlin 封装：
   protected fun <V : View> Activity.bindView(id: Int): Lazy<V> = lazy {
        viewFinder(id) as V
    }

    //acitivity中扩展调用
    private val viewFinder: Activity.(Int) -> View?
        get() = { findViewById(it) }


    //用于传递参数
    protected open fun getBundleExtras(extras: Bundle) {

    }


    /**
     * 初始化View
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    /**
     * 设置监听事件
     */
    protected abstract fun setListener()


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (null != this.currentFocus) {
            // 点击空白位置 隐藏软键盘  ScrollView里无用
            val mInputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            return mInputMethodManager.hideSoftInputFromWindow(
                this.currentFocus!!
                    .windowToken, 0
            )
        }
        return super.onTouchEvent(event)
    }


    /**
     * [页面跳转]
     *
     * @param clz
     */
    fun startActivity(clz: Class<*>) {
        startActivity(Intent(this@BaseActivity, clz))
    }

    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent()
        intent.setClass(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    fun startActivityForResult(cls: Class<*>, bundle: Bundle?, requestCode: Int) {
        val intent = Intent()
        intent.setClass(this, cls)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, requestCode)
    }
}
