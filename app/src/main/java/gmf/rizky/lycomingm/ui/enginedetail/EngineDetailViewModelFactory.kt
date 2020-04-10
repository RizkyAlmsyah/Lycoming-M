package gmf.rizky.lycomingm.ui.enginedonedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gmf.rizky.lycomingm.data.repositories.EngineJobProgressListRepository

@Suppress("UNCHECKED_CAST")
class EngineDoneDetailViewModelFactory (
    private val jobProgressListRepository: EngineJobProgressListRepository
) : ViewModelProvider.NewInstanceFactory() {

    lateinit var jobId: String

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EngineDoneDetailViewModel(jobProgressListRepository, jobId) as T
    }
}