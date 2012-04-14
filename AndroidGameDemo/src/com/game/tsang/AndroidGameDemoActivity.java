package com.game.tsang;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AndroidGameDemoActivity extends ListActivity 
    implements OnItemClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ListView lv = getListView();
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.gameLists, R.layout.game_text);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        String selectedGame = (String) parent.getItemAtPosition(position);
        Intent intent = new Intent();
        if ("Key Test".equals(selectedGame)) {
            intent.setClass(this, KeyDemo.class);
        }
        else if ("Multitouch Test".equals(selectedGame)) {
            intent.setClass(this, MultiTouch.class);
        }
        else if ("Accelerometer Test".equals(selectedGame)) {
            intent.setClass(this, AccelerometerDemo.class);
        }
        else if ("Load File".equals(selectedGame)) {
            intent.setClass(this, LoadingFile.class);
        }
        else if ("Sound Effect".equals(selectedGame)) {
            intent.setClass(this, SoundEffect.class);
        }
        else if ("Media Player".equals(selectedGame)) {
            intent.setClass(this, MediaPlayerDemo.class);
        }
        else if ("RenderView Demo".equals(selectedGame)) {
            intent.setClass(this, RenderViewDemo.class);
        }
        else if ("Simple Shape Demo".equals(selectedGame)) {
            intent.setClass(this, SimpleShapeDemo.class);
        }
        intent.putExtra("selected", selectedGame);
        startActivity(intent);
    }
    
}