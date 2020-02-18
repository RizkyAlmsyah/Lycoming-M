package gmf.rizky.lycomingm.data.repositories

import gmf.rizky.lycomingm.data.db.AppDatabase
import gmf.rizky.lycomingm.data.db.entities.Management
import gmf.rizky.lycomingm.data.network.ApiService
import gmf.rizky.lycomingm.data.network.SafeApiRequest
import gmf.rizky.lycomingm.data.network.responses.ManagementResponse
import gmf.rizky.lycomingm.data.preferenfces.PreferenceProvider

class ManagementRepository(
    private val apiService: ApiService,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
): SafeApiRequest() {

    suspend fun managementLogin(management_user_name: String, password: String) : ManagementResponse {
        return apiRequest { apiService.managementLogin(management_user_name, password) }
    }

    suspend fun saveManagement(management: Management) {
        db.getManagementDao().upsert(management)
        prefs.saveToken(management.token)
    }

    fun getManagement() = db.getManagementDao().getmanagement()

}