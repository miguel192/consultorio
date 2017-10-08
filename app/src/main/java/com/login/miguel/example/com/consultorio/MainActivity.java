package com.login.miguel.example.com.consultorio;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    TextView textTargetUri;
    ImageView targetImage;
    private String[] arraySpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton buttonLoadImage = (ImageButton) findViewById(R.id.Fotopaciente);
        textTargetUri = (TextView) findViewById(R.id.targeturi);
        targetImage = (ImageView) findViewById(R.id.Ohnolafoto);
        this.arraySpinner = new String[] {
                "Dr.Miguel", "Dr.Francisco", "Dra.Torres", "Dr.Gustavo", "Dr.Rosales"
        };
        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
        buttonLoadImage.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public void Tostada(View v) {
        EditText Uno = (EditText)findViewById(R.id.Nombre_Paciente);
        EditText Dos = (EditText)findViewById(R.id.Edad);
        EditText Tres = (EditText)findViewById(R.id.Fecha_Cita);
        EditText Cuatro = (EditText)findViewById(R.id.Hora_Cita);
        EditText V = (EditText)findViewById(R.id.Cel_Paciente);
        EditText VI = (EditText)findViewById(R.id.Correo_Paciente);
        EditText VII = (EditText)findViewById(R.id.Dir_Paciente);
        EditText VIII = (EditText)findViewById(R.id.Padecimiento_Paciente);
        Spinner IX = (Spinner)findViewById(R.id.spinner);
        Toast.makeText(MainActivity.this, "Guardado el paciente "+Uno.getText()+" De "+Dos.getText()+" Años de Edad, "+" El Dia "+
                        Tres.getText()+" A las "+Cuatro.getText()+" Datos de Contacto: Celular:"+V.getText()+" Correo:"+VI.getText()+
                        " Dirección: "+VII.getText()+" Con el Padecimiento: "+VIII.getText()+" Será atendido por el Doctor: "+IX.getSelectedItem().toString(),
                Toast.LENGTH_LONG).show();

    }
}
