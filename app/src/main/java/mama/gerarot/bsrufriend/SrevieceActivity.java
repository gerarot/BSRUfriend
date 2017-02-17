package mama.gerarot.bsrufriend;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SrevieceActivity extends FragmentActivity implements OnMapReadyCallback {

    //Explicit
    private GoogleMap mMap;
    private double userLatADouble = 13.733046, userLngADouble = 100.489407;
    private TextView textView;
    private Button button;
    private String[] loginStrings;
    private LocationManager locationManager;
    private Criteria criteria;
    private boolean aBoolean = true; //for Stop Loop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layuot);

        //Bind Widget
        textView = (TextView) findViewById(R.id.textView2);
        button = (Button) findViewById(R.id.button4);

        //Receive Value for MainActivity
        loginStrings = getIntent().getStringArrayExtra("login");

        //Show Text
        textView.setText(loginStrings[1]);

        //Setup location
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //My Loop
        myLoop();


    }      //Main Method

    private void myLoop() {

        //Doing
        afterResume();
        updatelatlng();

        //Delay
        if (aBoolean) {

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                   myLoop();
                }
            }, 1000);

        }   //if aboolean


    }   //myLoop

    private void updatelatlng() {

        try {

            EditLatLng editLatLng = new EditLatLng(SrevieceActivity.this, loginStrings[0]);
            editLatLng.execute(Double.toString(userLatADouble),
                    Double.toString(userLngADouble));
            boolean b = Boolean.parseBoolean(editLatLng.get());
            Log.d("17febV2", "Result ==> " + b);

        } catch (Exception e) {
            Log.d("17febV2", "e update ==> " + e.toString());
        }

    }   //updatelatlng

    @Override
    protected void onResume() {
        super.onResume();

        afterResume();

    }

    private void afterResume() {

        Location networdLocation = myFindLocation(LocationManager.NETWORK_PROVIDER);
        if (networdLocation != null) {

            userLatADouble = networdLocation.getLatitude();
            userLngADouble = networdLocation.getLongitude();

        }   //if networdLocation

        Location gpsLocation = myFindLocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {

            userLatADouble = gpsLocation.getLatitude();
            userLngADouble = gpsLocation.getLongitude();
        }   //if gpsLocation

        Log.d("17febV1", "Lat ==> " + userLatADouble);
        Log.d("17febV1", "Lng ==>" + userLngADouble);

    }   //afterResume

    @Override
    protected void onStop() {
        super.onStop();

        aBoolean = false;

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
        locationManager.removeUpdates(locationListener);
    }

    public Location myFindLocation(String strProvider) {

        Location location = null;

        if (locationManager.isProviderEnabled(strProvider)) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            locationManager.requestLocationUpdates(strProvider, 1000, 10, locationListener);
            location = locationManager.getLastKnownLocation(strProvider);


        }   //if

        return location;
    }


    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            userLatADouble = location.getLatitude();
            userLngADouble = location.getLongitude();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Setup center of Map
        mMap.animateCamera(CameraUpdateFactory
                .newLatLngZoom(new LatLng(userLatADouble, userLngADouble), 16));

    }   // onMapReady
}   //Main class
