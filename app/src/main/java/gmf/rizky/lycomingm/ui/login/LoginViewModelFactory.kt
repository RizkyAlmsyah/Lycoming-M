package gmf.rizky.lycomingm.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gmf.rizky.lycomingm.data.repositories.ManagementRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(
    private val managementRepository: ManagementRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(managementRepository) as T
    }
}