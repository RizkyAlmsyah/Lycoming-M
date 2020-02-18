package gmf.rizky.lycomingm.ui.profile

import androidx.lifecycle.ViewModel
import gmf.rizky.lycomingm.data.repositories.ManagementRepository

class ProfileViewModel (
    managementRepository: ManagementRepository
) : ViewModel() {

    val management = managementRepository.getManagement()
}