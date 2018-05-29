package herlon.meeting;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap = googleMap;
        mMap.setBuildingsEnabled(true);

        LatLng[] local = new LatLng[1000];

        // Add a marker in Sydney and move the camera
        local[0] = new LatLng(-19.8157, -43.9542);
        mMap.addMarker(new MarkerOptions().position(local[0]).title("FOR THE POG"));

        for (int i = 1; i<=40;i++)
        {
            float temp_1,temp_2;
            temp_1=rdn_1();
            temp_2=rdn_2();


            temp_1= 19+(temp_1/2500);
            temp_2= 43+(temp_2/2000);

            local[i] = new LatLng(-(temp_1), -(temp_2));
            mMap.addMarker(new MarkerOptions().position(local[i])
                    .title("Local "+ i )
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .snippet("Coord "+temp_1+" "+temp_2)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher)));
        }

// Move the camera instantly to Sydney with a zoom of 15.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(local[0], 15));

// Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

// Construct a CameraPosition focusing on Mountain View and animate the camera to that position.
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(local[0])      // Sets the center of the map to Mountain View
                .zoom(6)                   // Sets the zoom
                .bearing(0)                // Sets the orientation of the camera to east
                .tilt(45)                   // Sets the tilt of the camera to XX degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            //mMap.moveCamera(CameraUpdateFactory.newLatLng(local[0]));



//habilita a procura do Mylocation de acordo com o melhor provider ativo(ou seja com o GPS ativo, seria ele
// sozinho ele gera erro pois precisa da permissão do gps(ou no mainfest ou deve ser usado com try catch)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);


        //registra o clique do metodo logo abaixo (clique na tela para mostrar a coordenada)
        mMap.setOnMapClickListener((GoogleMap.OnMapClickListener) this);
        //habilita na UI o zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    public int rdn_1()
    {
        Random rand = new Random();
        int n = rand.nextInt(9999);
        return n;
    }
    public int rdn_2()
    {
        Random rand = new Random();
        int n = rand.nextInt(9999);
        return n;
    }
}
