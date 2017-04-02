package ngassam.tochap.lionel.advice_project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import ngassam.tochap.lionel.advice_project.Model.AdviceDb;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_return_home;
    private ListView list_advices;
    private AdviceDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new AdviceDb(this);
        Cursor results = db.fetchAll();


        list_advices = (ListView) findViewById(R.id.list_advices_id);
        SimpleCursorAdapter adapter = this.makeAdapter(results);
        list_advices.setAdapter(adapter);

        button_return_home = (Button) findViewById(R.id.id_button_return_home);
        button_return_home.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        int id_click = v.getId();
        Intent intent = null;
        if (id_click == button_return_home.getId()) {
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public SimpleCursorAdapter makeAdapter(Cursor cursor) {

        String[] columns = new String[] {
                "title"
        };

        int[] views = new int[] {
                R.id.title_advice_id
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, R.layout.listadvice, cursor,  columns, views, 0);

        return adapter;
    }

}
