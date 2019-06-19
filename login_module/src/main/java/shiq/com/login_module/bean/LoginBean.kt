package shiq.com.login_module.bean

import shiq.com.common.base.BaseBean


/**
 * created by shi on 2019/6/12/012
 *
 */
data class LoginBean(
    override val message: String,
    override val infoCode: Int
):BaseBean(message , infoCode)