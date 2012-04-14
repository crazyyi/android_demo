package com.game.tsang;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class LoadingFile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AssetManager manager = getAssets();
        TextView textView = new TextView(this);
        setContentView(textView);
        InputStream inputStream = null;
        try {
            inputStream = manager.open("texts/test.txt");
            String text = loadTextFromFile(inputStream);
            textView.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    textView.setText("Couldn't close file.");
                }
        }
    }

    private String loadTextFromFile(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[4096];
        int len = 0;
        while ((len = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes, 0, len);
        }
        return new String(outputStream.toByteArray(), "UTF8");
    }
    
    
}
