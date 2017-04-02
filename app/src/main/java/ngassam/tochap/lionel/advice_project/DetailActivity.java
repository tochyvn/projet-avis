package ngassam.tochap.lionel.advice_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ngassam.tochap.lionel.advice_project.Model.AdviceDb;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private AdviceDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        db = new AdviceDb(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            long id = extras.getLong("id");
            Log.d("id", id + "");
        }
    }

    @Override
    public void onClick(View v) {

    }
}
