package gmf.rizky.lycomingm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gmf.rizky.lycomingm.data.db.entities.Job

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllJob(job: Job)

    @Query("SELECT * FROM Job Where job_id = :id")
    fun getJob(id: Int) : LiveData<List<Job>>
}