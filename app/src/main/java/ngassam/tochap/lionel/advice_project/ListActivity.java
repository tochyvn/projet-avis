package ngassam.tochap.lionel.advice_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_return_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

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
}
