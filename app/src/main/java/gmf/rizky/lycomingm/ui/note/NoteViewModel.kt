package gmf.rizky.lycomingm.ui.note

import android.view.View
import androidx.lifecycle.ViewModel
import gmf.rizky.lycomingm.data.repositories.NoteRepository
import gmf.rizky.lycomingm.util.ApiException
import gmf.rizky.lycomingm.util.Coroutines
import gmf.rizky.lycomingm.util.NoInternetException

class NoteViewModel (
    private val noteRepository: NoteRepository,
    private val progress_job_id: String,
    private val progress_job_note: String
): ViewModel() {
    var note: String?= progress_job_note

    var noteListener: NoteListener? = null

    fun onSubmitButtonClick(view: View){
        noteListener?.onStarted()
        if(note.isNullOrEmpty()){
            noteListener?.onFailure("Note Empty please if cancel go back")
            return
        }

        Coroutines.main {
            try {
                val noteResponse = noteRepository.notePost(progress_job_id.toInt(), note!!)
                noteResponse.message?.let {
                    noteListener?.onSuccess(it)
                    return@main
                }
                noteListener?.onFailure(noteResponse.message!!)
            }catch (e: ApiException){
                noteListener?.onFailure(e.message!!)
            }catch (e: NoInternetException){
                noteListener?.onFailure(e.message!!)
            }
        }
    }
}