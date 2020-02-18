package gmf.rizky.lycomingm.ui.noteonprogress

import com.xwray.groupie.databinding.BindableItem
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.data.db.entities.Job
import gmf.rizky.lycomingm.databinding.ItemJobBinding

class JobItem(
    val job: Job
): BindableItem<ItemJobBinding>() {
    override fun getLayout() = R.layout.item_job

    override fun bind(viewBinding: ItemJobBinding, position: Int) {
        viewBinding.setJob(job)
    }

}