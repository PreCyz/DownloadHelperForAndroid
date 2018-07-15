package android.gawa.androiddownloadhelper.component.command

import org.junit.Assert.*
import org.junit.Test

class DownloadDataCommandTest {
    @Test
    fun given0Torrents_when_execute_then_emptyResult() {
        val result = CommandResult()
        DownloadDataCommand(0, EndCommand()).execute(result)

        assertEquals(0, result.torrentResponses.size)
    }

    @Test
    fun given1Torrents_when_execute_then_return1Torrent() {
        val result = CommandResult()
        DownloadDataCommand(1, EndCommand()).execute(result)

        assertEquals(1, result.torrentResponses.size)
        assertEquals(1, result.torrentResponses.get(0).torrents.size)
    }

    @Test
    fun given121Torrents_when_execute_then_return121Torrent() {
        val result = CommandResult()
        DownloadDataCommand(121, EndCommand()).execute(result)

        assertEquals(2, result.torrentResponses.size)
        assertEquals(121, result.torrentResponses.flatMap { it -> it.torrents }.size)
    }
}