package gmf.rizky.lycomingm

import android.app.Application
import gmf.rizky.lycomingm.data.db.AppDatabase
import gmf.rizky.lycomingm.data.network.ApiService
import gmf.rizky.lycomingm.data.network.NetworkAuthConnectionInterceptor
import gmf.rizky.lycomingm.data.network.NetworkConnectionInterceptor
import gmf.rizky.lycomingm.data.preferenfces.PreferenceProvider
import gmf.rizky.lycomingm.data.repositories.*
import gmf.rizky.lycomingm.ui.enginedone.EngineDoneViewModelFactory
import gmf.rizky.lycomingm.ui.enginedonedetail.EngineDoneDetailViewModelFactory
import gmf.rizky.lycomingm.ui.engineonprogress.EngineOnProgressViewModelFactory
import gmf.rizky.lycomingm.ui.engineonprogressdetail.EngineOnProgressDetailViewModelFactory
import gmf.rizky.lycomingm.ui.login.LoginViewModelFactory
import gmf.rizky.lycomingm.ui.note.JobViewModelFactory
import gmf.rizky.lycomingm.ui.note.NoteViewModelFactory
import gmf.rizky.lycomingm.ui.profile.ProfileViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class BaseApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@BaseApplication))

        //netowork
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { NetworkAuthConnectionInterceptor(instance(), instance()) }
        bind() from singleton { ApiService(instance(), instance()) }
        //storage
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferenceProvider(instance()) }
        //repository
        bind() from singleton { EngineJobDoneRepository(instance(), instance(), instance()) }
        bind() from singleton { EngineJobProgressRepository(instance(), instance(), instance()) }
        bind() from singleton { ManagementRepository(instance(), instance(), instance()) }
        bind() from singleton { EngineJobProgressListRepository(instance(), instance(), instance()) }
        bind() from singleton { EngineJobRepository( instance(), instance(), instance()) }
        bind() from singleton { NoteRepository( instance(), instance()) }
        //factory
        bind() from provider { LoginViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { EngineDoneViewModelFactory(instance()) }
        bind() from provider { EngineOnProgressViewModelFactory(instance()) }
        bind() from provider { EngineDoneDetailViewModelFactory(instance()) }
        bind() from provider { EngineOnProgressDetailViewModelFactory(instance()) }
        bind() from provider { JobViewModelFactory(instance()) }
        bind() from provider { NoteViewModelFactory( instance()) }
    }

}
