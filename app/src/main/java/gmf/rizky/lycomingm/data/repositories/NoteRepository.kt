package gmf.rizky.lycomingm.data.repositories

import gmf.rizky.lycomingm.data.network.ApiService
import gmf.rizky.lycomingm.data.network.SafeApiRequest
import gmf.rizky.lycomingm.data.network.responses.ProgressJobNoteResponse
import gmf.rizky.lycomingm.data.preferenfces.PreferenceProvider

class NoteRepository (
    private val api: ApiService,
    private val prefs: PreferenceProvider
) : SafeApiRequest() {

    val token = prefs.getToken()

    suspend fun notePost(id: Int, note: String): ProgressJobNoteResponse {
        val auth = "Bearer ${token}"
        return apiRequest { api.updateNote(auth, id, note) }
    }


}