package com.game.tsang;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MultiTouch extends Activity implements OnTouchListener {

    private static final int TOUCH_POINT_NUMBER = 10;
    StringBuilder builder = new StringBuilder();
    TextView textView;
    List<TouchPoint> touchPoints = new ArrayList<TouchPoint>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("Touch and drag(Multiple fingers supported)");
        textView.setOnTouchListener(this);
        setContentView(textView);
        
        for (int i = 0; i< TOUCH_POINT_NUMBER; i++) {
            touchPoints.add(new TouchPoint());
        }
        updateTextView();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
        MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        int pointerCount = event.getPointerCount();
        for (TouchPoint tp : touchPoints) {
            int i = touchPoints.indexOf(tp);
            if ( i >= pointerCount) {
                tp.touched = false;
                tp.id = -1;
                continue;
            }
            if (event.getAction() != MotionEvent.ACTION_MOVE&& i != pointerIndex) {
                // if it's an up/down/cancel/out event, mask the id to see if we should
                // process it for this touch point
                continue;
            }
            int pointerId = event.getPointerId(i);
            switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                tp.touched = true;
                tp.id = pointerId;
                tp.x = (int) event.getX(i);
                tp.y = (int) event.getY(i);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
                tp.touched = false;
                tp.id = -1;
                tp.x = (int) event.getX(i);
                tp.y = (int) event.getY(i);
                break;
            case MotionEvent.ACTION_MOVE:
                tp.touched = true;
                tp.id = pointerId;
                tp.x = (int) event.getX(i);
                tp.y = (int) event.getY(i);
                break;
            }
        }
        updateTextView();
        return true;
    }

    private void updateTextView() {
        builder.setLength(0);
        for (TouchPoint tp : touchPoints) {
            builder.append(tp.touched);
            builder.append(", ");
            builder.append(tp.id);
            builder.append(", ");
            builder.append(tp.x);
            builder.append(", ");
            builder.append(tp.y);
            builder.append("\n");
        }
        textView.setText(builder.toString());
    }

    private class TouchPoint {
        float x;
        float y;
        int id = -1;
        boolean touched;
        
        public TouchPoint() {
            
        }
    }
}
