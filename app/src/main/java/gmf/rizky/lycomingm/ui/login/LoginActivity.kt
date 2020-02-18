package gmf.rizky.lycomingm.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.data.db.entities.Management
import gmf.rizky.lycomingm.databinding.ActivityLoginBinding
import gmf.rizky.lycomingm.ui.EngineActivity
import gmf.rizky.lycomingm.util.hide
import gmf.rizky.lycomingm.util.show
import gmf.rizky.lycomingm.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.android.kodein


class LoginActivity : AppCompatActivity(), LoginListener, KodeinAware {

    override val kodein by kodein()

    private val factory : LoginViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding_login : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)

        binding_login.viewmodel = viewModel

        viewModel.loginListener = this

        viewModel.getLoggedInManagement().observe(this, Observer { management ->
            if(management != null){

                Intent(this, EngineActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(management: Management) {
        progress_bar.hide()
    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}
