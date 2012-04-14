package com.game.tsang;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.TextView;

public class KeyDemo extends Activity implements OnKeyListener {

    StringBuilder sb = new StringBuilder();
    TextView textView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("Press keys!");
        textView.setOnKeyListener(this);
        textView.setFocusableInTouchMode(true);
        textView.requestFocus();
        setContentView(textView);
    }
    
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        sb.setLength(0);
        switch(event.getAction()) {
        case KeyEvent.ACTION_DOWN:
            sb.append("down, ");
            break;
        case KeyEvent.ACTION_UP:
            sb.append("up, ");
            break;
        }
        
        sb.append(event.getKeyCode());
        sb.append(", ");
        sb.append((char) event.getUnicodeChar());
        String text = sb.toString();
        Log.d("KeyTest", text);
        textView.setText(text);
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
        return false;
        else
        return true;
    }

}
