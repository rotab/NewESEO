package com.mobile.eseo.neweseoapp;

import android.content.Intent;
import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.mobile.eseo.neweseoapp.bdd.DeadLineBDD;
import com.mobile.eseo.neweseoapp.bdd.EnseignantBDD;
import com.mobile.eseo.neweseoapp.bdd.PFEBDD;
import com.mobile.eseo.neweseoapp.model.DeadLine;
import com.mobile.eseo.neweseoapp.model.Enseignant;
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

        //public Enseignant(String nom, String prenom, String fonction, String email, long numero) {
        Enseignant enseignant1 = new Enseignant("Clavreuil","Mickael","Responsable qqchose","Mickael.clavreuil@eseo.fr", 665212365);
        Enseignant enseignant2 = new Enseignant("Woodward","Richard","Module applications mobiles","Richard.Woodward@eseo.fr", 616504174);
        Enseignant enseignant3 = new Enseignant("Rousseau","Sophie","Responsable LD","Sophie.Rousseau@eseo.fr", 655051420);
        Enseignant enseignant4 = new Enseignant("Brun","Matthias","Responsable qqchose","Matthias.Brun@eseo.fr", 654051420);
        Enseignant enseignant5 = new Enseignant("Jose","Henry","Responsable sieste","Henry.Jose@eseo.fr", 650452440);

        EnseignantBDD enseignantBDD = new EnseignantBDD(this);

        enseignantBDD.open();
        enseignantBDD.deleteAll();
        enseignantBDD.insertEnseignant(enseignant1);
        enseignantBDD.insertEnseignant(enseignant2);
        enseignantBDD.insertEnseignant(enseignant3);
        enseignantBDD.insertEnseignant(enseignant4);
        enseignantBDD.insertEnseignant(enseignant5);


        PFE pfe1 = new PFE(" Industrialisation logicielle", 1, "Le projet doit répondre à un besoin d'automatisation des processus permettant de produire, tester, qualifier et livrer un produit de type logiciel. Le projet part d'une base existante connue, les PIC, pour y ajouter une automatisation plus poussée et aboutir à une PLIC. ");
        PFE pfe2 = new PFE("Panneau Solaire Orientable", 1, " Le thème des énergies renouvelables a été retenu en I1 (S6) pour dispenser sous forme de projets, les enseignements du génie électrique et de l'automatique . La partie \"automatique\" est illustrée par un dispositif à base de panneau solaire contrôlable en azimut voire en élévation pour un \"suivi du soleil\". L'équipe d'enseignants souhaite disposer d'un démonstrateur.");
        PFE pfe3 = new PFE("Plateforme de fiction interactive", 1, "Etude et développement d’une plateforme au sein de l’environnement Android permettant aux utilisateurs de développer et de participer à une fiction interactive. La plateforme devra être fonctionnelle tout en laissant un maximum de liberté scénaristique aux utilisateurs.");
        PFE pfe4 = new PFE("Urban Board", 1, "Ce projet se déroulera en partenariat avec la société Véolia et concerne une application dans le champs de la ville intelligente. L'idée du projet est de proposer un affichage en temps réels des informations clés qui caractérisent le fonctionnement des infrastructures urbaines. Ce projet a été déposé par le partenaire industriel dans le cadre de PAVIC la plateforme angevine pour la ville intelligente. ");
        PFE pfe5 = new PFE("Plug-in(s) pédagogique pour Eclipse", 1, "Depuis 8 ans, l'ESEO a utilisé l'Assignment Centre pour aider les élèves en I1 et les enseignants pendant les projets de programmation orientée objet. LAssignment Centre est constitué de trois modules:\n" +
                "\n" +
                "    pour les enseignants: la création et l'exportation des fichiers necessaire pour l'Assignment Centre Student Module (acsm) (avec les tests unitaires et les tests de qualités)\n" +
                "    pour les élèves(acsm): l'aide interactive au cours du projet (la conformité, la progression et la qualité)\n" +
                "    pour les enseignants:l'analyse des codes (la conformité, la progression, la qualité et le plagiat) \n" +
                "\n" +
                "De ces trois modules, seul le module élève est entièrement fonctionnel, les deux autres modules nécessitent un certain nombre d'étapes manuelles. En plus, depuis l'année dernière, les modailités du cours Java à l'ESEO a changé, parmi ces modification, les élèves ont le droit d'utiliser Eclipse au cours du second semestre. ");
        PFE pfe6 = new PFE("L'arroseur connecté.", 1, "équiper un arroseur automatique d'une connexion permettant le contrôle à distance et la surveillance d'anomalies afin de permettre au maraîcher de dormir la nuit.");
        PFE pfe7 = new PFE("La sécurité par la pratique", 1, "Le numérique est au coeur de notre vie, nous la facilitant de plus en plus. Par contre, elle n'a jamais été aussi exposée qu'en ce moment. Toute cette technologie est potentiellement victime d'attaques virtuelles. Elles sont diverses et variées, menaçant aussi bien le matériel que le logiciel. De plus, un grand nombre de personnes négligent ces dangers par manque d'informations.");
        PFE pfe8 = new PFE(" \tConception d’un intranet sur AWS", 1, "Quelles solutions techniques choisir pour la conception d’un intranet élastique, scalable et viable économiquement pour une jeune entreprise / start-up?");

        PFEBDD pfeBDD = new PFEBDD(this);

        pfeBDD.open();
        pfeBDD.deleteAll();

        pfeBDD.insertPFE(pfe1);
        pfeBDD.insertPFE(pfe2);
        pfeBDD.insertPFE(pfe3);
        pfeBDD.insertPFE(pfe4);
        pfeBDD.insertPFE(pfe5);
        pfeBDD.insertPFE(pfe6);
        pfeBDD.insertPFE(pfe7);
        pfeBDD.insertPFE(pfe8);

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