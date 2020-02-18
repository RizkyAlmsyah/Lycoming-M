package gmf.rizky.lycomingm.ui.engineonprogressdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gmf.rizky.lycomingm.data.repositories.EngineJobProgressListRepository

@Suppress("UNCHECKED_CAST")
class EngineOnProgressDetailViewModelFactory (
    private val jobProgressListRepository: EngineJobProgressListRepository
) : ViewModelProvider.NewInstanceFactory() {

    lateinit var jobId: String

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EngineOnProgressDetailViewModel(jobProgressListRepository, jobId) as T
    }
}