package gmf.rizky.lycomingm.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gmf.rizky.lycomingm.data.repositories.ManagementRepository

@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory (
    private val repository: ManagementRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}