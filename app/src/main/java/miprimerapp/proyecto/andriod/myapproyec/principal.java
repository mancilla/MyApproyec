package miprimerapp.proyecto.andriod.myapproyec;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Principal;


public class principal extends ActionBarActivity {
    EditText codigo, marca, año, modelo, color, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        codigo = (EditText) findViewById(R.id.cod);
        marca = (EditText) findViewById(R.id.mar);
        año = (EditText) findViewById(R.id.añ);
        modelo = (EditText) findViewById(R.id.mod);
        color = (EditText) findViewById(R.id.col);
        descripcion = (EditText) findViewById(R.id.desc);
    }

    public void alta(View v) {
        segunda admin = new segunda(this, "agencia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Codigo = codigo.getText().toString();
        String Marca = marca.getText().toString();
        String Año = año.getText().toString();
        String Modelo = modelo.getText().toString();
        String Color = color.getText().toString();
        String Descripcion = descripcion.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("codigo", Codigo);
        registro.put("marca", Marca);
        registro.put("año", Año);
        registro.put("modelo", Modelo);
        registro.put("color", Color);
        registro.put("descripcion", Descripcion);

        bd.insert("agencia", null, registro);
        bd.close();

        codigo.setText("");
        marca.setText("");
        año.setText("");
        modelo.setText("");
        color.setText("");
        descripcion.setText("");

        Toast.makeText(this, "Se agrego un nuevo vehiculo a lista", Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {

        segunda admin = new segunda(this, "agencia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Codigo = codigo.getText().toString();

        Cursor fila = bd.rawQuery("select codigo, marca, año, modelo, color, descripcion  from agencia where codigo=" + Codigo, null);
        if (fila.moveToFirst()) {
            codigo.setText(fila.getString(0));
            marca.setText(fila.getString(1));
            año.setText(fila.getString(2));
            modelo.setText(fila.getString(3));
            color.setText(fila.getString(4));
            descripcion.setText(fila.getString(5));

        } else {
            Toast.makeText(this, "No existe el registro", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }


    public void baja(View v) {

        segunda admin = new segunda(this, "agencia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Codigo = codigo.getText().toString();

        int cant = bd.delete("agencia", "codigo=" + Codigo, null);
        bd.close();

        codigo.setText("");
        marca.setText("");
        año.setText("");
        modelo.setText("");
        color.setText("");
        descripcion.setText("");

        if (cant == 1) {
            Toast.makeText(this, "Se elimino el registro", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el registro", Toast.LENGTH_SHORT).show();
        }
    }


    public void modificacion(View v) {
        segunda admin = new segunda(this, "agencia", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String Codigo = codigo.getText().toString();
        String Marca = marca.getText().toString();
        String Año = año.getText().toString();
        String Modelo = modelo.getText().toString();
        String Color = color.getText().toString();
        String Descripcion = descripcion.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("codigo", Codigo);
        registro.put("marca", Marca);
        registro.put("año", Año);
        registro.put("modelo", Modelo);
        registro.put("color", Color);
        registro.put("descripcion", Descripcion);


        int cant = bd.update("agencia", registro, "codigo=" + Codigo, null);
        bd.close();

        if (cant == 1) {
            Toast.makeText(this, "Se modifico el registro del auto", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el registro del auto", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpia(View v) {
        codigo.setText("");
        marca.setText("");
        año.setText("");
        modelo.setText("");
        color.setText("");
        descripcion.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(principal.this, acerca.class);
            startActivity(intent);
        } else if (id == R.id.salir) {

            finish();

        }
        return super.onOptionsItemSelected(item);

    }
}

