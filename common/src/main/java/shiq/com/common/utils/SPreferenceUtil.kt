package shiq.com.common.utils

import android.content.Context
import shiq.com.common.base.BaseApplication
import java.lang.IllegalArgumentException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 设置Sp的配置存储
 * created by shi on 2019/1/5/005
 *
 */
class SPreferenceUtil<T>(val name:String, val default: T): ReadWriteProperty<Any?, T> {
    val prefs by lazy { BaseApplication.getApplication().getSharedPreferences(ConstanceBase.SHAREP_NAME , Context.MODE_PRIVATE) }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name , default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {

        putpreference(name , value)
    }

    private fun <U> findPreference(name:String , default: U):U = with(prefs){

        val res:Any = when(default){
            is Long -> getLong(name ,default)
            is String->getString(name ,default)
            is Int->getInt(name ,default)
            is Boolean-> getBoolean(name ,default)
            is Float->getFloat(name, default)

            else-> throw IllegalArgumentException("this type can`t saved SP")

        }

        res as U

    }

    private fun <U> putpreference(name: String ,value: U) = with(prefs.edit()){

        when(value){
            is Long->putLong(name ,value)
            is String -> putString(name ,value)
            is Int -> putInt(name ,value)
            is Boolean-> putBoolean(name ,value)
            is Float->putFloat(name, value)
            else -> throw IllegalArgumentException("this type not put SP")
        }.apply()

    }

}