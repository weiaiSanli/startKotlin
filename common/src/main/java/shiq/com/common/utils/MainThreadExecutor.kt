package shiq.com.common.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

/**
 * 子线程切换到主线程更新UI
 * created by shi on 2019/6/14/014
 *
 */
class MainThreadExecutor : Executor {
    private  var handler = Handler(Looper.getMainLooper())

    override fun execute(r: Runnable?) {
        handler.post(r)
    }
}