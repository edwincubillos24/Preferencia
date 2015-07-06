package cappacitate.drinkteam.com.preferencia;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getPreferences(MODE_PRIVATE);
        editor = prefs.edit();
        refreshPrefs();
    }

    public void savePref(View view){

        switch(view.getId()) {
            case R.id.bName:
                EditText campo = (EditText) findViewById(R.id.eName);
                String nombre = campo.getText().toString();
                editor.putString("nombre", nombre);
              //  Toast.makeText(this, "Nombre correcto", Toast.LENGTH_LONG).show();
                break;
            case R.id.bSexo:
                CheckBox sexo = (CheckBox)findViewById(R.id.cSexo);
                Boolean sex = sexo.isChecked();
                editor.putBoolean("sexo",sex);
              //  Toast.makeText(this, "Sexo correcto", Toast.LENGTH_LONG).show();
                break;
            case R.id.bEdad:
                EditText campo2 = (EditText) findViewById(R.id.eNumber);
                String campoStr = campo2.getText().toString();
                if ((campoStr != null && !campoStr.isEmpty())) {
                    try {
                        int value = Integer.valueOf(campoStr);
                        editor.putInt("edad", value);
                //        Toast.makeText(this, "Edad correcta", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
               //         Toast.makeText(this, "Edad No es entero", Toast.LENGTH_LONG).show();
                    }
                }


        }
        editor.commit();
        refreshPrefs();
    }

    public void refreshPrefs(){
        TextView data_char, data_int,data_boolean;
        data_int = (TextView)findViewById(R.id.tEdad);
        String edad = String.valueOf(prefs.getInt("edad",-1));
        data_int.setText(edad);

        data_int = (TextView)findViewById(R.id.tName);
        String nombre = String.valueOf(prefs.getString("nombre",null));
        data_int.setText(nombre);

        data_int = (TextView)findViewById(R.id.tSexo);
        String sexo = String.valueOf(prefs.getBoolean("sexo",false));
        data_int.setText(sexo);
    }

    public void Clear(){
        editor.clear();
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
