package gmf.rizky.lycomingm.ui.engineonprogressdetail

import androidx.lifecycle.ViewModel
import gmf.rizky.lycomingm.data.repositories.EngineJobProgressListRepository
import gmf.rizky.lycomingm.util.lazyDeferred

class EngineOnProgressDetailViewModel (
    private val jobProgressListRepository: EngineJobProgressListRepository,
    private val job_id: String
) : ViewModel() {

    val jobProgressList by lazyDeferred {
        jobProgressListRepository.getJobProgressList(job_id)
    }
}