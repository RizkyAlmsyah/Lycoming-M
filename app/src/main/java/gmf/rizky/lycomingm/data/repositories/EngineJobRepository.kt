package gmf.rizky.lycomingm.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gmf.rizky.lycomingm.data.db.AppDatabase
import gmf.rizky.lycomingm.data.db.entities.Job
import gmf.rizky.lycomingm.data.network.ApiService
import gmf.rizky.lycomingm.data.network.SafeApiRequest
import gmf.rizky.lycomingm.data.preferenfces.PreferenceProvider
import gmf.rizky.lycomingm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime


class EngineJobRepository(
    private val api: ApiService,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {
    private val job = MutableLiveData<Job>()
    val token = prefs.getToken()

    init {
        job.observeForever{
            saveJob(it)
        }
    }

    suspend fun getJob(job_id: String): LiveData<List<Job>> {
        return withContext(Dispatchers.IO){
            fetchJob(job_id.toInt())
            db.getJobDao().getJob(job_id.toInt())
        }
    }

    private suspend fun fetchJob(job_id: Int){
        val lastSavedAt = prefs.getLastSavedAt()
        val auth = "Bearer " + token
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest { api.getJob(auth, job_id) }
            job.postValue(response.job)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean{
        //ChronoUnit..between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
        return true
    }

    private fun saveJob(job: Job){
        Coroutines.io {
            prefs.saveLastSavedAt(LocalDateTime.now().toString())
            db.getJobDao().saveAllJob(job)
        }
    }
}