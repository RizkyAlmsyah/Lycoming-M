package gmf.rizky.lycomingm.ui.login

import android.view.View
import androidx.lifecycle.ViewModel
import gmf.rizky.lycomingm.data.repositories.ManagementRepository
import gmf.rizky.lycomingm.util.ApiException
import gmf.rizky.lycomingm.util.Coroutines
import gmf.rizky.lycomingm.util.NoInternetException

class LoginViewModel(
    private val managementRepository: ManagementRepository

) : ViewModel() {
    var management_user_name: String?= null
    var password: String?= null

    var loginListener: LoginListener? = null

    fun getLoggedInManagement() = managementRepository.getManagement()

    fun onLoginButtonClick(view: View){
        loginListener?.onStarted()
        if(management_user_name.isNullOrEmpty() || password.isNullOrEmpty()){
            loginListener?.onFailure("Invalid username or password")
            return
        }

        Coroutines.main {
            try{
                val managementResponse = managementRepository.managementLogin(management_user_name!!, password!!)
                managementResponse.management?.let {
                    loginListener?.onSuccess(it)
                    managementRepository.saveManagement(it)
                    return@main
                }
                loginListener?.onFailure(managementResponse.message!!)
            }catch (e: ApiException){
                loginListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                loginListener?.onFailure(e.message!!)
            }

        }


    }
}
