package gmf.rizky.lycomingm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gmf.rizky.lycomingm.data.db.entities.Job_progress

@Dao
interface JobProgressDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllEngineJobProgress(jobProgress: List<Job_progress>)

    @Query("SELECT * FROM Job_progress")
    fun getJobDone() : LiveData<List<Job_progress>>
}