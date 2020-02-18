package gmf.rizky.lycomingm.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Job_progress_list (
    @PrimaryKey(autoGenerate = false)
    val progress_job_id : Int,
    val job_id : Int,
    val job_sheet_id : Int,
    val engineer_id : Int,
    val management_id : Int,
    val progress_status_id : Int?,
    val progress_job_date_start : String?,
    val progress_job_date_completion : String?,
    val progress_job_remark : String?,
    val progress_job_note : String?,
    val created_at : String,
    val updated_at : String,
    val job_sheet_name : String,
    val progress_status_name : String?
)