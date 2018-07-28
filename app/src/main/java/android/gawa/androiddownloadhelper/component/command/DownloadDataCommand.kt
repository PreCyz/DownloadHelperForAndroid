package android.gawa.androiddownloadhelper.component.command

import android.content.Context
import android.gawa.androiddownloadhelper.component.mapper.TorrentResponseMapper
import android.gawa.androiddownloadhelper.component.settings.ApiSetup
import android.gawa.androiddownloadhelper.component.web.WebClient

internal class DownloadDataCommand(context: Context, nextCommand: Command?) : AbstractCommand(nextCommand) {

    private val apiSetup = ApiSetup(context)

    override fun execute(commandResult: CommandResult) {
        for (i: Int in 1..apiSetup.queryPage) {
            val params = mapOf("limit" to apiSetup.queryLimit.toString(), "page" to i.toString())
            val response = WebClient().getRequest(apiSetup.url, params)
            val torrentResponse = TorrentResponseMapper().map(response)
            commandResult.torrentResponses.add(torrentResponse)
        }

        nextCommand?.execute(commandResult)
    }
}