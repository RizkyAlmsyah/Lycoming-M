package gmf.rizky.lycomingm.ui.engineonprogress

import com.xwray.groupie.databinding.BindableItem
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.data.db.entities.Job_progress
import gmf.rizky.lycomingm.databinding.ItemEngineOnprogressBinding

class JobProgressItem (
    val jobProgress: Job_progress
) : BindableItem<ItemEngineOnprogressBinding>() {
    override fun getLayout() = R.layout.item_engine_onprogress

    override fun bind(viewBinding: ItemEngineOnprogressBinding, position: Int) {
        viewBinding.setJobprogress(jobProgress)
    }

}