package gmf.rizky.lycomingm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gmf.rizky.lycomingm.data.db.entities.Job_done

@Dao
interface JobDoneDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllEngineJobDone(jobDone: List<Job_done>)

    @Query("SELECT * FROM Job_done")
    fun getJobDone() : LiveData<List<Job_done>>


}