package ngassam.tochap.lionel.advice_project;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, LocationListener, OnMapReadyCallback {

    private Button button_add;
    private Button button_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.handleAccessPermissions(true);

        //Ajout des listeners à mes 2 buttons
        button_add = (Button) findViewById(R.id.id_button_ajout);
        button_list = (Button) findViewById(R.id.id_button_liste);
        button_add.setOnClickListener(this);
        button_list.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id_click = v.getId();
        Intent intent = null;
        if (id_click == button_add.getId()) {
            intent = new Intent(getApplicationContext(), AddActivity.class);
            startActivity(intent);
        } else if (id_click == button_list.getId()) {
            intent = new Intent(getApplicationContext(), ListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d("POSITION", "Received permissions results...");
        //this.handleAccessPermissions(false);
    }

    public void handleAccessPermissions(boolean requestPermissionsIfNeeded) {
        Log.d("POSITION", "Will check permissions...");

        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        int fineLocationPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int coarseLocationPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);

        ArrayList<String> permissionsToRequest = new ArrayList<String>();

        //FINE LOCATION
        if (fineLocationPermission == PackageManager.PERMISSION_GRANTED) {
            Log.d("POSITION", "FINE location ok.");

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
        } else {
            Log.d("POSITION", "FINE location nok.");

            permissionsToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        //COARSE LOCATION
        if (coarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
            Log.d("POSITION", "COARSE location ok.");

            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        } else {
            Log.d("POSITION", "COARSE location nok.");

            permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (permissionsToRequest.size() == 0) {
            return;
        }

        if (! requestPermissionsIfNeeded) {
            return;
        }

        Log.d("POSITION", "Need to ask some permissions.");
        String[] permissionsToRequestArray = permissionsToRequest.toArray(new String[0]);
        ActivityCompat.requestPermissions(this, permissionsToRequestArray, 0);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

}
