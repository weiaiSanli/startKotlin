package debug;

import android.app.Application;
import android.util.Log;
import shiq.com.common.base.BaseApplication;
import shiq.com.common.base.IComponentApplication;

/**模块 Application 接口实现类:用于当前module的Application的初始化配置参数
 * created by shi on 2019/6/18/018
 */
public class LoginApplicationLike implements IComponentApplication {

    private static final String TAG = "UserApplicationLike";
    @Override
    public void onCreate(BaseApplication application) {

        Log.d(TAG, "onCreate: LoginApplicationLike 创建并初始化");

    }

    @Override
    public Application getAppliaction() {
        return null;
    }
}