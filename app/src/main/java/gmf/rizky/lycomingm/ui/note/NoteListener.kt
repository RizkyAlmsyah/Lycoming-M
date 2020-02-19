package gmf.rizky.lycomingm.ui.note

interface NoteListener {
    fun onStarted()
    fun onSuccess(message: String)
    fun onFailure(message: String)
}