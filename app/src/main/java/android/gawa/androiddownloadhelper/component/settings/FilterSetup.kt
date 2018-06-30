package android.gawa.androiddownloadhelper.component.settings

import java.time.LocalDate

class FilterSetup {
    val torrentAge : Int = 0
    val maxFileSize : Long = 0
    val releaseDate : LocalDate? = null
    val repeatDownload : Boolean = false
    val handleDuplicates : Boolean = true

    override fun toString(): String {
        return "FilterSetup(torrentAge=$torrentAge, maxFileSize=$maxFileSize, releaseDate=$releaseDate, repeatDownload=$repeatDownload, handleDuplicates=$handleDuplicates)"
    }

}