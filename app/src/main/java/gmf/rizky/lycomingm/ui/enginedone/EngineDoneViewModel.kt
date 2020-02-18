package gmf.rizky.lycomingm.ui.enginedone

import androidx.lifecycle.ViewModel
import gmf.rizky.lycomingm.data.repositories.EngineJobDoneRepository
import gmf.rizky.lycomingm.util.lazyDeferred


class EngineDoneViewModel(
    private val jobDoneRepository: EngineJobDoneRepository
) : ViewModel() {

    val jobDone by lazyDeferred {
        jobDoneRepository.getJobDone()
    }
}