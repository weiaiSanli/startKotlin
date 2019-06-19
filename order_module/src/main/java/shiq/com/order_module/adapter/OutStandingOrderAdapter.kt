package shiq.com.order_module.adapter

import android.content.Context
import shiq.com.common.view.recycleview.BaseRecyclerAdapter
import shiq.com.order_module.R
import shiq.com.order_module.bean.OutStandingOrderBean

/**
 * created by shi on 2019/6/17/017
 *
 */
class OutStandingOrderAdapter(private val context: Context, list: List<OutStandingOrderBean>)
    : BaseRecyclerAdapter<OutStandingOrderBean>(context, list) {


    override fun getLayoutId(): Int {

        return R.layout.outstanding_order_list
    }

    override fun bindData(holder: BaseViewHolder?, position: Int) {

    }
}