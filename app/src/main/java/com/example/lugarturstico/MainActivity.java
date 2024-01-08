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
            { "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/laroma_cacao.png", "https://uealecpeterson.net/turismo/assets/imgs/logos_gifs/lgalaxie.png", "url_imagen_1_3"},
            {"url_imagen_2_1", "url_imagen_2_2"},
            // Agrega más arreglos con las URLs para cada categoría aquí
    };
    private String[][] titulos = {
            {"Aroma de Cacao", "Rancho Galaxi"},
            {"Título 2-1", "Título 2-2"},
            // Agrega más arreglos con los títulos para cada categoría aquí
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
