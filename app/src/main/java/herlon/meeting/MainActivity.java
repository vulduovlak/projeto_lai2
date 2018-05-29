package herlon.meeting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    WebView mWebView;
    //WebView mWebView_1;
    //WebView mWebView_2;
    //WebView mWebView_3;
    //WebView mWebView_4;
    Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        inicializarComponentes();


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chamada("Maps");
            }
        });

    }


    private void inicializarComponentes() {

        mTextMessage = (TextView) findViewById(R.id.message);
        mWebView = (WebView) findViewById(R.id.webView);
        //mWebView_1 = (WebView) findViewById(R.id.webView_1);
        //mWebView_2 = (WebView) findViewById(R.id.webView_2);
        //mWebView_3 = (WebView) findViewById(R.id.webView_3);
        //mWebView_4 = (WebView) findViewById(R.id.webView_4);
        mButton = (Button) findViewById(R.id.btnConfirma);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mWebView.loadUrl("http://trabalhodolai.scienceontheweb.net/index.html");
        mButton.setVisibility(View.GONE);

    }//inicialização dos componentes

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText("Home");
                    chamada("Home");
                    mWebView.loadUrl("http://trabalhodolai.scienceontheweb.net/index.html");
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText("Usuario");
                    chamada("Usuario");
                    mWebView.loadUrl("http://trabalhodolai.scienceontheweb.net/procura.html");
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText("Cliente");
                    chamada("Cliente");
                    mWebView.loadUrl("http://trabalhodolai.scienceontheweb.net/cadastro.html");
                    return true;
            }
            return false;
        }
    };

    public void chamada(String item) {

        String id = item;

        switch (id) {

            case "Home":
                mWebView.setVisibility(View.VISIBLE);
                mButton.setVisibility(View.GONE);
                break;

            case "Usuario":
                mWebView.setVisibility(View.VISIBLE);
                mButton.setVisibility(View.VISIBLE);
                break;

            case "Cliente":
                mWebView.setVisibility(View.VISIBLE);
                mButton.setVisibility(View.GONE);
                break;

            case "Maps":
                //mWebView.setVisibility(View.GONE);
                //mWebView.setVisibility(View.GONE);
                Intent intent_nav_cliente = new Intent(this, MapsActivity.class);
                startActivity(intent_nav_cliente);
                break;
        }
    }
}
