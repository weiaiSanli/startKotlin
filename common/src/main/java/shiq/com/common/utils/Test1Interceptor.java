package shiq.com.common.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.blankj.utilcode.util.ActivityUtils;

/**
 * created by shi on 2019/6/14/014
 */
@Interceptor(priority = 8 , name = "我是拦截器")
public class Test1Interceptor implements IInterceptor {
    @Override
    public void process(final Postcard postcard,final InterceptorCallback callback) {

        if (ArouterConst.OrderModule.order_orderActivity.equals(postcard.getPath())) {


            final AlertDialog.Builder ab = new AlertDialog.Builder(ActivityUtils.getTopActivity());
            ab.setCancelable(false);
            ab.setTitle("温馨提醒");
            ab.setMessage("想要跳转到Test4Activity么？(触发了\"/inter/test1\"拦截器，拦截了本次跳转)");
            ab.setNegativeButton("继续", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callback.onContinue(postcard);
                }
            });
            ab.setNeutralButton("算了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    callback.onInterrupt(null);
                }
            });
            ab.setPositiveButton("加点料", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    postcard.withString("extra", "我是在拦截器中附加的参数");
                    callback.onContinue(postcard);
                }
            });

          new MainThreadExecutor().execute(new Runnable() {
              @Override
              public void run() {
                  ab.create().show();
              }
          });

        } else {
            callback.onContinue(postcard);
        }



    }

    @Override
    public void init(Context context) {

    }
}
