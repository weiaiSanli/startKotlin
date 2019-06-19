package shiq.com.common.utils

/**
 * created by shi on 2019/6/13/013
 *
 */

interface ArouterConst {

    interface OrderModule {

        companion object {

            const val order_orderActivity = "/order/orderActivity"
            const val order_outstandingfrag = "/order/outstandingFragment"
            const val order_completefrag = "/order/completeorderFragment"
            const val order_storemanagerfrag = "/order/storemanagerFragment"

        }

    }

    interface LoginModule {

        companion object {

            const val login_loginActivity = "/login/loginActivity"

        }

    }

    interface CommonModule {
        companion object {
            const val common_degradeServiceImpl = "/common/degradeServiceImpl"

        }

    }


}