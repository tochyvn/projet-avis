package ngassam.tochap.lionel.advice_project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ngassam.tochap.lionel.advice_project.Model.AdviceDb;

import static ngassam.tochap.lionel.advice_project.R.id.map;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private AdviceDb db;
    private TextView title;
    private TextView description;
    private TextView note;
    private TextView auteur;
    private TextView categorie;
    private TextView longitude;
    private TextView latitude;
    private Button return_home;

    private double lat;
    private double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView) findViewById(R.id.id_detail_title);
        description = (TextView) findViewById(R.id.id_detail_description);
        note = (TextView) findViewById(R.id.id_detail_note);
        auteur = (TextView) findViewById(R.id.id_detail_auteur);
        categorie = (TextView) findViewById(R.id.id_detail_categorie);
        longitude = (TextView) findViewById(R.id.id_detail_longitude);
        latitude = (TextView) findViewById(R.id.id_detail_latitude);

        //La carte geolocalisante
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);


        return_home = (Button) findViewById(R.id.id_button_return_home);
        return_home.setOnClickListener(this);

        db = new AdviceDb(this);

        Bundle extras = getIntent().getExtras();
        long id = 0;
        if (extras != null) {
            id = extras.getLong("id");
            Log.d("id", id + "");
        }
        if (id != 0) {
            Cursor cursor = db.fetchById(id);
            if (cursor.moveToNext()) {
                title.setText(cursor.getString(1));
                description.setText(cursor.getString(2));
                note.setText(cursor.getInt(3)+"");
                auteur.setText(cursor.getString(4));
                categorie.setText(cursor.getString(5));
                longitude.setText(cursor.getDouble(6)+"");
                latitude.setText(cursor.getDouble(7)+"");

            }
        }

    }

    @Override
    public void onClick(View v) {

        int id_click = v.getId();
        if (id_click == return_home.getId()) {
            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("POSITION", "MAP is ready.");

        LatLng target = new LatLng(Double.parseDouble(latitude.getText().toString()), Double.parseDouble(longitude.getText().toString()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(target, 10));

        googleMap.addMarker(new MarkerOptions()
                .title( this.getResources().getString( R.string.marker_title ) )
                .snippet( this.getResources().getString( R.string.marker_text ) )
                .position(target));

    }
}
