package gmf.rizky.lycomingm.ui.engineonprogressdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.data.db.entities.Job_progress_list
import gmf.rizky.lycomingm.ui.engineonprogress.EngineOnProgressFragment
import gmf.rizky.lycomingm.util.Coroutines
import gmf.rizky.lycomingm.util.hide
import gmf.rizky.lycomingm.util.show
import kotlinx.android.synthetic.main.fragment_job_progress_detail.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class EngineOnProgressDetailFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: EngineOnProgressDetailViewModelFactory by instance()

    private lateinit var viewModel: EngineOnProgressDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.fragment_job_progress_detail, container, false)

        val bundle: Bundle? = this.getArguments()

        val jobDoneNo: TextView = rootView.findViewById(R.id.textInputJobNo)
        val jobDoneModel: TextView = rootView.findViewById(R.id.textInputModel)
        val jobDoneSerial: TextView = rootView.findViewById(R.id.textInputSerialNo)
        val jobDoneCustomer: TextView = rootView.findViewById(R.id.textInputCustomer)
        val jobDoneDate: TextView = rootView.findViewById(R.id.textInputDate)
        val jobDoneReference: TextView = rootView.findViewById(R.id.textInputReference)
        val jobDoneReason: TextView = rootView.findViewById(R.id.textInputReason)

        jobDoneNo.text = bundle?.getString(EngineOnProgressFragment.JOB_NUMBER)
        jobDoneModel.text = bundle?.getString(EngineOnProgressFragment.JOB_ENGINE_MODEL_NAME)
        jobDoneSerial.text = bundle?.getString(EngineOnProgressFragment.JOB_ENGINE_NUMBER)
        jobDoneCustomer.text = bundle?.getString(EngineOnProgressFragment.JOB_CUSTOMER)
        jobDoneDate.text = bundle?.getString(EngineOnProgressFragment.JOB_ENTRY_DATE)
        jobDoneReference.text = bundle?.getString(EngineOnProgressFragment.JOB_REFERENCE)
        jobDoneReason.text = bundle?.getString(EngineOnProgressFragment.JOB_ORDER_NAME)

        val job_id: Int? = bundle?.getInt(EngineOnProgressFragment.JOB_ID)
        factory.jobId = job_id.toString()

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(EngineOnProgressDetailViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.jobProgressList.await().observe(this, Observer {
            progress_bar.hide()
            initRecylerView(it.toJobProgressListItem())
        })
    }

    private fun initRecylerView(jobProgressListItem: List<JobOnProgressProgressItem>){
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(jobProgressListItem)
        }


        recycler_viewEngineOnProgressProgressDetail.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }

    private fun List<Job_progress_list>.toJobProgressListItem() : List<JobOnProgressProgressItem> {
        return this.map {
            JobOnProgressProgressItem(it)
        }
    }
}
