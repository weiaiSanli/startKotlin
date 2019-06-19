package shiq.com.login_module.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.KeyguardManager
import android.content.Intent
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.os.Bundle
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import shiq.com.common.base.BaseActivity
import shiq.com.common.utils.ConstanceBase
import shiq.com.common.utils.SPreferenceUtil
import shiq.com.common.utils.toast
import shiq.com.login_module.R
import shiq.com.login_module.fragment.FingerprintDialogFragment
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

/**
 * created by shi on 2019/6/13/013
 */
class FingerMarkActivity : BaseActivity(){

    private var finger_type: Boolean by SPreferenceUtil(ConstanceBase.finger_type, false) //获取是否添加了指纹识别


    companion object{
        const val DEFAULT_KEY_NAME = "default_key"
    }

    private var intentType : String ?= null
    override fun getBundleExtras(extras: Bundle) {

        intentType = extras.getString(ConstanceBase.ConstanceLogin.intentFigerType)
    }

    override fun getLayoutResourceId(): Int {
       return R.layout.activity_finger
    }

    private lateinit var ivPhoto:ImageView
    private lateinit var llFinger:LinearLayout

    private var keyStore: KeyStore ?= null

    override fun initView() {

        ivPhoto = findViewById(R.id.iv_photo)
        llFinger = findViewById(R.id.ll_finger)

    }

    override fun initData() {

        if (supportFingerprint() && finger_type) run {
            initKey()
            initCipher()
        }else{
            toast("暂不支持指纹识别")
            finish()
        }
    }

    @TargetApi(23)
    private fun initKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore")
            keyStore!!.load(null)
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
            val builder = KeyGenParameterSpec.Builder(
                DEFAULT_KEY_NAME,
                KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
            )
                .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                .setUserAuthenticationRequired(true)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            keyGenerator.init(builder.build())
            keyGenerator.generateKey()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }


    @SuppressLint("MissingPermission")
    fun supportFingerprint(): Boolean {
        if (Build.VERSION.SDK_INT < 23) {
            Toast.makeText(this, "您的系统版本过低，不支持指纹功能", Toast.LENGTH_SHORT).show()
            return false
        } else {
            val keyguardManager = getSystemService(KeyguardManager::class.java)
            val fingerprintManager = getSystemService(FingerprintManager::class.java)
            if (!fingerprintManager.isHardwareDetected) {
                Toast.makeText(this, "您的手机不支持指纹功能", Toast.LENGTH_SHORT).show()
                return false
            } else if (!keyguardManager.isKeyguardSecure) {
                Toast.makeText(this, "您还未设置锁屏，请先设置锁屏并添加一个指纹", Toast.LENGTH_SHORT).show()
                return false
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "您至少需要在系统设置中添加一个指纹", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }


    @TargetApi(23)
    private fun initCipher() {
        try {
            val key = keyStore?.getKey(DEFAULT_KEY_NAME, null) as SecretKey
            val cipher = Cipher.getInstance(
                KeyProperties.KEY_ALGORITHM_AES + "/"
                        + KeyProperties.BLOCK_MODE_CBC + "/"
                        + KeyProperties.ENCRYPTION_PADDING_PKCS7
            )
            cipher.init(Cipher.ENCRYPT_MODE, key)
            showFingerPrintDialog(cipher)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    private fun showFingerPrintDialog(cipher: Cipher) {
        val fragment = FingerprintDialogFragment()
        fragment.setCipher(cipher)
        fragment.show(fragmentManager, "fingerprint")
    }

    fun onAuthenticated() {

        if (!intentType.isNullOrEmpty()) {
            setResult(200)
        } else {
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }
        toast("指纹验证成功!")
        this@FingerMarkActivity.finish()
    }


    override fun setListener() {

        llFinger.setOnClickListener {

            initCipher()

        }

    }

}
