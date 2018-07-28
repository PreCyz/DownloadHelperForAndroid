package android.gawa.androiddownloadhelper

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.preference.*
import android.view.MenuItem
import androidx.core.app.NavUtils

/**
 * A [PreferenceActivity] that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 *
 * See [Android Design: Settings](http://developer.android.com/design/patterns/settings.html)
 * for design guidelines and the [Settings API Guide](http://developer.android.com/guide/topics/ui/settings.html)
 * for more information on developing a Settings UI.
 */
class SettingsActivity : AppCompatPreferenceActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            if (!super.onMenuItemSelected(featureId, item)) {
                NavUtils.navigateUpFromSameTask(this)
                finish()
            }
            return true
        }
        return super.onMenuItemSelected(featureId, item)
    }

    override fun onIsMultiPane(): Boolean {
        return isXLargeTablet(this)
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onBuildHeaders(target: List<PreferenceActivity.Header>) {
        loadHeadersFromResource(R.xml.pref_headers, target)
    }

    override fun isValidFragment(fragmentName: String): Boolean {
        return PreferenceFragment::class.java.name == fragmentName
                || FilterPreferenceFragment::class.java.name == fragmentName
                || ServerPreferenceFragment::class.java.name == fragmentName
                || ApiPreferenceFragment::class.java.name == fragmentName
                || OtherPreferenceFragment::class.java.name == fragmentName
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class ApiPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_torrent_api)
            setHasOptionsMenu(true)

            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_torrent_api_url_key)))
            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_torrent_api_limit_key)))
            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_torrent_api_page_key)))
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            if (id == android.R.id.home) {
                startActivity(Intent(activity, SettingsActivity::class.java))
                return true
            }
            return super.onOptionsItemSelected(item)
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class ServerPreferenceFragment : PreferenceFragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_server)
            setHasOptionsMenu(true)

            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_server_url_key)))
            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_server_protocol_key)))
            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_server_username_key)))
            bindPreferenceSummaryToSecretValue(findPreference(getString(R.string.pref_server_password_key)), context)
            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_server_download_folder_key)))
            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_server_api_info_key)))
            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_server_task_creation_method_key)))
            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_server_torrent_url_type_key)))
        }
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            if (id == android.R.id.home) {
                startActivity(Intent(activity, SettingsActivity::class.java))
                return true
            }
            return super.onOptionsItemSelected(item)
        }

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class FilterPreferenceFragment : PreferenceFragment() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_filters)
            setHasOptionsMenu(true)

            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_filter_torrent_age_key)))
            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_filter_max_file_size_key)))
            bindPreferenceSummaryToBooleanValue(findPreference(getString(R.string.pref_filter_repeat_download_key)), context)
            bindPreferenceSummaryToBooleanValue(findPreference(getString(R.string.pref_filter_handle_duplicates_key)), context)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            if (id == android.R.id.home) {
                startActivity(Intent(activity, SettingsActivity::class.java))
                return true
            }
            return super.onOptionsItemSelected(item)
        }

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    class OtherPreferenceFragment : PreferenceFragment() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            addPreferencesFromResource(R.xml.pref_other)
            setHasOptionsMenu(true)

            bindPreferenceSummaryToStringValue(findPreference(getString(R.string.pref_other_live_track_interval_key)))
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val id = item.itemId
            if (id == android.R.id.home) {
                startActivity(Intent(activity, SettingsActivity::class.java))
                return true
            }
            return super.onOptionsItemSelected(item)
        }
    }

    companion object {

        /**
         * A preference value change listener that updates the preference's summary
         * to reflect its new value.
         */
        private val sBindPreferenceSummaryToValueListener = Preference.OnPreferenceChangeListener { preference, value ->
            val stringValue = value.toString()

            if (preference is ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                val listPreference = preference
                val index = listPreference.findIndexOfValue(stringValue)

                // Set the summary to reflect the new value.
                preference.setSummary(
                        if (index >= 0)
                            listPreference.entries[index]
                        else
                            null)

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.summary = stringValue
            }
            true
        }

        /**
         * Helper method to determine if the device has an extra-large screen. For
         * example, 10" tablets are extra-large.
         */
        private fun isXLargeTablet(context: Context): Boolean {
            return context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_XLARGE
        }

        /**
         * Binds a preference's summary to its value. More specifically, when the
         * preference's value is changed, its summary (line of text below the
         * preference title) is updated to reflect the value. The summary is also
         * immediately updated upon calling this method. The exact display format is
         * dependent on the type of preference.

         * @see .sBindPreferenceSummaryToValueListener
         */
        private fun bindPreferenceSummaryToStringValue(preference: Preference) {
            preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.context)
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, sharedPreferences.getString(preference.key, ""))
        }

        private fun bindPreferenceSummaryToBooleanValue(preference: Preference, context: Context) {
            val booleanPrefKeys = listOf(
                    context.getString(R.string.pref_filter_repeat_download_key),
                    context.getString(R.string.pref_filter_handle_duplicates_key)
            )
            preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.context)
            if (booleanPrefKeys.contains(preference.key)) {
                sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, sharedPreferences.getBoolean(preference.key, false))
            }
        }

        private fun bindPreferenceSummaryToSecretValue(preference: Preference, context: Context) {
            preference.onPreferenceChangeListener = sBindPreferenceSummaryToValueListener

            var summary = ""
            if (preference.key == context.getString(R.string.pref_server_password_key)) {
                summary = context.getString(R.string.pref_server_password_summary)
            }
            sBindPreferenceSummaryToValueListener.onPreferenceChange(preference, summary)
        }
    }
}
