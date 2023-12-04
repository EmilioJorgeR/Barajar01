package com.elabeto.barajar01;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final int PALOS = 4;
    final int CARTAS = 13;
    private final ImageButton[][] ibtnCartas = new ImageButton[PALOS][CARTAS];
    private FrameLayout parentLayout;
    private Button btnBarajar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentLayout = findViewById(R.id.frameLayout); // Asigna el LinearLayout de tu XML
        btnBarajar = findViewById(R.id.button);

        crearBaraja();

        btnBarajar.setOnClickListener(bBarajarOnClickListener);
    }

    private void crearBaraja() {

        String nombreCarta = null;

        // Ejemplo de creación e inicialización de ImageButton en una matriz
        for (int i = 0; i < PALOS; i++) {
            for (int j = 0; j < CARTAS; j++) {
                ibtnCartas[i][j] = new ImageButton(this);
                ibtnCartas[i][j].setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                ));
                ibtnCartas[i][j].setScaleType(ImageView.ScaleType.CENTER_CROP);
                if (i== 0) nombreCarta = "c_"+ (j + 1);
                if (i== 1) nombreCarta = "d_"+ (j + 1);
                if (i== 2) nombreCarta = "p_"+ (j + 1);
                if (i== 3) nombreCarta = "d_"+ (j + 1);

                int resourceId = getResources().getIdentifier(nombreCarta, "drawable", getPackageName());

                // Asigna la imagen al ImageButton si se encontró el recurso
                if (resourceId != 0) {
                    ibtnCartas[i][j].setImageResource(resourceId);
                    Log.e("log", "Recurso encontrado para " + nombreCarta);

                    ibtnCartas[i][j].setVisibility(View.VISIBLE);
                    // Agrega el ImageButton al layout contenedor
                    parentLayout.addView(ibtnCartas[i][j]);
                } else {
                    // Manejo en caso de que el recurso no se encuentre
                    Log.e("Error", "Recurso no encontrado para " + nombreCarta);
                }


                // Puedes agregar eventos onClick u otras configuraciones aquí si es necesario
                ibtnCartas[i][j].setOnClickListener(ibtnCartasOnClickListener);

            }
        }
    }

    static class DelayFunction {

        public static void delay(int milliseconds) {
            try {
                Thread.sleep(milliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Create OnClickListener
    private final View.OnClickListener bBarajarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // invisivilizar el resto de las cartas
            for (int i = 0; i < PALOS; i++) {
                for (int j = 0; j < CARTAS; j++) {
                    ibtnCartas[i][j].setVisibility(View.VISIBLE);
                    // DelayFunction.delay(100);
                    ibtnCartas[i][j].setVisibility(View.INVISIBLE);
                }
            }
            int x = (int) (Math.random() * 4);
            int y = (int) (Math.random() * 13);
            ibtnCartas[x][y].setVisibility(View.VISIBLE);
            Log.e("Error", "OnClick Boton "+ x + y);

        }
    };

    private final View.OnClickListener ibtnCartasOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ImageButton ib = (ImageButton) v;
            ib.setVisibility(View.INVISIBLE);
        }
    };
}

