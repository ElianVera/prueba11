package com.example.lugarturstico;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.Target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class DatosAdapter extends RecyclerView.Adapter<DatosAdapter.DatosViewHolder> {

    private Context context;
    private String[] urls;
    private String[] titulos;

    public DatosAdapter(Context context, String[] urls, String[] titulos) {
        this.context = context;
        this.urls = urls;
        this.titulos = titulos;
    }

    @NonNull
    @Override
    public DatosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new DatosViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull DatosViewHolder holder, int position) {
        holder.bindData(urls[position], titulos[position]);
    }

    @Override
    public int getItemCount() {
        return urls.length;
    }

    static class DatosViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView titleTextView;

        public DatosViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2); // Asegúrate de que el ID coincida con el del ImageView en el layout item_layout.xml
            titleTextView = itemView.findViewById(R.id.titulo); // Asegúrate de que el ID coincida con el del TextView en el layout item_layout.xml
        }

        public void bindData(String url, String title) {
            Glide.with(itemView)
                    .load(url)
                    .into(imageView);
            titleTextView.setText(title);

            Glide.with(itemView)
                    .load(url)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.e("Glide", "Error loading image: " + e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.d("Glide", "Image loaded successfully");
                            return false;
                        }
                    })
                    .into(imageView);

        }
    }
}
