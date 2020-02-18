package gmf.rizky.lycomingm.ui.enginedone

import com.xwray.groupie.databinding.BindableItem
import gmf.rizky.lycomingm.R
import gmf.rizky.lycomingm.data.db.entities.Job_done
import gmf.rizky.lycomingm.databinding.ItemEngineDoneBinding

class JobDoneItem (
    val jobDone: Job_done
) : BindableItem<ItemEngineDoneBinding>() {
    override fun getLayout() = R.layout.item_engine_done

    override fun bind(viewBinding: ItemEngineDoneBinding, position: Int) {
        viewBinding.setJobdone(jobDone)
    }
}