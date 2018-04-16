package arduinosensors.example.com.maps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends AppCompatActivity {


    EditText edtCliente1;
    EditText edtCliente2;
    EditText edtCliente3;
    EditText edtCliente4;
    EditText edtCliente5;
    EditText edtCliente6;
    EditText edtCliente7;
    EditText edtCliente8;
    Button btnSalvaCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cliente);

        inicializarComponentes();
        funcbtnSalvaCliente();

    }

    private void inicializarComponentes(){

        btnSalvaCliente = (Button) findViewById(R.id.btnSalvaCliente);
        edtCliente1 = (EditText) findViewById(R.id.edtCliente1);
        edtCliente2 = (EditText) findViewById(R.id.edtCliente2);
        edtCliente3 = (EditText) findViewById(R.id.edtCliente3);
        edtCliente4 = (EditText) findViewById(R.id.edtCliente4);
        edtCliente5 = (EditText) findViewById(R.id.edtCliente5);
        edtCliente6 = (EditText) findViewById(R.id.edtCliente6);
        edtCliente7 = (EditText) findViewById(R.id.edtCliente7);
        edtCliente8 = (EditText) findViewById(R.id.edtCliente8);




    }//inicialização dos componentes


    private void funcbtnSalvaCliente(){
        btnSalvaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                toastFromEditText(edtCliente1);
                toastFromEditText(edtCliente2);
                toastFromEditText(edtCliente3);
                toastFromEditText(edtCliente4);
                toastFromEditText(edtCliente5);
                toastFromEditText(edtCliente6);
                toastFromEditText(edtCliente7);
                toastFromEditText(edtCliente8);

            }
        });
    }
    public void toastFromEditText(EditText text) {

        Toast.makeText(Cliente.this, text.getText().toString(), Toast.LENGTH_SHORT).show();
    }


    public void editActions(View view){

        if(findViewById(R.id.edtCliente1) == view ){
            edtCliente1.getText().clear();
        }
        else if(findViewById(R.id.edtCliente2) == view ) {
            edtCliente2.getText().clear();
        }
        else if(findViewById(R.id.edtCliente3) == view ) {
            edtCliente3.getText().clear();
        }
        else if(findViewById(R.id.edtCliente4) == view ) {
            edtCliente4.getText().clear();
        }
        else if(findViewById(R.id.edtCliente5) == view ) {
            edtCliente5.getText().clear();
        }
        else if(findViewById(R.id.edtCliente6) == view ) {
            edtCliente6.getText().clear();
        }
        else if(findViewById(R.id.edtCliente7) == view ) {
            edtCliente7.getText().clear();
        }
        else if(findViewById(R.id.edtCliente8) == view ) {
            edtCliente8.getText().clear();
        }
    }
}
