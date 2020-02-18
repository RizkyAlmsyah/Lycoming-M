package gmf.rizky.lycomingm.ui.noteonprogress

import androidx.lifecycle.ViewModel
import gmf.rizky.lycomingm.data.repositories.EngineJobRepository
import gmf.rizky.lycomingm.util.lazyDeferred


class JobViewModel (
    private val jobRepository: EngineJobRepository,
    private val job_id: String
) : ViewModel() {

    val job by  lazyDeferred {
        jobRepository.getJob(job_id)
    }
}