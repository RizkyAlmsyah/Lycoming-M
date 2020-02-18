package gmf.rizky.lycomingm.data.network.responses

import gmf.rizky.lycomingm.data.db.entities.Job_done

data class EngineDoneResponse(
    val job_done: List<Job_done>
)