package com.example.recyclerviewwithdifferentcardview.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewwithdifferentcardview.MainActivity;
import com.example.recyclerviewwithdifferentcardview.R;
import com.example.recyclerviewwithdifferentcardview.models.Ads;
import com.example.recyclerviewwithdifferentcardview.models.Facebook;
import com.example.recyclerviewwithdifferentcardview.models.Item;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FacebookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Item> items;
    private Bitmap currentImage;
    static OnIntentReceived mActivityContext;
    public FacebookAdapter(List<Item> items, OnIntentReceived ActivityContext) {
        this.items = items;
        mActivityContext = ActivityContext;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // ads = 0, facebook = 1
        if(viewType == 0){
            return new adsViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.ads_cardview,
                            parent,
                            false
                    )
            );
        } else{
            return new facebookViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.facebook_cardview,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == 0){
            Ads ads = (Ads) items.get(position).getObject();
            ((adsViewHolder) holder).setAds(ads);
        }else {
            Facebook facebook = (Facebook) items.get(position).getObject();
            ((facebookViewHolder) holder).setFacebook(facebook);
            ((facebookViewHolder) holder).facebookBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }


    static class adsViewHolder extends RecyclerView.ViewHolder{

        private final ImageView adsImage;
        private final TextView adsTitle;
        public adsViewHolder(@NonNull View itemView) {
            super(itemView);
            adsImage = itemView.findViewById(R.id.ads_image_view);
            adsTitle = itemView.findViewById(R.id.ads_text_view);
        }
        void setAds(Ads ads){
            adsImage.setImageResource(ads.getAdsImage());
            adsTitle.setText(ads.getAdsTitle());
        }
    }
    static class facebookViewHolder extends RecyclerView.ViewHolder{

        private final TextView facebookTextView;
        private final ImageView faceImageView;
        private final Button facebookBT;

        Context context;
        public facebookViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            facebookTextView = itemView.findViewById(R.id.facebook_text_view);
            faceImageView = itemView.findViewById(R.id.face_IV);
            facebookBT = itemView.findViewById(R.id.facebook_BT);
        }
        void setFacebook(Facebook facebook){
            facebookTextView.setText(facebook.getFaceBookTitle());
            faceImageView.setImageResource(facebook.getImage());

        }

    }

}
