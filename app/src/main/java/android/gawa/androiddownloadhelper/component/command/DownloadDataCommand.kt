package android.gawa.androiddownloadhelper.component.command

import android.gawa.androiddownloadhelper.component.mapper.TorrentResponseMapper
import android.gawa.androiddownloadhelper.component.web.WebClient

internal class DownloadDataCommand(private val numberOfTorrents: Int) : Command {

    private var nextCommand : Command? = null
    private val url = "https://eztv.ag/api/get-torrents"
    private val maxLimit = 100

    override fun next(command: Command) {
        this.nextCommand = command
    }

    override fun execute(commandResult: CommandResult) {
        val counter = 1 + (numberOfTorrents / maxLimit)
        if (counter == 1 && numberOfTorrents % maxLimit == 0) {
            return
        }
        for (i: Int in 1..counter) {
            var limit = numberOfTorrents % maxLimit
            if (i * 100 < numberOfTorrents) {
                limit = maxLimit
            }
            val params = mapOf("limit" to limit.toString(), "page" to i.toString())
            val response = WebClient().getRequest(url, params)
            val torrentResponse = TorrentResponseMapper().map(response)
            commandResult.torrentResponses.add(torrentResponse)
        }

        nextCommand?.execute(commandResult)
    }
}