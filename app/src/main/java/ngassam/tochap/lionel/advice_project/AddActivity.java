package ngassam.tochap.lionel.advice_project;

import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ngassam.tochap.lionel.advice_project.Metier.Advice;
import ngassam.tochap.lionel.advice_project.Model.AdviceDb;

public class AddActivity extends AppCompatActivity implements OnClickListener, LocationListener {

    private Button button_return_home;
    private Button button_add;
    private AdviceDb db;
    private EditText edit_title;
    private EditText edit_description;
    private EditText edit_note;
    private EditText edit_auteur;
    private EditText edit_categorie;

    private double longitude;
    private double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.handleAccessPermissions(true);

        //Creation de la base de données si elle n'existe pas encore
        db = new AdviceDb(this);
        Log.d("DB", db.getReadableDatabase().getPath());

        button_return_home = (Button) findViewById(R.id.id_button_return_home);
        button_return_home.setOnClickListener(this);

        button_add = (Button) findViewById(R.id.id_button_add);
        button_add.setOnClickListener(this);

        edit_title = (EditText) findViewById(R.id.id_input_title);
        edit_description = (EditText) findViewById(R.id.id_input_description);
        edit_note = (EditText) findViewById(R.id.id_input_note);
        edit_auteur = (EditText) findViewById(R.id.id_input_auteur);
        edit_categorie = (EditText) findViewById(R.id.id_input_categorie);


    }

    @Override
    public void onClick(View v) {

        int id_click = v.getId();
        Intent intent = null;
        if (id_click == button_return_home.getId()) {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        } else if(id_click == button_add.getId()) {

            //Controle des erreurs dans le formulaire
            boolean erreur = false;
            if (edit_title.getText().toString().equals("")) {
                erreur = true;
                edit_title.setError("Ce champ ne peut être vide!");
            }
            if (edit_description.getText().toString().equals("")) {
                erreur = true;
                edit_description.setError("Ce champ ne peut être vide!");
            }

            try {
                Integer.parseInt(edit_note.getText().toString());
            } catch (NumberFormatException e) {
                Log.d("Format incorrecte", e.getMessage());
                erreur =true;
                edit_note.setError("Ce champ doit être numérique!");
            }

            //Insertion en base de données
            if (!erreur) {
                Advice advice = new Advice(
                        edit_title.getText().toString(),
                        edit_description.getText().toString(),
                        Integer.parseInt(edit_note.getText().toString()),
                        edit_auteur.getText().toString(),
                        edit_categorie.getText().toString()
                );
                advice.setLongitude(longitude);
                advice.setLatitude(latitude);

                Log.d("DB-ADD", advice+"");
                db.addAdvice(advice);
                this.reset();
                CharSequence text = "Votre avis a été inséré avec succès!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(this, text, duration);
                toast.show();
            }
        } else {
            this.reset();
        }

    }

    private void reset() {
        edit_title.setText("");
        edit_description.setText("");
        edit_note.setText("");
        edit_auteur.setText("");
        edit_categorie.setText("");
    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = location.getLongitude();
        latitude = location.getLatitude();
        Log.d("coordonnées", "Long " + longitude + " Lat " + latitude);
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses =  geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    1);
            Address address = addresses.get(0);
            Log.d("adress", address.getAddressLine(0) + "  ville : " +address.getAddressLine(1));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        this.handleAccessPermissions(false);
    }

    public void handleAccessPermissions(boolean requestPermissionsIfNeeded) {
        Log.d("POSITION", "Will check permissions...");

        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        int fineLocationPermission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        int coarseLocationPermission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);

        ArrayList<String> permissionsToRequest = new ArrayList<String>();

        //FINE LOCATION
        if (fineLocationPermission == PackageManager.PERMISSION_GRANTED) {
            Log.d("POSITION", "FINE location ok.");

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, this);
        } else {
            Log.d("POSITION", "FINE location nok.");

            permissionsToRequest.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        }

        //COARSE LOCATION
        if (coarseLocationPermission == PackageManager.PERMISSION_GRANTED) {
            Log.d("POSITION", "COARSE location ok.");

            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        } else {
            Log.d("POSITION", "COARSE location nok.");

            permissionsToRequest.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
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

}
