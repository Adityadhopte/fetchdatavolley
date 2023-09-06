package com.example.finaltestshujabits;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {


     DBHelper dbHelper;
     ListView listView;
     SimpleCursorAdapter cursorAdapter;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listView);

        Button chooseImageButton = findViewById(R.id.chooseImageButton);
        chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                Bitmap selectedImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                saveImage(selectedImageBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveImage(Bitmap imageBitmap) {


        Image image = new Image(imageBitmap, "Image Name");
        Log.d("Upload Image", "Before image insertion");

        long result = dbHelper.insertImage(image);

        Log.d("Upload Image", "After image insertion");


        if (result != -1) {
            Toast.makeText(this, "Image saved successfully.", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Error saving image.", Toast.LENGTH_SHORT).show();
        }
    }
}

