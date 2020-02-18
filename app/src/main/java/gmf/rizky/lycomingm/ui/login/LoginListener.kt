package gmf.rizky.lycomingm.ui.login

import gmf.rizky.lycomingm.data.db.entities.Management

interface LoginListener{
    fun onStarted()
    fun onSuccess(management: Management)
    fun onFailure(message: String)
}