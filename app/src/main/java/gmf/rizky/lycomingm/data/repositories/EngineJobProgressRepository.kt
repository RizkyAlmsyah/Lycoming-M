package gmf.rizky.lycomingm.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gmf.rizky.lycomingm.data.db.AppDatabase
import gmf.rizky.lycomingm.data.db.entities.Job_progress
import gmf.rizky.lycomingm.data.network.ApiService
import gmf.rizky.lycomingm.data.network.SafeApiRequest
import gmf.rizky.lycomingm.data.preferenfces.PreferenceProvider
import gmf.rizky.lycomingm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

private val MINIMUM_INTERVAL = 6

class EngineJobProgressRepository (
    private val api: ApiService,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
): SafeApiRequest(){
    private val jobProgress = MutableLiveData<List<Job_progress>>()
    private val token = prefs.getToken()

    init {
        jobProgress.observeForever{
            saveJobProgress(it)
        }
    }

    suspend fun getJobProgress(): LiveData<List<Job_progress>> {
        return withContext(Dispatchers.IO){
            fetchJobProgress()
            db.getJobProgressDao().getJobDone()
        }
    }

    private suspend fun fetchJobProgress() {
        val lastSavedAt = prefs.getLastSavedAt()
        val auth = "Bearer " + token
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
            val response = apiRequest { api.getJobProgress(auth) }
            jobProgress.postValue(response.job_progress)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean{
        //ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
        return true
    }

    private fun saveJobProgress(jobProgress: List<Job_progress>) {
        Coroutines.io {
            prefs.saveLastSavedAt(LocalDateTime.now().toString())
            db.getJobProgressDao().saveAllEngineJobProgress(jobProgress)
        }
    }
}