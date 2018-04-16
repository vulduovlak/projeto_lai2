package arduinosensors.example.com.maps;

import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.drawable.BitmapDrawable;
        import android.location.Criteria;
        import android.location.LocationManager;
        import android.os.Bundle;
        import android.util.Log;
        import android.widget.Toast;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
        import com.google.android.gms.maps.model.CameraPosition;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;

// a fragmentactivity vira SupportMapFragment para poder ser usada no content_act
//isso para ter uma versatilidade maior e nãoprecisar criar um activity pra tudo que for usar mapa
public class ProviderFragmentV1 extends SupportMapFragment implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener{

    private GoogleMap mMap;

    int marker_count = 1;
    double[] latitude = new double[100];
    double[] longitude = new double[100];
    String[] LATLONG = new String[100];
    String[] titulo = new String[100];


    private static final LatLng SYDNEY = new LatLng(-33.88,151.21);










    //essa é o objeto que e responsavel por trabalhar com nosso provider para localização
    private LocationManager locationManager;
    //tag com o nome da classe para debug futuro (obs não pode passar de 23 char)
    private static final String TAG = "ProviderFragmentV1";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {



        try{//try do mMap.setMyLocationEnabled(true);

//recuperação do metodo de localização, o retorno do getActivity(a localização da do pelo (LocationManager))
// que e a recuperação do serviço do android(location service)
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//configuração da biblioteca para atualizar ctrl+alt+shift+s,na aba APP
// na guia de dependencias (com.google.android.gmsplay-services X.X.X)
// devera ser removido e adicionado a versão mais recente(add com.google.android.gms:play-services-maps:11.6.0 e att com.google.android.gms:play-services:11.6.0)
// pesquisado na + library dependency, dai e so aguardar a atualização


// permite localizar informações referentes ao provider deixando mais especificas as buscas
            Criteria criteria = new Criteria();
//deixa como automatico a pesquisa do provider(eu poderia definir mas não compensa, pois o algoritmo ja e eficiente o bastante),e somente os ativos
            String provider = locationManager.getBestProvider(criteria, true);
//exibe o nome do provider escolhido pelo sistema e ativo
            Toast.makeText(getActivity(),"Provider " + provider ,Toast.LENGTH_LONG);

//quando o mapa estiver pronto sera executado tudo abaxo dessa linha
            // abrevia o googlemap
            mMap = googleMap;
            //registra o clique do metodo logo abaixo (clique na tela para mostrar a coordenada)
            mMap.setOnMapClickListener(this);
            //habilita na UI o zoom
            mMap.getUiSettings().setZoomControlsEnabled(true);
//habilita a procura do Mylocation de acordo com o melhor provider ativo(ou seja com o GPS ativo, seria ele
// sozinho ele gera erro pois precisa da permissão do gps(ou no mainfest ou deve ser usado com try catch)
            mMap.setMyLocationEnabled(true);















//posicionamento camera e ajustes de posição no clique do mapa
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                @Override
                public void onMapClick(LatLng point) {

                    //habilita construções
                    mMap.setBuildingsEnabled(true);

                    MarkerOptions marker = new MarkerOptions().position(
                            new LatLng(latitude[marker_count],longitude[marker_count])).title("Novo marcador "+ marker_count).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));





                    latitude[marker_count]=point.latitude;
                    longitude[marker_count]=point.longitude;
                    LatLng Temp = new LatLng(latitude[marker_count],longitude[marker_count]);
                    LATLONG[marker_count] =  String.valueOf(Temp);
                    mMap.addMarker(marker);

                    /*Temp = mMap.addMarker(new MarkerOptions()
                            .position(Temp)
                            .title("Sydney")
                            .snippet("Population: 4,627,300")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));










                    Bitmap.Config conf = Bitmap.Config.ARGB_8888;
                    Bitmap bmp = Bitmap.createBitmap(80, 80, conf);
                    Canvas canvas1 = new Canvas(bmp);

// paint defines the text color, stroke width and size
                    Paint color = new Paint();
                    color.setTextSize(35);
                    color.setColor(Color.BLACK);

// modify canvas
                    canvas1.drawBitmap(BitmapFactory.decodeResource(getResources(),
                            R.mipmap.system_control_panel_15843), 0,0, color);
                    canvas1.drawText("User Name!", 30, 40, color);

// add marker to Map
                    mMap.addMarker(new MarkerOptions()
                            .position(Temp)
                            .icon(BitmapDescriptorFactory.fromBitmap(bmp))
                            // Specifies the anchor to be at a particular point in the marker image.
                            .anchor(0.5f, 1));

*/







                    // Move the camera instantly to Temp with a zoom of 15.
                    //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Temp,15));

// Zoom in, animating the camera.
                    //mMap.animateCamera(CameraUpdateFactory.zoomIn());

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
                    //mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

// Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(Temp)      // Sets the center of the map to Mountain View
                            .zoom(17)                   // Sets the zoom
                            .bearing(90)                // Sets the orientation of the camera to east
                            .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                            .build();                   // Creates a CameraPosition from the builder
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));








                }

            });

        }catch(SecurityException ex)
        {
            //sera exibido um log com a tag(geralmente o nome da classe para facilitar no logcat) do erro com mensagem de erro e retorna ex
            //nesse caso o TAG sera uma constante que sera o nome da classe
            Log.e(TAG,"Error",ex);
        }


/*
        //coordenadas para Sydney
        LatLng sydney = new LatLng(-34, 151);
        //inicializa um marcador
        MarkerOptions marker = new MarkerOptions();
        //define a posição em Sydney
        marker.position(sydney);
        //define o titulo
        marker.title("Marker in Sydney");


        //adiciona o marcador
        mMap.addMarker(marker);
        //move a camera para a posição
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
*/

    }

    @Override
    public void onMapClick(LatLng latLng) {
        //sera usado para exibir a coordenada atual
        //como é do tipo context(porem não e um activity e sim um fragment, não posso usar o this) devera ser usado
        //ou getContext() ou getActivity()
        Toast.makeText(getContext(), "Coordenadas " + latLng.toString() , Toast.LENGTH_SHORT).show();
    }
}
//esse fragmento e chamado no fragmento principal do mapa apos configurado no ONSELECT
//ao emular foi percebido que gerou erro(logcat) na chamada desse fragmento, isso foi resolvido
// mudando a versão do target sdk(ctrl+alt+shift+s->flavor target sdk version para API 17: Android 4.2 (Jelly Bean)), isso resolve o erro