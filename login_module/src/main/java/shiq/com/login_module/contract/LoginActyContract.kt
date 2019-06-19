package shiq.com.login_module.contract

/**
 * created by shi on 2019/6/12/012
 */
interface LoginActyContract {
    interface Model

    interface View {
         fun getUserName(): String
         fun getPassword(): String
        fun loginError(error: String?)
        fun loginSuccess(userName:String)
    }

    interface Presenter{
        fun loginUser()
    }
}
