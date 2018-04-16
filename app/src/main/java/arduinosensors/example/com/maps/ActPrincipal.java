package arduinosensors.example.com.maps;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.GpsStatus;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActPrincipal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;



    // criado para modificar o fragmento do mapa(MapsActivity)
    private android.support.v4.app.FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_principal);

//inicializa os componentes, previamente definidos em variaveis.
        inicializarComponentes();

    }
    //fecha o menu lateral (sempre que clicar em um item essa função fecha o menu lateral novamente)-abaixo


    private void inicializarComponentes(){

        //chamada da toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //chamada do menu lateral
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //chamada para os itens do menu
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //efetua a recuperação do objeto fragmentManager
        fragmentManager = getSupportFragmentManager();
        //responsavel pela transação entre BD e executar o processo e confirmar
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        //define onde ocorrera a transação(onde criei o containeir ira chamar a mapsactivity)
        //nessa linha tambem decidi por renomear a activity ja que sera um fragmento...
        //direito na activity e rafactor->rename->"RENOMEAR"->refactor,se der erro aparecera na notificação para dar "Do Factor"
        //isso renomeara em TODOS os trechos onde o codigo e usado
        transaction.add(R.id.container, new MapsFragment(),"MapsFragment");
        //confirmo se a transação foi bem sucedida;
        transaction.commitAllowingStateLoss();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);



    }//inicialização dos componentes

    @Override
    public void onBackPressed() {

            super.onBackPressed();//sair da aplicação

    }//controle do botão de voltar

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();


        //seleciona pelo id do botão qual sera a chamada feita(fragments)
        // feito exatamente igual a parte de cima, o que muda:
        // não precisa recuperar pois o mesmo foi feito mais acima(na primeira declaração)
        // o add vira replace(1 activity varios fragments)
        // a transação vira somente commit ja que sera apenas uma substituição
        switch (id){
            case R.id.nav_exemplo :
/*
//responsavel pela transação entre BD e executar o processo e confirmar
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
//define onde ocorrera a transação(onde criei o containeir ira chamar a mapsactivity)
                //nessa linha tambem decidi por renomear a activity ja que sera um fragmento...
                //direito na activity e rafactor->rename->"RENOMEAR"->refactor,se der erro aparecera na notificação para dar "Do Factor"
                //isso renomeara em TODOS os trechos onde o codigo e usado
                transaction.replace(R.id.container, new MapsFragment(),"MapsFragment");
//confirmo se a transação foi bem sucedida;
                transaction.commit();*/
                showFragment(new MapsFragment(),"MapsFragment");

                break;
            case R.id.nav_exemplo_providerv1 :

                /*android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction.replace(R.id.container, new MapsFragment(),"MapsFragment");

                transaction.commit();*/
                showFragment(new ProviderFragmentV1(),"ProviderFragmentV1");

                break;

            case R.id.nav_cliente :

                Intent intent_nav_cliente = new Intent(this, Cliente.class);
                startActivity(intent_nav_cliente);

                break;

            case R.id.nav_usuatio :

                Intent intent_nav_usuatio = new Intent(this, Usuario.class);
                startActivity(intent_nav_usuatio);



                break;

        }


        if (id == R.id.nav_exemplo) {

        }
//sempre que clicar em um item essa função fecha o menu lateral novamente
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }//escolha do menu de seleção

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.act_principal, menu);
        return true;
    }//retorno do menu de opções

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
    } // retorno da seleção

    //ja que o switch fara a mesma coisa foi então criado um metodo para relaizar a tarefa recebendo o fragment e processando(usar v4 pois pega versões antigas)
    private void showFragment(Fragment fragment, String name)
    {

        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container, fragment, name);

        transaction.commit();

    }

}
