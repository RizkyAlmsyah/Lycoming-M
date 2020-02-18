package gmf.rizky.lycomingm.ui.noteonprogress

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import gmf.rizky.lycomingm.data.repositories.NoteRepository

@Suppress("UNCHECKED_CAST")
class NoteViewModelFactory (
    private val noteRepository: NoteRepository
) : ViewModelProvider.NewInstanceFactory() {

    lateinit var progress_job_id: String

    lateinit var progress_job_note: String

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteViewModel(noteRepository, progress_job_id, progress_job_note) as T
    }
}