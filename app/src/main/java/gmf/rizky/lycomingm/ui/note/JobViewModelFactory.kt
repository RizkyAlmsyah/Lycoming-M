package gmf.rizky.lycomingm.ui.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gmf.rizky.lycomingm.data.repositories.EngineJobRepository

@Suppress("UNCHECKED_CAST")
class JobViewModelFactory (
    private val jobRepository: EngineJobRepository
) : ViewModelProvider.NewInstanceFactory() {

    lateinit var jobId: String

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return JobViewModel(jobRepository, jobId) as T
    }
}