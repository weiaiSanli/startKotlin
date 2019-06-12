package shiq.com.common.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import com.alibaba.android.arouter.launcher.ARouter;
import shiq.com.common.BuildConfig;

import java.util.LinkedList;
import java.util.List;


/**
 * 组件开发中我们的application是放在debug包下的，进行集成合并时是需要移除掉的，
 * 所以组件module中不能使用debug包下的application的context，
 * 因此组件中必须通过Utils.getContext()方法来获取全局 Context
 */

public class BaseApplication extends Application {
    private static BaseApplication application;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;

        //注册Arouter组件化工具
        if (!BuildConfig.isDebug) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

    }

    public static BaseApplication getApplication(){
        return application;
    }


}
