package com.mobile.eseo.neweseoapp;

import android.content.Intent;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.mobile.eseo.neweseoapp.bdd.DeadLineBDD;
import com.mobile.eseo.neweseoapp.bdd.PFEBDD;
import com.mobile.eseo.neweseoapp.model.DeadLine;
import com.mobile.eseo.neweseoapp.model.PFE;

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

        initializeBDD();

        //Assign id to Tabhost.
        TabHostWindow = (TabHost) findViewById(android.R.id.tabhost);

        //Creating tab menu.
        TabHost.TabSpec TabNews = TabHostWindow.newTabSpec("News tab");
        TabHost.TabSpec TabSchedule = TabHostWindow.newTabSpec("Schedule Tab");
        TabHost.TabSpec TabDeadline = TabHostWindow.newTabSpec("Deadline Tab");
        TabHost.TabSpec TabInfo = TabHostWindow.newTabSpec("Information Tab");

        //Setting up tab 1 name.
        TabNews.setIndicator(null, getResources().getDrawable(R.drawable.ic_newspaper));
        //Set tab 1 activity to tab 1 menu.
        TabNews.setContent(new Intent(this, TabActivity_News.class));

        //Setting up tab 2 name.
        TabSchedule.setIndicator(null, getResources().getDrawable(R.drawable.ic_schedule));
        //Set tab 3 activity to tab 1 menu.
        TabSchedule.setContent(new Intent(this, TabActivity_Schedule.class));

        //Setting up tab 2 name.
        TabDeadline.setIndicator(null, getResources().getDrawable(R.drawable.ic_deadlines));
        //Set tab 3 activity to tab 3 menu.
        TabDeadline.setContent(new Intent(this, TabActivity_Deadlines.class));

        //Setting up tab 2 name.
        TabInfo.setIndicator(null, getResources().getDrawable(R.drawable.ic_menu));
        //Set tab 3 activity to tab 3 menu.
        TabInfo.setContent(new Intent(this, TabActivity_Information.class));

        //Adding tab1, tab2, tab3 to tabhost view.

        TabHostWindow.addTab(TabNews);
        TabHostWindow.addTab(TabSchedule);
        TabHostWindow.addTab(TabDeadline);
        TabHostWindow.addTab(TabInfo);

    }

    private void initializeBDD() {

        PFE pfe1 = new PFE(" Industrialisation logicielle", 1, "Le projet doit répondre à un besoin d'automatisation des processus permettant de produire, tester, qualifier et livrer un produit de type logiciel. Le projet part d'une base existante connue, les PIC, pour y ajouter une automatisation plus poussée et aboutir à une PLIC. ");
        PFE pfe2 = new PFE("Panneau Solaire Orientable", 1, " Le thème des énergies renouvelables a été retenu en I1 (S6) pour dispenser sous forme de projets, les enseignements du génie électrique et de l'automatique . La partie \"automatique\" est illustrée par un dispositif à base de panneau solaire contrôlable en azimut voire en élévation pour un \"suivi du soleil\". L'équipe d'enseignants souhaite disposer d'un démonstrateur.");
        PFE pfe3 = new PFE("Plateforme de fiction interactive", 1, "Etude et développement d’une plateforme au sein de l’environnement Android permettant aux utilisateurs de développer et de participer à une fiction interactive. La plateforme devra être fonctionnelle tout en laissant un maximum de liberté scénaristique aux utilisateurs.");
        PFEBDD pfeBDD = new PFEBDD(this);

        pfeBDD.open();
        pfeBDD.deleteAll();

        pfeBDD.insertPFE(pfe1);
        pfeBDD.insertPFE(pfe2);
        pfeBDD.insertPFE(pfe3);

        pfeBDD.close();

        DeadLine deadline1 = new DeadLine(0, "Projet COOL_TL_QL à rendre", "20/01/2017 12:00:00");
        DeadLine deadline2 = new DeadLine(1, "Projet application mobile à rendre", "15/11/2016 00:00:00");
        DeadLine deadline3 = new DeadLine(2, "Forum AnjouP: déposer CV", "10/10/2016 18:00:00");
        DeadLine deadline4 = new DeadLine(3, "CV & LM à rendre", "20/09/2016 16:00:00");
        DeadLine deadline5 = new DeadLine(4, "TP Base de données avancée", "11/12/2017 00:00:00");
        DeadLine deadline6 = new DeadLine(5, "PFE", "03/02/2017 23:00:00");
        DeadLine deadline7 = new DeadLine(6, "100 jours I3", "26/10/2016 18:00:00");

        DeadLineBDD deadlinebdd = new DeadLineBDD(this);

        deadlinebdd.open();

        deadlinebdd.deleteAll();

        deadlinebdd.insertDeadLine(deadline1);
        deadlinebdd.insertDeadLine(deadline2);
        deadlinebdd.insertDeadLine(deadline3);
        deadlinebdd.insertDeadLine(deadline4);
        deadlinebdd.insertDeadLine(deadline5);
        deadlinebdd.insertDeadLine(deadline6);
        deadlinebdd.insertDeadLine(deadline7);

        deadlinebdd.close();
    }
}