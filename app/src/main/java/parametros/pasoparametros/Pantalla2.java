package parametros.pasoparametros;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Pantalla2 extends Activity {

    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);

        Bundle b = getIntent().getExtras();
        if(b!=null){
            String nombre = b.getString("Nombre");
            String sexo = b.getString("Sexo");
            String carnet = b.getString("carnet");
            String valor =  b.getString("valoracion");
            String puntuacion =  b.getString("seekBar");
            TextView texto = (TextView) findViewById(R.id.mensage);
            TextView texto1 = (TextView) findViewById(R.id.textoCarnet);
            TextView texto2 = (TextView) findViewById(R.id.textoValor);
            TextView texto3 = (TextView) findViewById(R.id.textoSeek);
            texto.setText("Hola "+sexo+" "+nombre+", tus valoraciones sobre el carnet de conducir " +
                    "son las siguientes:");
            texto1.setText("Tienes carnet: "+ carnet);
            texto2.setText("Le has puesto una valoracion de: "+ valor);
            texto3.setText("Le has puesto una puntuacion de: "+ puntuacion);

        }
        Button sigue = (Button) findViewById(R.id.Aceptar);
        sigue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cierraConsulta(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pantalla2, menu);
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

    public void cierraConsulta(View v){


        Intent i;
        i = new Intent();
        int edadUsuario = 0;
        EditText edadEditText= (EditText)findViewById(R.id.Edad);
        try{
            edadUsuario =Integer.parseInt(edadEditText.getText().toString());
            if(edadUsuario<18) {
                i.putExtra("Edad", "Tienes " + edadUsuario + " años, ATENCIÓN CONTROL PARENTAL, NO HAY NADA QUE VER AQUÍ ¿VALE?");
                setResult(RESULT_OK, i);
                finish();
            }else if (edadUsuario >= 18 && edadUsuario < 25){
                i.putExtra("Edad", "Tienes " + edadUsuario + " años, ya eres mayor de edad.");
                setResult(RESULT_OK, i);
                finish();
            }else if (edadUsuario > 25 || edadUsuario == 35){
                i.putExtra("Edad", "Tienes " + edadUsuario + " años, ya estás en la flor de la vida.");
                setResult(RESULT_OK, i);
                finish();
            }else if(edadUsuario >= 35){
                i.putExtra("Edad", "Tienes " + edadUsuario + " años, AY AY AY.");
                setResult(RESULT_OK, i);
                finish();
            }
        }catch (Exception e){
            Toast.makeText(this, "Introduce todos los datos", Toast.LENGTH_SHORT).show();

        }




    }
}
