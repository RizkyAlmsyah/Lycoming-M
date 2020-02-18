package gmf.rizky.lycomingm.ui.enginedonedetail

import android.graphics.Color
import android.view.View
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.xwray.groupie.databinding.BindableItem
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.data.db.entities.Job_progress_list
import gmf.rizky.lycomingm.databinding.ItemJobDoneProgressDetailBinding

class JobDoneProgressItem (
    val jobDoneProgressList: Job_progress_list
) : BindableItem<ItemJobDoneProgressDetailBinding>() {
    override fun getLayout() = R.layout.item_job_done_progress_detail

    override fun bind(viewBinding: ItemJobDoneProgressDetailBinding, position: Int){
        viewBinding.jobprogresslist = jobDoneProgressList
        if(viewBinding.getJobprogresslist()?.progress_status_name == "Done"){
            viewBinding.progressInputStatus.setTextColor(Color.rgb(20, 171, 0))
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
    }
}