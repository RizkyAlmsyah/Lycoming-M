package gmf.rizky.lycomingm.data.network.responses

import gmf.rizky.lycomingm.data.db.entities.Job_progress

data class EngineProgressResponse(
    val job_progress : List<Job_progress>
)