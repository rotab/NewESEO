package com.mobile.eseo.neweseoapp;

import android.content.Intent;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import com.mobile.eseo.neweseoapp.bdd.DeadLineBDD;
import com.mobile.eseo.neweseoapp.model.DeadLine;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {


/*
        *//** Called when the activity is first created. *//*
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);



            //CrÃ©ation d'une instance de ma classe DeadLineBDD
            DeadLineBDD deadlinebdd = new DeadLineBDD(this);

            //CrÃ©ation d'une deadline
            DeadLine deadline = new DeadLine("truc Ã  faire","aaa");

            //On ouvre la base de donnÃ©es pour Ã©crire dedans
            deadlinebdd.open();


            //On insÃ¨re la deadline que l'on vient de crÃ©er
            deadlinebdd.insertDeadLine(deadline);



            //Pour vÃ©rifier que l'on a bien crÃ©Ã© notre deadline dans la BDD
            //on extrait le livre de la BDD


            DeadLine deadlineFromBdd = deadlinebdd.getDeadLineWithLimite(deadline.getLimite());
            //Si une deadline est retournÃ© (donc si le livre Ã  bien Ã©tÃ© ajoutÃ© Ã  la BDD)




            if(deadlineFromBdd != null) {
                //On affiche les infos de la deadline dans un Toast
                Toast.makeText(this, deadlineFromBdd.toString(), Toast.LENGTH_LONG).show();
                //On modifie la limite du livre
                deadlineFromBdd.setLimite("08/08/2017 a 20h (repousse d'un jour)");
                //Puis on met Ã  jour la BDD
                deadlinebdd.updateDeadLine(deadlineFromBdd.getId(), deadlineFromBdd);
            }

            //On extrait le livre de la BDD grÃ¢ce Ã  la nouvelle deadline
            deadlineFromBdd = deadlinebdd.getDeadLineWithLimite("08/08/2017 Ã  20h (repoussÃ© d'un jour)");
            //S'il existe un livre possÃ©dant cet id dans la BDD
            if(deadlineFromBdd != null){
                //On affiche les nouvelles informations du livre pour vÃ©rifier que la deadline du livre a bien Ã©tÃ© mis Ã  jour
                Toast.makeText(this, deadlineFromBdd.toString(), Toast.LENGTH_LONG).show();
                //on supprime la deadline de la BDD grÃ¢ce Ã  son ID
                deadlinebdd.removeDeadLineWithID(deadlineFromBdd.getId());
            }

            //On essaye d'extraire de nouveau le livre de la BDD toujours grÃ¢ce Ã  sa nouvelle deadline
            deadlineFromBdd = deadlinebdd.getDeadLineWithLimite("08/08/2017 Ã  20h (repoussÃ© d'un jour)");
            //Si aucun livre n'est retournÃ©
            if(deadlineFromBdd == null){
                //On affiche un message indiquant que le livre n'existe pas dans la BDD
                Toast.makeText(this, "Ce livre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
            }
            //Si le livre existe (mais normalement il ne devrait pas)
            else{
                //on affiche un message indiquant que le livre existe dans la BDD
                Toast.makeText(this, "Ce livre existe dans la BDD", Toast.LENGTH_LONG).show();
            }
            deadlinebdd.close();
        }*/




    TabHost TabHostWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign id to Tabhost.
        TabHostWindow = (TabHost) findViewById(android.R.id.tabhost);

        //Creating tab menu.
        TabHost.TabSpec TabMenu1 = TabHostWindow.newTabSpec("First tab");
        TabHost.TabSpec TabMenu2 = TabHostWindow.newTabSpec("Second Tab");
        TabHost.TabSpec TabMenu3 = TabHostWindow.newTabSpec("Third Tab");
        TabHost.TabSpec TabMenu4 = TabHostWindow.newTabSpec("Fourth Tab");

        //Setting up tab 1 name.
        TabMenu1.setIndicator(null, getResources().getDrawable(R.drawable.ic_newspaper));
        //Set tab 1 activity to tab 1 menu.
        TabMenu1.setContent(new Intent(this, TabActivity_News.class));

        //Setting up tab 2 name.
        TabMenu2.setIndicator(null, getResources().getDrawable(R.drawable.ic_schedule));
        //Set tab 3 activity to tab 1 menu.
        TabMenu2.setContent(new Intent(this, TabActivity_Schedule.class));

        //Setting up tab 2 name.
        TabMenu3.setIndicator(null, getResources().getDrawable(R.drawable.ic_deadlines));
        //Set tab 3 activity to tab 3 menu.
        TabMenu3.setContent(new Intent(this, TabActivity_Deadlines.class));

        //Setting up tab 2 name.
        TabMenu4.setIndicator(null, getResources().getDrawable(R.drawable.ic_menu));
        //Set tab 3 activity to tab 3 menu.
        TabMenu4.setContent(new Intent(this, TabActivity_Information.class));

        //Adding tab1, tab2, tab3 to tabhost view.

        TabHostWindow.addTab(TabMenu1);
        TabHostWindow.addTab(TabMenu2);
        TabHostWindow.addTab(TabMenu3);
        TabHostWindow.addTab(TabMenu4);

    }
}