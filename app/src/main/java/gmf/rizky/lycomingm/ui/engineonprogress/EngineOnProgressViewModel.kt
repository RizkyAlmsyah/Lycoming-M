package gmf.rizky.lycomingm.ui.engineonprogress

import androidx.lifecycle.ViewModel
import gmf.rizky.lycomingm.data.repositories.EngineJobProgressRepository
import gmf.rizky.lycomingm.util.lazyDeferred

class EngineOnProgressViewModel(
    private val jobProgressRepository: EngineJobProgressRepository
) : ViewModel() {

    val jobProgress by lazyDeferred {
        jobProgressRepository.getJobProgress()
    }
}