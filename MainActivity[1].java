package com.example.hotcakes.tienda_mascotas;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLInput;

public class MainActivity extends AppCompatActivity {
    private EditText editMascota, editPadecimiento, editColor, editID;
    private Button btnAlta, btnConsulta;
    private Base objBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        objBD = new Base(this, "Mascotas",null, 1);
        editID = findViewById(R.id.editID);
        editMascota = findViewById(R.id.editMascota);
        editPadecimiento = findViewById(R.id.editPadecimiento);
        editColor = findViewById(R.id.editColor);
        btnAlta = findViewById(R.id.brnAlta);
        btnConsulta = findViewById(R.id.btnConsulta);

    }

    //ALTAS
    public void agregar(View view) {
        objBD = new Base(this, "Mascotas", null, 1);
        SQLiteDatabase bd = objBD.getWritableDatabase();
        SQLiteDatabase lectura = objBD.getReadableDatabase();


        String aidi = editID.getText().toString();
        int id = Integer.parseInt(aidi);
        String nombre = editMascota.getText().toString();
        String padecimiento = editPadecimiento.getText().toString();
        String color = editColor.getText().toString();

        Cursor fila = lectura.rawQuery("select Nombre,Padecimiento,Color from animales where idMascota = " + id, null);

        if (!aidi.isEmpty() && !nombre.isEmpty() && !padecimiento.isEmpty() && !color.isEmpty()) {

            if (fila.moveToFirst()){
                Toast.makeText(this, "ID ya registrado", Toast.LENGTH_LONG).show();
                lectura.close();
                editColor.setText("");
                editPadecimiento.setText("");
                editMascota.setText("");

            }
            else {
                //Toast.makeText(this, "No existe esa mascota", Toast.LENGTH_LONG).show();
                ContentValues registro = new ContentValues();
                registro.put("idMascota", id);
                registro.put("Nombre", nombre);
                registro.put("Padecimiento", padecimiento);
                registro.put("Color", color);

                bd.insert("animales", null, registro);
                bd.close();

                editID.setText("");
                editColor.setText("");
                editPadecimiento.setText("");
                editMascota.setText("");

                Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this,"Llena todos los datos, por favor", Toast.LENGTH_SHORT).show();
        }
    }
//aaaaa
    public void buscar(View view){
        objBD = new Base(this, "Mascotas",null, 1);
        SQLiteDatabase bd = objBD.getReadableDatabase();
        String aidi = editID.getText().toString();
        int id = Integer.parseInt(aidi);

        if (!aidi.isEmpty()){
            Cursor fila = bd.rawQuery("select Nombre,Padecimiento,Color from animales where idMascota = " + id, null);
            if (fila.moveToFirst()){
                editMascota.setText(fila.getString(0));
                editPadecimiento.setText(fila.getString(1));
                editColor.setText(fila.getString(2));
                bd.close();
            }
            else{
                Toast.makeText(this, "No existe esa mascota", Toast.LENGTH_LONG).show();
            }

        }
        else {
            Toast.makeText(this, "Debes Escribir el ID de la Mascota", Toast.LENGTH_LONG).show();
        }

    }
}
