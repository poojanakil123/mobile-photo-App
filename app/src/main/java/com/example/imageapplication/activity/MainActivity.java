package com.example.imageapplication.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.imageapplication.R;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    EditText etUrl;
    ImageView ivResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUrl = findViewById(R.id.et_url);
        ivResult = findViewById(R.id.iv_result);
    }

    public void onClick(View view) {
        String urlLink = etUrl.getText().toString();
        if (urlLink.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter url !", Toast.LENGTH_SHORT).show();
        } else {
            LoadImage loadImage = new LoadImage(ivResult);
            loadImage.execute(urlLink);
        }
    }



    private class LoadImage extends AsyncTask<String,Void, Bitmap>{
        ImageView imageView;

        public LoadImage(ImageView ivResult) {
            this.imageview = ivResult;
        }

        protected  Bitmap doInBackground(String...strings){
                String urlLink = strings[0];
                Bitmap bitmap = null;
            inputStream inputStream = null;
            try {
                InputStream inputStream = new java.net.URL(urlLink).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            bitmap = BitmapFactory.decodeStream(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        return bitmap;
    }
    protected void onPostExecute(Bitmap bitmap) {
        ivResult.setImageBitmap(bitmap);
    }
}
