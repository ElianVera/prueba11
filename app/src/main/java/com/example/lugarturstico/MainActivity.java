package com.example.lugarturstico;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RecyclerView recyclerView;
    private ImageView imageView;
    private TextView categoria;
    private String[][] urls = {
            { "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/laroma_cacao.png", "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lgalaxie.png", "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lbravo.png", "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/ldelrio.png", "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lcosta.jpg","https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lcrespotel.jpg"},
            {"https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lcafe_tinto.png", "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lfancy_mint.png","https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lsweet.png", "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/ltoro_cafe.png"},
            {"https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lronda_quevedena.jpeg","https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lsanjose_iglesia.jpg","https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lparque_madre.jpg","https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lmuseo_municipal.jpg"},
            {"https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lboho.png","https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lcoco_fresh.png","https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lcontainer.png","https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lparadise.jpeg"},
            {"https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lquadra.png","https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lpaseo_shopping.jpeg","",""}

    };
    private String[][] titulos = {
            {"Aroma de Cacao", "Rancho Galaxi","Hotel Bravo","Hotel del Río","Costa del Sol","Crespotel"},
            {"café & Tinto", "Fancy Mint Coffe & Cake Shop", "Sweet Land", "Toro Café"},
            {"Ronda Quevedeña","Iglesia San José", "Parque de la Madre", "Museo Municipal"},
            {"Boho Garden Plaza","Coco Fresh","El Container","Paradise"},
            {"LAQUADRA","Paseo Shopping Quevedo", "Shopping Center", "Victoria Ventura"}
    };
    private String[] datos = {"Alojamiento y Hoteles", "Alimentación y Bebidas", "Atractivos Culturales", "Esparcimientos", "Compras"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner cmbOpciones = findViewById(R.id.spinner);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datos);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbOpciones.setAdapter(adaptador);
        cmbOpciones.setOnItemSelectedListener(this);

        recyclerView = findViewById(R.id.datos);
        imageView = findViewById(R.id.imageView2);
        categoria = findViewById(R.id.categoria);
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        String categoriaSeleccionada = parent.getItemAtPosition(position).toString();
        categoria.setText("Categoría: " + categoriaSeleccionada);

        int index = java.util.Arrays.asList(datos).indexOf(categoriaSeleccionada);
        if (index != -1) {
            DatosAdapter adapter = new DatosAdapter(this, urls[index], titulos[index]);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }


    private void cargarImagen(String url) {
        Glide.with(this)
                .load(url)
                .into(imageView);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        categoria.setText("");
    }
}
