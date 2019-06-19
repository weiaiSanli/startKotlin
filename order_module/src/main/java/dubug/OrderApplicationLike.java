package dubug;

import android.app.Application;
import shiq.com.common.base.BaseApplication;
import shiq.com.common.base.IComponentApplication;

/**
 * created by shi on 2019/6/18/018
 */
public class OrderApplicationLike implements IComponentApplication {
    @Override
    public void onCreate(BaseApplication application) {
        System.out.println("我是创建的OrderApplicationLike");
    }

    @Override
    public Application getAppliaction() {
        return null;
    }
}
