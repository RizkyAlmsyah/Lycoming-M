package gmf.rizky.lycomingm.ui.enginedone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gmf.rizky.lycomingm.data.repositories.EngineJobDoneRepository

@Suppress("UNCHECKED_CAST")
class EngineDoneViewModelFactory(
    private val jobDoneRepository: EngineJobDoneRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T{
        return EngineDoneViewModel(jobDoneRepository) as T
    }
}