package android.gawa.androiddownloadhelper.component.settings

import org.junit.Assert.assertSame
import org.junit.Test

class ServerPortTest {
    @Test
    fun givenText_when_valueOf_then_return() {
        val valueOf = ServerPort.valueOf("HTTP")

        assertSame(valueOf, ServerPort.HTTP)
    }
}