package shiq.com.order_module.contract;

import org.jetbrains.annotations.NotNull;
import shiq.com.order_module.bean.CompleteOrder;

import java.util.List;

/**
 * created by shi on 2019/6/17/017
 */
public interface CompleteOrderFragContract {
    interface Model {
    }

    interface View {

        String getUserName();
        String getPageNum();
        void loginSuccess(@NotNull List<CompleteOrder> orderList);
    }

    interface Presenter {
        void loginNet();
    }
}
