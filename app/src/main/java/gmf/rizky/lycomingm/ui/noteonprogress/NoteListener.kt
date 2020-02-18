package gmf.rizky.lycomingm.ui.noteonprogress

interface NoteListener {
    fun onStarted()
    fun onSuccess(message: String)
    fun onFailure(message: String)
}