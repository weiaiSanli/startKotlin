package shiq.com.order_module.presenter

import shiq.com.common.base.basemvp.BasePresenter
import shiq.com.order_module.contract.BaseRecycleFragContract

/**
 * created by shi on 2019/6/17/017
 */
class BaseRecycleFragPresenter(private var view: BaseRecycleFragContract.View) : BasePresenter<BaseRecycleFragContract.View>(view), BaseRecycleFragContract.Presenter
