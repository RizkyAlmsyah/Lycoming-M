package gmf.rizky.lycomingm.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gmf.rizky.lycomingm.data.db.AppDatabase
import gmf.rizky.lycomingm.data.db.entities.Job_progress_list
import gmf.rizky.lycomingm.data.network.ApiService
import gmf.rizky.lycomingm.data.network.SafeApiRequest
import gmf.rizky.lycomingm.data.preferenfces.PreferenceProvider
import gmf.rizky.lycomingm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

private val MINIMUM_INTERVAL = 6

class EngineJobProgressListRepository (
    private val api: ApiService,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
): SafeApiRequest() {
    private val jobProgressList = MutableLiveData<List<Job_progress_list>>()
    private val token =  prefs.getToken()

    init {
        jobProgressList.observeForever{
            saveJobProgressList(it)
        }
    }

    suspend fun getJobProgressList(job_id: String): LiveData<List<Job_progress_list>> {
        return withContext(Dispatchers.IO){
            fetchJobProgressList(job_id.toInt())
            db.getJobProgressListDao().getJobProgressList(job_id.toInt())
        }
    }

    private suspend fun fetchJobProgressList(job_id: Int) {
        val lastSavedAt = prefs.getLastSavedAt()
        val auth = "Bearer " + token
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest { api.getJobStatusProgress(auth, job_id) }
            jobProgressList.postValue(response.job_progress_list)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean{
        //ChronoUnit..between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
        return true
    }

    private fun saveJobProgressList(jobProgressList: List<Job_progress_list>) {
        Coroutines.io {
            prefs.saveLastSavedAt(LocalDateTime.now().toString())
            db.getJobProgressListDao().saveAllJobProgressList(jobProgressList)
        }
    }
}