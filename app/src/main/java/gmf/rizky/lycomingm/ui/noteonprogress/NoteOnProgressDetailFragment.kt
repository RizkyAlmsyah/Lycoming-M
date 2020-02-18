package gmf.rizky.lycomingm.ui.noteonprogress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.data.db.entities.Job
import gmf.rizky.lycomingm.databinding.FragmentNoteOnprogressDetailBinding
import gmf.rizky.lycomingm.util.Coroutines
import kotlinx.android.synthetic.main.fragment_note_onprogress_detail.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class NoteOnProgressDetailFragment : Fragment(), KodeinAware, NoteListener {

    override val kodein by kodein()

    private val factoryJob: JobViewModelFactory by instance()

    private val factoryNote: NoteViewModelFactory by instance()

    private lateinit var viewModelJob: JobViewModel

    private lateinit var viewModelNote: NoteViewModel


    companion object {
        val PROGRESS_JOB_NOTE = "PROGRESS_JOB_NOTE"
        val JOB_ID = "JOB_ID"
        val PROGRESS_JOB_ID = "PROGRESS_JOB_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bindingNote: FragmentNoteOnprogressDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_note_onprogress_detail, container, false)

        val bundle: Bundle? = this.getArguments()

        val job_id: Int? = bundle?.getInt(JOB_ID)
        val progress_job_id: Int? = bundle?.getInt(PROGRESS_JOB_ID)
        val progress_job_note: String? = bundle?.getString(PROGRESS_JOB_NOTE)

        factoryJob.jobId = job_id.toString()
        factoryNote.progress_job_id = progress_job_id.toString()
        factoryNote.progress_job_note = progress_job_note.toString()

        viewModelJob = ViewModelProviders.of(this, factoryJob).get(JobViewModel::class.java)
        viewModelNote = ViewModelProviders.of(this, factoryNote).get(NoteViewModel::class.java)

        bindingNote.viewmodel = viewModelNote

        viewModelNote.noteListener = this


        return bindingNote.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelJob = ViewModelProviders.of(this, factoryJob).get(JobViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        viewModelJob.job.await().observe(this, Observer {
            initRecyclerView(it.toJobItem())
        })
    }

    private fun initRecyclerView(jobItem: List<JobItem>){
        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(jobItem)
        }

        recycler_viewEngineJob.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun List<Job>.toJobItem() : List<JobItem> {
        return this.map {
            JobItem(it)
        }
    }

    override fun onStarted() {

    }

    override fun onSuccess(message: String) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
        view?.let { Navigation.findNavController(it).navigate(R.id.nav_engine_onprogress) }
    }

    override fun onFailure(message: String) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
    }
}