package gmf.rizky.lycomingm.data.network.responses

import gmf.rizky.lycomingm.data.db.entities.Management

data class ManagementResponse(

    val management: Management?,
    val message: String?
)