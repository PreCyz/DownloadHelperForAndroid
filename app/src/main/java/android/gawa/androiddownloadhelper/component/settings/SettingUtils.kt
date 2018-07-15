package android.gawa.androiddownloadhelper.component.settings

import android.content.Context
import android.content.SharedPreferences
import android.gawa.androiddownloadhelper.R
import androidx.appcompat.app.AppCompatActivity

class SettingUtils(private val activity: AppCompatActivity) {

    private var sharedPreferences : SharedPreferences? = null

    private fun getSharedPref(): SharedPreferences? {
        return activity.getPreferences(Context.MODE_PRIVATE)
    }

    fun readLimitFromSharedPreferences(): Int {
        sharedPreferences = sharedPreferences ?: getSharedPref()
        val defaultValue = activity.resources.getInteger(R.integer.default_response_limit)
        return sharedPreferences?.getInt(activity.getString(R.string.response_limit_key), defaultValue) ?: defaultValue
    }

    fun writeLimitToSharedPreferences(limit: Int) {
        sharedPreferences = sharedPreferences ?: getSharedPref()
        with (sharedPreferences?.edit()) {
            this?.putInt(activity.getString(R.string.response_limit_key), limit)
            this?.apply()
        }
    }

}