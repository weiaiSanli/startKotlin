package shiq.com.common.utils

import android.content.Context
import android.provider.SyncStateContract
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import shiq.com.common.BuildConfig
import java.util.regex.Pattern

/**
 * kotlin的扩展函数类
 * created by shi on 2019/1/16/016
 *
 */
/**
 * 用于打印log的扩展函数,kotlin使用实例: "你好,我是log".log() , java中使用 SpreadFunUtilKt.log("你好我是log" , "shi");
 */
inline fun String.log(tag: String = "shiq") {
    if (BuildConfig.isDebug) {
        Log.e(tag, this)
    }
}

/**
 * EditView的赋值扩展函数:传入一个String返回EditView需要的Editable对象
 */
fun String.toEditable(): Editable {
    return Editable.Factory.getInstance().newEditable(this)
}


/**
 * String的转化扩展函数:传入一个String返回末尾不带.0 , 如 12.0->12 , 12.10->12.1
 */
fun Double.subZeroAndDot(): String {
    var s = this.toString()
    if (s.indexOf(".") > 0) {
        val nativePattern = Pattern.compile("0+?\$")
        s = nativePattern.matcher(s).replaceAll("")
        val nativePattern2 = Pattern.compile("[.]\$")
        s = nativePattern2.matcher(s).replaceAll("")
    }
    return s
}




/**
 * toast的扩展函数,用于kotlin的toast,在所有context的子类型中均可直接调用
 */
fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


inline fun Double.format2String(): String {
    return String.format("%.2f", this)
}

fun String?.markIsNullOrEmpty(): String? {

    if (this.isNullOrEmpty()) {
        return null
    }
    return this
}






inline fun Context.getResouceColor(@ColorRes id: Int): Int { //内联函数,提高运行效率
    return ContextCompat.getColor(this, id)
}

fun Context.checkPhone(phone: String): Boolean {
    var num = "[1][345678]\\d{9}"
    return phone.matches(num.toRegex())
}

/**
 * 检查当前输入的是否为null
 */
fun TextView.checkBlank(message: String): String? {
    val text = this.text.toString()
    if (text.isBlank()) {
        return null
    }
    return text
}


// 编写一个扩展方法
fun EditText.checkEtNotNull(): Boolean {
    val text = this.text.toString()
    if (text.isBlank()) {

        return false
    }
    this.setSelection(text.length)
    return true
}
