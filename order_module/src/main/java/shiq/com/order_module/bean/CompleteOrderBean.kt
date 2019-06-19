package shiq.com.order_module.bean

import shiq.com.common.base.BaseBean

/**
 * created by shi on 2019/6/17/017
 */
data class CompleteOrderBean(
    val data: Data,
    override val infoCode: Int,
    override val message: String,
    val success: String
): BaseBean(message, infoCode)

data class Data(
    val orderList: List<CompleteOrder>
)

data class CompleteOrder(
    val city: String?,
    val create_date: Long,
    val deposit: Double,
    val distribution: Int,
    val goods_id: Int,
    val goods_name: String?,
    val goods_num: Int,
    val id: Int,
    val isCompany: Int,
    val isFinish: Int,
    val order_no: String?,
    val remarks: String?,
    val send_time: String?,
    val send_time_range: String?,
    val ship_address: String?,
    val ship_mobile_phone: String?,
    val ship_name: String?,
    val songName: String?,
    val songNum: Int,
    val songSendTime: Long,
    val songTP: String?,
    val reTgoods: String?,
    val ysjremark: String?,
    val reTnum: String?,
    val song_num: Int,
    val status: Int,
    val update_date: Long,
    val user_id: Int,
    val user_name: String?,
    val waterNum: String?
)