package shiq.com.order_module.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.TimeUtils
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import shiq.com.common.utils.TimeUtil
import shiq.com.common.utils.getResouceColor
import shiq.com.common.view.recycleview.BaseRecyclerAdapter
import shiq.com.order_module.R
import shiq.com.order_module.bean.CompleteOrder

/**
 * created by shi on 2019/6/17/017
 *
 */
class CompleteOrderAdapter(private val mContext: Context, list: List<CompleteOrder>) :
    BaseRecyclerAdapter<CompleteOrder>(mContext, list) {


    override fun getLayoutId(): Int {

        return R.layout.complete_order_list
    }

    @SuppressLint("SetTextI18n")
    override fun bindData(holder: BaseViewHolder, position: Int) {

        var item = datas[position]


        val tvBucketClassify = holder.getView<TextView>(R.id.tv_bucket_classify)
        tvBucketClassify.text = item.goods_name

        val tvProductBucketNumbers = holder.getView<TextView>(R.id.tv_product_bucket_numbers)

        tvProductBucketNumbers.text = "X" + context.resources.getString(R.string.out_bound_submit1) + item.goods_num

        val tvUserName = holder.getView<TextView>(R.id.tv_user_name)

        tvUserName.text = item.ship_name

        val tvUserPhoneNumber = holder.getView<TextView>(R.id.tv_user_phone_number)
        tvUserPhoneNumber.text = item.ship_mobile_phone


        val tvUserAddress = holder.getView<TextView>(R.id.tv_user_address)
        tvUserAddress.text = item.ship_address


        val tvProductComplectedDate = holder.getView<TextView>(R.id.tv_product_complected_date)


        if (!item.send_time.isNullOrEmpty()){
            val substring = item.send_time!!.substring(0, 6)
            tvProductComplectedDate.text = TimeUtil.strToTime(substring)
        }


        val tvProductComplectedTime = holder.getView<TextView>(R.id.tv_product_complected_time)
        tvProductComplectedTime.text = item.send_time_range


        //设置是否配送
        //是否是送水已完成的确认 2 为已完成
        val isFinish = item.isFinish

        val btnConfirm = holder.getView<Button>(R.id.btn_confirm)
        val llIsBack = holder.getView<LinearLayout>(R.id.ll_is_back)
        val tvSendDate = holder.getView<TextView>(R.id.tv_send_date)
        val tvSendTime = holder.getView<TextView>(R.id.tv_send_time)
        val tvBackMoney = holder.getView<TextView>(R.id.tv_back_money)
        val tvBackBrand = holder.getView<TextView>(R.id.tv_back_brand)
        val tvBackMb = holder.getView<TextView>(R.id.tv_back_mb)
        val tvBackBb = holder.getView<TextView>(R.id.tv_back_bb)
        val tvUserRemark = holder.getView<TextView>(R.id.tv_user_remark)
        val tvStoreRemarks = holder.getView<TextView>(R.id.tv_store_remarks)
        val ivPhoneCall = holder.getView<ImageView>(R.id.iv_phone_call)



        //未完成订单
        if (isFinish == 1) {
            btnConfirm.visibility = View.VISIBLE
            btnConfirm.text = "重新分配"
            tvProductBucketNumbers.setTextColor(mContext.resources.getColor(R.color.colorRed))
            llIsBack.visibility = View.GONE
            tvSendDate.setTextColor(mContext.getResouceColor(R.color.colorRed))
            tvSendTime.setTextColor(mContext.getResouceColor(R.color.colorRed))
            tvSendDate.text = TimeUtil.formatTime(System.currentTimeMillis().toString() , TimeUtil.YMD)
            tvSendTime.text = TimeUtil.formatTime(System.currentTimeMillis().toString() , TimeUtil.HM) +
                    "(" + item.songName + ")" + "正在配送"
        } else {
            btnConfirm.visibility = View.GONE
            tvProductBucketNumbers.setTextColor(mContext.resources.getColor(R.color.colorBlue))
            tvBackMoney.setTextColor(mContext.resources.getColor(R.color.colorBlue))
            tvBackBrand.setTextColor(mContext.resources.getColor(R.color.colorBlue))
            llIsBack.visibility = View.VISIBLE
            tvSendDate.setTextColor(mContext.resources.getColor(R.color.colorBlue))
            tvSendTime.setTextColor(mContext.resources.getColor(R.color.colorBlue))
            tvSendDate.text = TimeUtil.formatTime(item.songSendTime.toString() , TimeUtil.YMD)
            tvSendTime.text = TimeUtil.formatTime(item.songSendTime.toString() , TimeUtil.HM) + "(" + item.songName + ")" + "已送达"

            tvBackMb.text = "押金为:"
            tvBackBb.text = "回桶为:"
            tvBackMoney.text = "${item.deposit} 元"
            if (!item.reTgoods.isNullOrEmpty() &&!item.reTnum.isNullOrEmpty()){
                tvBackBrand.text = getBrandNumber(item.reTgoods!!, item.reTnum!!)
            }

        }

        tvUserRemark.text = item.remarks
        tvStoreRemarks.text = item.ysjremark


        btnConfirm.tag = position

        btnConfirm.setOnClickListener { v ->
            listener?.onRvClick(v , position)
        }

        ivPhoneCall.tag = position
        ivPhoneCall.setOnClickListener { v ->
            listener?.onRvClick(v , position)
        }

    }

    fun getBrandNumber(reTgoods: String, reTnum: String): String {


        var sb = StringBuffer()
        if (!TextUtils.isEmpty(reTgoods)) {
            val splitGoods = reTgoods.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val splitNums = reTnum.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (i in splitGoods.indices) {

                println(splitGoods[i] + "----" + splitNums[i] + "\n")
                sb.append(splitGoods[i] + "X" + splitNums[i] + mContext.resources.getString(R.string.out_bound_submit1))


            }

            println(sb.toString() + "我的品牌家数量")

        } else {
            sb.append("此单无回桶")
        }

        return sb.toString()

    }

}