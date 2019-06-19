package shiq.com.order_module.presenter

import shiq.com.common.base.basemvp.BasePresenter
import shiq.com.order_module.contract.OutStandingOrderFragContract

/**
 * created by shi on 2019/6/17/017
 */
class OutStandingOrderFragPresenter(view:OutStandingOrderFragContract.View) :BasePresenter<OutStandingOrderFragContract.View>(view) ,  OutStandingOrderFragContract.Presenter
