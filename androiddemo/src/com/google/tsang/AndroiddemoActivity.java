package com.google.tsang;

import android.app.DialogFragment;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Main entry
 * 
 * @author Yi Zeng
 *
 */
public class AndroiddemoActivity extends ListActivity 
    implements OnItemClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = getListView();
        // Uses the string-array in strings.xml to generate contents for the list
        ListAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.list_content, R.layout.list_control);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        final String selectedItem = (String) parent.getItemAtPosition(position);
        final DialogFragment galleryDialog = 
                GalleryDialog.getInstance(R.string.dialog_title, selectedItem);
        galleryDialog.show(getFragmentManager(), "Gallery Dialog");
    }
    
}