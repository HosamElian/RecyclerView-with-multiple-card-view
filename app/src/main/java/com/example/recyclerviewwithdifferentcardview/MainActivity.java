package com.example.recyclerviewwithdifferentcardview;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewwithdifferentcardview.adapter.FacebookAdapter;
import com.example.recyclerviewwithdifferentcardview.adapter.OnIntentReceived;
import com.example.recyclerviewwithdifferentcardview.models.Ads;
import com.example.recyclerviewwithdifferentcardview.models.Facebook;
import com.example.recyclerviewwithdifferentcardview.models.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity  implements OnIntentReceived {

    private static final int REQUEST_CAMERA_PERMISSION_CODE = 1;
//    private static final int REQUEST_IMAGE_CAPTURE_CODE = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookAdapter facebookAdapter;

        Ads ads1 = new Ads("", R.drawable.coca);
        Ads ads2 = new Ads("", R.drawable.naturaljuice);

        Facebook facebook1 = new Facebook("First facebook Post", R.drawable.ic_launcher_foreground);
        Facebook facebook2 = new Facebook("Second facebook Post", R.drawable.ic_launcher_foreground);

        List<Item> items = new ArrayList<>();

        items.add(new Item(0,ads1));
        items.add(new Item(0,ads2));
        items.add(new Item(1,facebook1));
        items.add(new Item(1,facebook2));

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        facebookAdapter = new FacebookAdapter( items, this);
        recyclerView.setAdapter(facebookAdapter);


    }

    public AtomicReference<Bitmap> CaptureImage(){
        AtomicReference<Bitmap> rImage = new AtomicReference<>();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION_CODE);
        }else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ActivityResultLauncher<Intent> takeImage = registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Bundle imageDate = result.getData().getExtras();
                            Bitmap image = (Bitmap) imageDate.get("data");
                            if(null != image){
                                Toast.makeText(this, "woks", Toast.LENGTH_SHORT).show();
                                rImage.set(image);
                            }
                        }
                    }
            );
            takeImage.launch(intent);

        }
        return rImage;
    }


}
