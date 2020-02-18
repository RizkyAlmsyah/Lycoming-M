package gmf.rizky.lycomingm.ui.engineonprogress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gmf.rizky.lycomingm.data.repositories.EngineJobProgressRepository

@Suppress("UNCHECKED_CAST")
class EngineOnProgressViewModelFactory (
    private val jobProgressRepository: EngineJobProgressRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EngineOnProgressViewModel(jobProgressRepository) as T
    }
}