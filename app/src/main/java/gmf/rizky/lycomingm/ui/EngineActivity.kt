package gmf.rizky.lycomingm.ui

import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.databinding.ActivityMainBinding
import gmf.rizky.lycomingm.ui.profile.ProfileViewModel
import gmf.rizky.lycomingm.ui.profile.ProfileViewModelFactory
import gmf.rizky.lycomingm.ui.splashcreen.SplashActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.android.x.kodein

import org.kodein.di.generic.instance

class EngineActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val factory: ProfileViewModelFactory by instance()

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val txtUsername: TextView = findViewById(R.id.nav_header_username)
        val btnLogout: LinearLayout = findViewById(R.id.btnLogout)

        var viewModel = ViewModelProviders.of(this, factory).get(ProfileViewModel::class.java)

        viewModel.management.observe(this, Observer { management ->
            txtUsername.text = management.management_full_name
        })

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_engine_onprogress,
                R.id.nav_engine_done
            ), drawerLayout
        )

        btnLogout.setOnClickListener {
            val intent = Intent(this, SplashActivity::class.java)
            viewModel.delete()
            startActivity(intent)
            finish()
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
