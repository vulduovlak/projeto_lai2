package arduinosensors.example.com.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
// a fragmentactivity vira SupportMapFragment para poder ser usada no content_act
//isso para ter uma versatilidade maior e nãoprecisar criar um activity pra tudo que for usar mapa
public class MapsFragment extends SupportMapFragment implements OnMapReadyCallback,
                                                                GoogleMap.OnMapClickListener{

    private GoogleMap mMap;

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
//quando o mapa estiver pronto sera executado tudo abaxo dessa linha
        // abrevia o googlemap
        mMap = googleMap;
        //registra o clique do metodo logo abaixo (clique na tela para mostrar a coordenada)
        mMap.setOnMapClickListener(this);
        //habilita na UI o zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);


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
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));





    }

    @Override
    public void onMapClick(LatLng latLng) {
        //sera usado para exibir a coordenada atual
        //como é do tipo context(porem não e um activity e sim um fragment, não posso usar o this) devera ser usado
        //ou getContext() ou getActivity()
        Toast.makeText(getContext(), "Coordenadas " + latLng.toString() , Toast.LENGTH_SHORT).show();
    }
}
