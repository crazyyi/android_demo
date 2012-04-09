package com.google.tsang;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class AndroiddemoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        FragmentManager fm = getFragmentManager();
    }
}