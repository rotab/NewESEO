package com.mobile.eseo.neweseoapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.app.TabActivity;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.TabHost;

import com.mobile.eseo.neweseoapp.bdd.DeadLineBDD;
import com.mobile.eseo.neweseoapp.bdd.EnseignantBDD;
import com.mobile.eseo.neweseoapp.bdd.PFEBDD;
import com.mobile.eseo.neweseoapp.model.DeadLine;
import com.mobile.eseo.neweseoapp.model.Enseignant;
import com.mobile.eseo.neweseoapp.model.PFE;
import com.mobile.eseo.neweseoapp.service.DeadlineService;

import java.util.Calendar;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    TabHost TabHostWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeBDD();

        TabHostWindow = (TabHost) findViewById(android.R.id.tabhost);

        TabHost.TabSpec TabNews = TabHostWindow.newTabSpec("News tab");
        TabHost.TabSpec TabSchedule = TabHostWindow.newTabSpec("Schedule Tab");
        TabHost.TabSpec TabDeadline = TabHostWindow.newTabSpec("Deadline Tab");
        TabHost.TabSpec TabInfo = TabHostWindow.newTabSpec("Information Tab");


        TabNews.setIndicator(null, getResources().getDrawable(R.drawable.ic_newspaper));
        TabNews.setContent(new Intent(this, TabActivity_News.class));

        TabSchedule.setIndicator(null, getResources().getDrawable(R.drawable.ic_schedule));
        TabSchedule.setContent(new Intent(this, TabActivity_Schedule.class));

        TabDeadline.setIndicator(null, getResources().getDrawable(R.drawable.ic_deadlines));
        TabDeadline.setContent(new Intent(this, TabActivity_Deadlines.class));

        TabInfo.setIndicator(null, getResources().getDrawable(R.drawable.ic_menu));
        TabInfo.setContent(new Intent(this, TabActivity_Information.class));


        TabHostWindow.addTab(TabNews);
        TabHostWindow.addTab(TabSchedule);
        TabHostWindow.addTab(TabDeadline);
        TabHostWindow.addTab(TabInfo);

    }

    /*This method initialize the data base with statics data.
    * When the application can be connected to the database containing the necessary information, it will take its data in this one.
    * */
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

        DeadLine deadline1 = new DeadLine(0, "Projet COOL_TL_QL à rendre", "20/10/2016 12:00:00");
        DeadLine deadline2 = new DeadLine(1, "Projet application mobile à rendre", "01/11/2016 00:00:00");
        DeadLine deadline3 = new DeadLine(2, "Forum AnjouP: déposer CV", "16/11/2016 18:00:00");
        DeadLine deadline4 = new DeadLine(3, "CV & LM à rendre", "16/11/2016 16:00:00");
        DeadLine deadline5 = new DeadLine(4, "TP Base de données avancée", "17/11/2016 18:00:00");
        DeadLine deadline6 = new DeadLine(5, "PFE", "17/11/2016 23:00:00");
        DeadLine deadline7 = new DeadLine(6, "100 jours I3", "18/11/2016 18:00:00");

        DeadLine deadline8 = new DeadLine(7, "Projet Composant Logiciel", "18/11/2016 12:00:00");
        DeadLine deadline9 = new DeadLine(8, "Inscription Association", "19/11/2016 00:00:00");
        DeadLine deadline10 = new DeadLine(9, "Forum étudiant", "19/11/2016 18:00:00");
        DeadLine deadline11 = new DeadLine(10, "Rapport de stage", "20/11/2016 16:00:00");
        DeadLine deadline12 = new DeadLine(11, "Audit COOL_TL_QL", "20/11/2016 18:00:00");
        DeadLine deadline13 = new DeadLine(12, "Monographie sectorielle", "21/11/2016 23:00:00");
        DeadLine deadline14 = new DeadLine(13, "Inscription soirée étudiant", "21/11/2016 18:00:00");

        DeadLine deadline15 = new DeadLine(14, "Etude de cas BI", "22/11/2016 12:00:00");
        DeadLine deadline16 = new DeadLine(15, "Projet Systeme critique", "23/11/2016 10:00:00");
        DeadLine deadline17 = new DeadLine(16, "Forum étudiant", "24/11/2016 18:00:00");
        DeadLine deadline18 = new DeadLine(17, "Convention de stage à signer", "25/11/2016 16:00:00");
        DeadLine deadline19 = new DeadLine(18, "Réunion PFE", "26/11/2016 18:00:00");
        DeadLine deadline20 = new DeadLine(19, "Colloque des objets connectés", "27/11/2016 23:00:00");
        DeadLine deadline21 = new DeadLine(20, "Audit Test", "28/11/2016 18:00:00");
        DeadLine deadline22 = new DeadLine(21, "Début stage I3", "27/02/2017 07:00:00");
        DeadLine deadline23 = new DeadLine(22, "Remise diplome", "10/09/2017 18:00:00");

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

        deadlinebdd.insertDeadLine(deadline8);
        deadlinebdd.insertDeadLine(deadline9);
        deadlinebdd.insertDeadLine(deadline10);
        deadlinebdd.insertDeadLine(deadline11);
        deadlinebdd.insertDeadLine(deadline12);
        deadlinebdd.insertDeadLine(deadline13);
        deadlinebdd.insertDeadLine(deadline14);

        deadlinebdd.insertDeadLine(deadline15);
        deadlinebdd.insertDeadLine(deadline16);
        deadlinebdd.insertDeadLine(deadline17);
        deadlinebdd.insertDeadLine(deadline18);
        deadlinebdd.insertDeadLine(deadline19);
        deadlinebdd.insertDeadLine(deadline20);
        deadlinebdd.insertDeadLine(deadline21);
        deadlinebdd.insertDeadLine(deadline22);
        deadlinebdd.insertDeadLine(deadline23);

        deadlinebdd.close();
    }

    public void onResume() {
        super.onResume();
        boolean activeNotif = true;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 7);

        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(this, DeadlineService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        am.cancel(pi);
        if (activeNotif) {
            am.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(),
                    24*60*1000, pi);
        }
    }

}