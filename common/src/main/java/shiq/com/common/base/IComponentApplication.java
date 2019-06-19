package shiq.com.common.base;

import android.app.Application;

/**
 * 组件化创建Application的接口
 * created by shi on 2019/6/18/018
 */
public interface IComponentApplication {

    void onCreate(BaseApplication application);

    Application getAppliaction();
}
