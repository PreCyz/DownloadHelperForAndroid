package android.gawa.androiddownloadhelper.component.settings

class AppSettings(val serverSetup: ServerSetup, val apiSetup: ApiSetup, val requestSetup: RequestSetup,
                  val filterSetup: FilterSetup, val otherSetup: OtherSetup) {
    override fun toString(): String {
        return "AppSettings(serverSetup=$serverSetup, apiSetup=$apiSetup, requestSetup=$requestSetup, filterSetup=$filterSetup, otherSetup=$otherSetup)"
    }
}