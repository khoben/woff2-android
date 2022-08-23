package com.github.khoben.woff2android

import android.content.Context
import android.content.res.AssetManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.runner.RunWith
import java.io.File

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {
    protected val context: Context = InstrumentationRegistry.getInstrumentation().context
    protected val assets: AssetManager = context.assets

    protected fun getTempFile(): File {
        return File.createTempFile("test", null, context.cacheDir)
    }

    protected fun getFileFromAssets(filename: String): File {
        val temp = getTempFile()
        temp.outputStream().use { output ->
            assets.open(filename).use { input ->
                input.copyTo(output)
            }
        }
        return temp
    }

    protected fun getBytesFromAssets(filename: String): ByteArray {
        return assets.open(filename).use { input ->
            input.readBytes()
        }
    }
}