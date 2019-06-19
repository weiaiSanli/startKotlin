package shiq.com.login_module.activity

import android.content.Intent
import android.view.KeyEvent
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.NetworkUtils
import shiq.com.common.base.BaseActivity
import shiq.com.common.base.ViewPagerBaseFragment
import shiq.com.common.utils.ArouterConst
import shiq.com.common.utils.toast
import shiq.com.common.view.FragmentPagerAdapter
import shiq.com.common.view.NoScrollViewPager
import shiq.com.login_module.R
import java.util.*


/**
 * created by shi on 2019/6/13/013
 *
 */

class ShopMainActivity : BaseActivity() {


    private var pagerList: MutableList<ViewPagerBaseFragment> = ArrayList()
    override fun getLayoutResourceId(): Int {
        return R.layout.activity_shop_main
    }

    private lateinit var vpHomeContent: NoScrollViewPager
    private lateinit var rbOutstanding: RadioButton
    private lateinit var rbCompleted: RadioButton
    private lateinit var rbStore: RadioButton
    private lateinit var rgNewGroup: RadioGroup
    private lateinit var rlRadioGroup: RelativeLayout

    override fun initView() {

        vpHomeContent = findViewById(R.id.vp_home_content)
        rbOutstanding = findViewById(R.id.rb_outstanding)
        rbCompleted = findViewById(R.id.rb_completed)
        rbStore = findViewById(R.id.rb_store)
        rgNewGroup = findViewById(R.id.rg_new_group)
        rlRadioGroup = findViewById(R.id.rl_radioGroup)


    }

    override fun initData() {

        //1:未完成订单
        val outstandingfragment = ARouter.getInstance().build(ArouterConst.OrderModule.order_outstandingfrag)
            .withInt("type" , 0).navigation() as ViewPagerBaseFragment
        val completeOrderfragment = ARouter.getInstance().build(ArouterConst.OrderModule.order_completefrag)
            .navigation() as ViewPagerBaseFragment
        val storemanagerfragment = ARouter.getInstance().build(ArouterConst.OrderModule.order_storemanagerfrag)
            .navigation() as ViewPagerBaseFragment

        pagerList.add(outstandingfragment)
        //2:已完成订单
        pagerList.add(completeOrderfragment)
        //3:门店管理
        pagerList.add(storemanagerfragment)
        vpHomeContent.adapter = FragmentPagerAdapter(supportFragmentManager, pagerList)

        //初始化第一个布局文件
        vpHomeContent.setCurrentItem(0, false)
        rbOutstanding.toggle()


    }

    override fun setListener() {

        /**
         * 设置rediobutton点击相应viewpager的页码
         */
        rgNewGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_outstanding -> {
                    if (vpHomeContent != null) {
                        vpHomeContent.setCurrentItem(0, false)
                    }
                }
                R.id.rb_completed -> {
                    vpHomeContent.setCurrentItem(1, false)

                }
                R.id.rb_store -> {
                    vpHomeContent.setCurrentItem(2, false)
                }
            }
        }
    }

    /**
     * 菜单、返回键响应
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        // TODO Auto-generated method stub
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click()      //调用双击退出函数
        } else false
    }

    private var isExit = false
    private fun exitBy2Click(): Boolean {
        var tExit: Timer
        if (!isExit) {
            isExit = true // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            tExit = Timer()

            tExit.schedule(object : TimerTask() {
                override fun run() {
                    isExit = false // 取消退出
                }
            }, 2000) // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.addCategory(Intent.CATEGORY_HOME)
            startActivity(intent)
            isExit = false
        }
        return isExit
    }

}