package gmf.rizky.lycomingm.data.network.responses

import gmf.rizky.lycomingm.data.db.entities.Job_progress_list


data class JobStatusProgressResponse(
    val job_progress_list : List<Job_progress_list>
)