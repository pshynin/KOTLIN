package sample

import platform.UIKit.UIDevice

actual class Sample {
    actual fun checkMe() = 7
}

actual object Platform {
    actual val name = UIDevice.currentDevice.let { "${it.systemName()} ${it.systemVersion()}" }
}