package gmf.rizky.lycomingm.ui.profile

import androidx.lifecycle.ViewModel
import gmf.rizky.lycomingm.data.repositories.ManagementRepository
import gmf.rizky.lycomingm.util.lazyDeferred

class ProfileViewModel (
    private var managementRepository: ManagementRepository
) : ViewModel() {

    val management = managementRepository.getManagement()

    fun delete(){
        managementRepository.deleteAllManagement()
    }
}