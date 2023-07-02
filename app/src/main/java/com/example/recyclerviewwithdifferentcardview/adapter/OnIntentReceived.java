package com.example.recyclerviewwithdifferentcardview.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;

import java.util.concurrent.atomic.AtomicReference;

public interface OnIntentReceived {
    AtomicReference<Bitmap> CaptureImage();
}
