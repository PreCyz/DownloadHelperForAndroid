package android.gawa.androiddownloadhelper.component.settings

enum class ServerPort {
    HTTP {
        override fun port() = 5000
    },
    HTTPS {
        override fun port() = 5001
    };

    abstract fun port(): Int
}