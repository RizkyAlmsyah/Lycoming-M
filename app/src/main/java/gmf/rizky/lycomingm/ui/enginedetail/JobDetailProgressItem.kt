package gmf.rizky.lycomingm.ui.enginedetail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.xwray.groupie.databinding.BindableItem
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.data.db.entities.Job_progress_list
import gmf.rizky.lycomingm.databinding.ItemJobProgressDetailBinding
import gmf.rizky.lycomingm.ui.note.NoteDetailFragment

class JobProgressItem (
    val jobDoneProgressList: Job_progress_list
) : BindableItem<ItemJobProgressDetailBinding>() {
    override fun getLayout() = R.layout.item_job_progress_detail

    override fun bind(viewBinding: ItemJobProgressDetailBinding, position: Int){
        viewBinding.jobprogresslist = jobDoneProgressList
        if(viewBinding.getJobprogresslist()?.progress_status_name == "Done"){
            viewBinding.progressInputStatus.setTextColor(Color.rgb(20, 171, 0))
        }else if (viewBinding.getJobprogresslist()?.progress_status_name == "On Progress"){
            viewBinding.progressInputStatus.setTextColor(Color.rgb(253, 219, 58))
        }else if (viewBinding.getJobprogresslist()?.progress_status_name == "Pending"){
            viewBinding.progressInputStatus.setTextColor(Color.rgb(199, 44, 65))
        }

        viewBinding.expandedLayout.setVisibility(View.GONE)
        viewBinding.expandButton.setOnClickListener {
            if (viewBinding.expandedLayout.visibility == View.GONE){
                TransitionManager.beginDelayedTransition(viewBinding.cvJobDoneProgressDetail, AutoTransition())
                viewBinding.expandedLayout.visibility = View.VISIBLE
                viewBinding.expandButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
            } else {
                TransitionManager.beginDelayedTransition(viewBinding.cvJobDoneProgressDetail, AutoTransition())
                viewBinding.expandedLayout.visibility = View.GONE
                viewBinding.expandButton.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_blue_12dp)
            }
        }

        viewBinding.btnUpdateNote.setOnClickListener { view ->
            val bundle = Bundle()

            val fragment = NoteDetailFragment()

            bundle.putInt(NoteDetailFragment.JOB_ID, jobDoneProgressList.job_id)
            bundle.putInt(NoteDetailFragment.PROGRESS_JOB_ID, jobDoneProgressList.progress_job_id)
            bundle.putString(NoteDetailFragment.PROGRESS_JOB_NOTE, jobDoneProgressList.progress_job_note)
            bundle.putString(NoteDetailFragment.JOB_SHEET_NAME, jobDoneProgressList.job_sheet_name)

            fragment.arguments = bundle
            Navigation.findNavController(view).navigate(R.id.nav_note_onprogress_detail, bundle)
        }
    }
}