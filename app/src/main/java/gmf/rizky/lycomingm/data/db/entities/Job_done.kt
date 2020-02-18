package gmf.rizky.lycomingm.data.db.entities

import androidx.room.*
@Entity
data class Job_done(
    @PrimaryKey(autoGenerate = false)
    val job_id : Int,
    val engine_model_id : Int,
    val job_order_id : Int,
    val job_number : String,
    val job_engine_number : String,
    val job_customer : String,
    val job_reference : String,
    val job_entry_date : String,
    val created_at : String,
    val updated_at : String,
    val engine_model_name : String,
    val job_order_name : String

)