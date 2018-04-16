package arduinosensors.example.com.maps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Usuario extends AppCompatActivity {

    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_usuario);

        webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("http://trabalhodolai.scienceontheweb.net/_about_.html");
    }





}
