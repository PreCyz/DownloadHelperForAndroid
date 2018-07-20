package android.gawa.androiddownloadhelper.component.model

data class DsPath(
        private val authInfo: DsApiDetails,
        private val downloadStationTask: DsApiDetails,
        private val fileStationUpload: DsApiDetails,
        private val fileStationDownload: DsApiDetails
)