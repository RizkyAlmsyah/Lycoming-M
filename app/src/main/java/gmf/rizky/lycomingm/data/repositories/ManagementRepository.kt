package gmf.rizky.lycomingm.data.repositories

import android.os.AsyncTask
import gmf.rizky.lycomingm.data.db.AppDatabase
import gmf.rizky.lycomingm.data.db.entities.Management
import gmf.rizky.lycomingm.data.network.ApiService
import gmf.rizky.lycomingm.data.network.SafeApiRequest
import gmf.rizky.lycomingm.data.network.responses.ManagementResponse
import gmf.rizky.lycomingm.data.preferenfces.PreferenceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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

    fun deleteAllManagement() {
        val deleteAllManagementAsyncTask = DeleteAllManagementAsyncTask(
            db
        ).execute()
    }

}

private class DeleteAllManagementAsyncTask(val db: AppDatabase): AsyncTask<Unit, Unit, Unit>() {
    override fun doInBackground(vararg params: Unit?) {
        db.getManagementDao().deletemanagement()
    }

}