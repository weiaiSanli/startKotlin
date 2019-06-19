package shiq.com.order_module.presenter

import shiq.com.common.base.basemvp.BasePresenter
import shiq.com.order_module.contract.StoreManagerFragContract

/**
 * created by shi on 2019/6/17/017
 */
class StoreManagerFragPresenter(view:StoreManagerFragContract.View) : BasePresenter<StoreManagerFragContract.View>(view) , StoreManagerFragContract.Presenter
