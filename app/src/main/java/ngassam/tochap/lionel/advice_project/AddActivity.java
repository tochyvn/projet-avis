package ngassam.tochap.lionel.advice_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ngassam.tochap.lionel.advice_project.Metier.Advice;
import ngassam.tochap.lionel.advice_project.Model.AdviceDb;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button_return_home;
    private Button button_add;
    private AdviceDb db;
    private EditText edit_title;
    private EditText edit_description;
    private EditText edit_note;
    private EditText edit_auteur;
    private EditText edit_categorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

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

}
