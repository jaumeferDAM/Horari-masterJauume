package com.example.alumnedam.horari;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String horaDelSistema;
        String grupo = "A1";


        String idHorari, grup, codiAsignatura, horaInici, horaFi, diaSetmana, profesor, aula;
        //Crear la Base de datos
        SqlActivity sql = new SqlActivity(this, "Jaume", null, 1);
        //Crear el objeta base de datos
        SQLiteDatabase db = sql.getWritableDatabase();


        //Cogemos la hora del sistema y le damos el formato que queremos.
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        horaDelSistema = format.format(calendar.getTime());


        //Si la base de datos no esta ejecuta la sentencia select
        if(db != null){
            String[] args = new String[] {horaDelSistema};
            Cursor c = db.rawQuery("SELECT * FROM tablaHorarios WHERE ? BETWEEN hora_inici AND hora_fi", args);
            if(c.moveToFirst()){
                do{
                    idHorari = c.getString(0);
                    grup = c.getString(1);
                    codiAsignatura = c.getString(2);
                    horaInici = c.getString(3);
                    horaFi = c.getString(4);
                    diaSetmana = c.getString(5);
                    profesor = c.getString(6);
                    aula = c.getString(7);
                }while(c.moveToNext());


                Toast.makeText(this, ""+idHorari+" "+grup+" "+codiAsignatura+" "+horaInici+" "+horaFi+" "+diaSetmana+" "+profesor
                        +" "+aula, Toast.LENGTH_SHORT).show();
            }
        }

    }



    @Override
    public void onClick(View view) {
        intent = new Intent(this, HorariActivity.class);
        TextView tvNom = (TextView) findViewById(R.id.etNom);
        Spinner spin = (Spinner) findViewById(R.id.spinnerCurs);
        Spinner fondo = (Spinner)findViewById(R.id.spinnerFons);
        Spinner font = (Spinner)findViewById(R.id.spinnerfont);
        if (!(tvNom.getText().toString().equals(""))) {

        } else {
            Toast.makeText(this, "Introdueix els valors", Toast.LENGTH_SHORT).show();
        }
    }


}
