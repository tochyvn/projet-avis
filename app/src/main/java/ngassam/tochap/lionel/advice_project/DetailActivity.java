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

import ngassam.tochap.lionel.advice_project.Model.AdviceDb;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private AdviceDb db;
    private TextView title;
    private TextView description;
    private TextView note;
    private TextView auteur;
    private TextView categorie;
    private Button return_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView) findViewById(R.id.id_detail_title);
        description = (TextView) findViewById(R.id.id_detail_description);
        note = (TextView) findViewById(R.id.id_detail_note);
        auteur = (TextView) findViewById(R.id.id_detail_auteur);
        categorie = (TextView) findViewById(R.id.id_detail_categorie);

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

}
