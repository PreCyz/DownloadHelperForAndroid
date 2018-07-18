package android.gawa.androiddownloadhelper

import android.R
import android.app.ListActivity
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.SimpleCursorAdapter
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader

class DownloadActivity : ListActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    // These are the Contacts rows that we will retrieve
    internal val PROJECTION = arrayOf(
            ContactsContract.Data._ID,
            ContactsContract.Data.DISPLAY_NAME)

    // This is the select criteria
    internal val SELECTION = "((" +
            ContactsContract.Data.DISPLAY_NAME + " NOTNULL) AND (" +
            ContactsContract.Data.DISPLAY_NAME + " != '' ))"


    // This is the Adapter being used to display the list's data
    private lateinit var mAdapter: SimpleCursorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a progress bar to display while the list loads
        val progressBar = ProgressBar(this)
        progressBar.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER)
        progressBar.isIndeterminate = true
        listView.emptyView = progressBar

        // Must add the progress bar to the root of the layout
        val root: ViewGroup = findViewById(android.R.id.content)
        root.addView(progressBar)

        // For the cursor adapter, specify which columns go into which views
        val fromColumns: Array<String> = arrayOf(ContactsContract.Data.DISPLAY_NAME)
        val toViews = intArrayOf(android.R.id.text1) // The TextView in simple_list_item_1

        // Create an empty adapter we will use to display the loaded data.
        // We pass null for the cursor, then update it in onLoadFinished()
        mAdapter = SimpleCursorAdapter(this,
                R.layout.simple_list_item_1, null,
                fromColumns, toViews, 0)
        listAdapter = mAdapter

        // Prepare the loader. Either re-connect with an existing one,
        // or start a new one.
        //loaderManager.initLoader(0, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(this, ContactsContract.Data.CONTENT_URI,
                PROJECTION, SELECTION, null, null)
    }

    // Called when a previously created loader has finished loading
    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
        // Swap the new cursor in. (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data)
    }

    // Called when a previously created loader is reset, making the data unavailable
    override fun onLoaderReset(loader: Loader<Cursor>) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed. We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null)
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        // Do something when a list item is clicked
    }

}
