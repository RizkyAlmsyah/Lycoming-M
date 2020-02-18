package gmf.rizky.lycomingm.ui.enginedone

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
import gmf.rizky.lycomingm.data.db.entities.Job_done
import gmf.rizky.lycomingm.ui.enginedonedetail.EngineDoneDetailFragment
import gmf.rizky.lycomingm.util.Coroutines
import gmf.rizky.lycomingm.util.hide
import gmf.rizky.lycomingm.util.show
import kotlinx.android.synthetic.main.fragment_engine_done.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class EngineDoneFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: EngineDoneViewModelFactory by instance()


    private lateinit var viewModel: EngineDoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_engine_done, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(EngineDoneViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.jobDone.await().observe(this, Observer {
            progress_bar.hide()
            initRecyclerView(it.toJobDoneItem())
        })
    }

    private fun initRecyclerView(jobDoneItem: List<JobDoneItem>){
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(jobDoneItem)
        }

        recycler_viewEngineDone.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

        mAdapter.setOnItemClickListener { item, view ->
            val bundle = Bundle()
            (item as? JobDoneItem)?.let {
                bundle.putInt(EngineDoneDetailFragment.JOB_ID, it.jobDone.job_id)
                bundle.putString(EngineDoneDetailFragment.JOB_NUMBER, it.jobDone.job_number)
                bundle.putString(EngineDoneDetailFragment.JOB_ENGINE_NUMBER, it.jobDone.job_engine_number)
                bundle.putString(EngineDoneDetailFragment.JOB_CUSTOMER, it.jobDone.job_customer)
                bundle.putString(EngineDoneDetailFragment.JOB_REFERENCE, it.jobDone.job_reference)
                bundle.putString(EngineDoneDetailFragment.JOB_ENTRY_DATE, it.jobDone.job_entry_date)
                bundle.putString(EngineDoneDetailFragment.JOB_ENGINE_MODEL_NAME, it.jobDone.engine_model_name)
                bundle.putString(EngineDoneDetailFragment.JOB_ORDER_NAME, it.jobDone.job_order_name)

                val fragmentEngineDone = EngineDoneDetailFragment()


                fragmentEngineDone.arguments = bundle


                Navigation.findNavController(view).navigate(R.id.nav_engine_done_detail, bundle)
            }

        }
    }

    private fun List<Job_done>.toJobDoneItem() : List<JobDoneItem> {
        return this.map {
            JobDoneItem(it)
        }
    }
}