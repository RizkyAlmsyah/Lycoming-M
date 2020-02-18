package gmf.rizky.lycomingm.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import gmf.rizky.lycomingm.data.db.AppDatabase
import gmf.rizky.lycomingm.data.db.entities.Job_done
import gmf.rizky.lycomingm.data.network.ApiService
import gmf.rizky.lycomingm.data.network.SafeApiRequest
import gmf.rizky.lycomingm.data.preferenfces.PreferenceProvider
import gmf.rizky.lycomingm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

private val MINIMUM_INTERVAL = 6

class EngineJobDoneRepository(
    private val api: ApiService,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider
): SafeApiRequest() {
    private val jobDone = MutableLiveData<List<Job_done>>()
    private val token = prefs.getToken()

    init {
        jobDone.observeForever{
            saveJobDone(it)
        }
    }

    suspend fun getJobDone(): LiveData<List<Job_done>> {
        return withContext(Dispatchers.IO){
            fetchJobDone()
            db.getJobDoneDao().getJobDone()
        }
    }

    private suspend fun fetchJobDone(){
        val lastSavedAt = prefs.getLastSavedAt()
        val auth = "Bearer " + token
        if(lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
            val response = apiRequest { api.getJobDone(auth) }
            jobDone.postValue(response.job_done)
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean{
        //ChronoUnit..between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
        return true
    }

    private fun saveJobDone(jobDone: List<Job_done>){
        Coroutines.io{
            prefs.saveLastSavedAt(LocalDateTime.now().toString())
            db.getJobDoneDao().saveAllEngineJobDone(jobDone)
        }
    }

}