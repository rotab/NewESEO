package com.mobile.eseo.neweseoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.view.View;

public class TabActivity_Information extends AppCompatActivity {


    TabHost TabHostWindow2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_information);
    }

        public void pfe(View v) {
            //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
            Intent intent=new Intent(this,TabActivity_PFE.class);
            //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici SecondActivite
            startActivity(intent);

        }

        public void enseignant(View v) {
            //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
            Intent intent=new Intent(this,TabActivity_Enseignants.class);
            //on lance l'intent, cela a pour effet de stoper l'activité courante et lancer une autre activite ici SecondActivite
            startActivity(intent);

        }
}
