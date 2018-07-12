package android.gawa.androiddownloadhelper.component.command

import android.gawa.androiddownloadhelper.component.mapper.TorrentResponseMapper
import android.gawa.androiddownloadhelper.component.web.WebClient

internal class DownloadDataCommand(private val url: String, private val requestParamMap: Map<String, String>) : Command {

    private var nextCommand : Command? = null

    override fun next(command: Command) {
        this.nextCommand = command
    }

    override fun execute(commandResult: CommandResult) {
        val response = WebClient().getRequest(url, requestParamMap)
        val torrentResponse = TorrentResponseMapper().map(response)
        commandResult.torrentResponse = torrentResponse
        nextCommand?.execute(commandResult)
    }
}