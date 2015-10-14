package parametros.pasoparametros;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaNombre extends Activity {

    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Paso de Parametros");
        setContentView(R.layout.activity_pantalla_nombre);
        Button continuar = (Button) findViewById(R.id.continuar);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreConsulta(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pantalla_nombre, menu);
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

    public void abreConsulta(View v){
        boolean puedo = false;
        boolean puedo2 = false;
        boolean puedo3 = false;
        Intent i;
        i = new Intent(getApplicationContext(),Pantalla2.class);

        EditText nombre = (EditText) findViewById(R.id.campoNombre);
        if(!nombre.getText().toString().isEmpty()){
            i.putExtra("Nombre", nombre.getText().toString());
            puedo = true;
        }

        RadioButton sexo = (RadioButton) findViewById(R.id.radioHombre);
        RadioButton sexo2 = (RadioButton) findViewById(R.id.radioMujer);

        SeekBar bar = (SeekBar) findViewById(R.id.seekBar);
        RatingBar rating = (RatingBar) findViewById(R.id.valoracion);
        Switch carnet = (Switch) findViewById(R.id.carnet);
        
        i.putExtra("carnet", String.valueOf(carnet.isChecked()));
        i.putExtra("seekBar",String.valueOf(bar.getProgress()));
        
        if(sexo.isChecked()){
            i.putExtra("Sexo", "Sr");
            puedo2 = true;
        }
        if(sexo2.isChecked()){
            i.putExtra("Sexo", "Sra");
            puedo2 = true;
        }
        if(rating.getRating()!=0){
            i.putExtra("valoracion",String.valueOf(rating.getRating()));
            puedo3 = true;
        }

        if(puedo==true&&puedo2==true&&puedo3==true){
            startActivityForResult(i,REQUEST_CODE);
        }else{
            Toast.makeText(this, "Introduce todos los datos", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView texto = (TextView) findViewById(R.id.mensaje);
        if ((this.REQUEST_CODE == requestCode) && (resultCode == RESULT_OK)){
            if(data.hasExtra("Edad")){
                texto.setText(data.getExtras().getString("Edad"));
            }
        }
    }



}
