package gmf.rizky.lycomingm.ui.engineonprogress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.data.db.entities.Job_progress
import gmf.rizky.lycomingm.ui.engineonprogressdetail.EngineOnProgressDetailFragment
import gmf.rizky.lycomingm.util.Coroutines
import gmf.rizky.lycomingm.util.hide
import gmf.rizky.lycomingm.util.show
import kotlinx.android.synthetic.main.fragment_engine_onprogress.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class EngineOnProgressFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: EngineOnProgressViewModelFactory by instance()

    private lateinit var viewModel: EngineOnProgressViewModel

    companion object {
        val JOB_ID = "JOB_ID"
        val JOB_NUMBER = "JOB_NUMBER"
        val JOB_ENGINE_NUMBER = "JOB_ENGINE_NUMBER"
        val JOB_CUSTOMER = "JOB_CUSTOMER"
        val JOB_REFERENCE = "JOB_REFERENCE"
        val JOB_ENTRY_DATE = "JOB_ENTRY_DATE"
        val JOB_ENGINE_MODEL_NAME = "ENGINE_MODEL_NAME"
        val JOB_ORDER_NAME = "JOB_ORDER_NAME"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_engine_onprogress, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(EngineOnProgressViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.jobProgress.await().observe(this, Observer {
            progress_bar.hide()
            initRecyclerView(it.toJobProgressItem())
        })
    }

    private fun initRecyclerView(jobProgressItem: List<JobProgressItem>) {
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(jobProgressItem)
        }

        recycler_viewEngineProgress.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

        mAdapter.setOnItemClickListener{ item, view ->
            val bundle = Bundle()
            (item as? JobProgressItem)?.let {
                bundle.putInt(JOB_ID, it.jobProgress.job_id)
                bundle.putString(JOB_NUMBER, it.jobProgress.job_number)
                bundle.putString(JOB_ENGINE_NUMBER, it.jobProgress.job_engine_number)
                bundle.putString(JOB_CUSTOMER, it.jobProgress.job_customer)
                bundle.putString(JOB_REFERENCE, it.jobProgress.job_reference)
                bundle.putString(JOB_ENTRY_DATE, it.jobProgress.job_entry_date)
                bundle.putString(JOB_ENGINE_MODEL_NAME, it.jobProgress.engine_model_name)
                bundle.putString(JOB_ORDER_NAME, it.jobProgress.job_order_name)

                val fragmentEngineOnProgress = EngineOnProgressDetailFragment()

                fragmentEngineOnProgress.arguments = bundle
                Navigation.findNavController(view).navigate(R.id.nav_engine_onprogress_detail, bundle)
            }
        }
    }

    private fun List<Job_progress>.toJobProgressItem() : List<JobProgressItem> {
        return this.map {
            JobProgressItem(it)
        }
    }
}