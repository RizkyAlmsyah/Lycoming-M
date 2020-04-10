package gmf.rizky.lycomingm.ui.enginedonedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.data.db.entities.Job_progress_list
import gmf.rizky.lycomingm.util.Coroutines
import gmf.rizky.lycomingm.util.show
import gmf.rizky.lycomingm.util.hide
import kotlinx.android.synthetic.main.fragment_job_done_detail.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class EngineDoneDetailFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: EngineDoneDetailViewModelFactory by instance()

    private lateinit var viewModel: EngineDoneDetailViewModel

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
        val rootView: View = inflater.inflate(R.layout.fragment_job_done_detail, container, false)

        val bundle: Bundle? = this.getArguments()

        val jobDoneNo: TextView = rootView.findViewById(R.id.textInputJobNo)
        val jobDoneModel: TextView = rootView.findViewById(R.id.textInputModel)
        val jobDoneSerial: TextView = rootView.findViewById(R.id.textInputSerialNo)
        val jobDoneCustomer: TextView = rootView.findViewById(R.id.textInputCustomer)
        val jobDoneDate: TextView = rootView.findViewById(R.id.textInputDate)
        val jobDoneReference: TextView = rootView.findViewById(R.id.textInputReference)
        val jobDoneReason: TextView = rootView.findViewById(R.id.textInputReason)

        jobDoneNo.text = bundle?.getString(JOB_NUMBER)
        jobDoneModel.text = bundle?.getString(JOB_ENGINE_MODEL_NAME)
        jobDoneSerial.text = bundle?.getString(JOB_ENGINE_NUMBER)
        jobDoneCustomer.text = bundle?.getString(JOB_CUSTOMER)
        jobDoneDate.text = bundle?.getString(JOB_ENTRY_DATE)
        jobDoneReference.text = bundle?.getString(JOB_REFERENCE)
        jobDoneReason.text = bundle?.getString(JOB_ORDER_NAME)

        val job_id: Int? = bundle?.getInt(JOB_ID)
        factory.jobId = job_id.toString()
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(EngineDoneDetailViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.jobProgressList.await().observe(this, Observer {
            progress_bar.hide()
            initRecylerView(it.toJobProgressListItem())
        })
    }

    private fun initRecylerView(jobProgressListItem: List<JobDoneProgressItem>){
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(jobProgressListItem)
        }

        recycler_viewEngineDoneProgressDetail.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)

            adapter = mAdapter
        }

    }

    private fun List<Job_progress_list>.toJobProgressListItem() : List<JobDoneProgressItem> {
        return this.map {
            JobDoneProgressItem(it)
        }
    }
}