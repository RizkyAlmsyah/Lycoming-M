package gmf.rizky.lycomingm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gmf.rizky.lycomingm.data.db.entities.Job_progress_list

@Dao
interface JobProgressListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllJobProgressList(jobProgressList: List<Job_progress_list>)

    @Query("SELECT * FROM Job_progress_list Where job_id = :id ")
    fun getJobProgressList(id: Int) : LiveData<List<Job_progress_list>>
}