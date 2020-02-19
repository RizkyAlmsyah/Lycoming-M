package gmf.rizky.lycomingm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gmf.rizky.lycomingm.data.db.entities.*

@Database(
    entities = [Management::class, Job_done::class, Job_progress::class, Job_progress_list::class, Job::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getManagementDao() : ManagementDao
    abstract fun getJobDoneDao() : JobDoneDao
    abstract fun getJobProgressDao() : JobProgressDao
    abstract fun getJobProgressListDao() : JobProgressListDao
    abstract fun getJobDao() : JobDao

    companion object{

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance?:buildDatabase(context)
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "Lycoming.db"
            ).build()
    }
}