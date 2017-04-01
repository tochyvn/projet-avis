package ngassam.tochap.lionel.advice_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_add;
    private Button button_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
