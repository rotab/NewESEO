package com.mobile.eseo.neweseoapp.adaptater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobile.eseo.neweseoapp.R;
import com.mobile.eseo.neweseoapp.model.Enseignant;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by etudiant on 09/11/2016.
 */

public class EnseignantArrayAdaptater extends ArrayAdapter<Enseignant> {


    private Context context;
    private List<Enseignant> enseignants;

    //constructor, call on creation
    public EnseignantArrayAdaptater(Context context, int resource, ArrayList<Enseignant> objects) {
        super(context, resource, objects);
        this.context = context;
        this.enseignants = objects;
    }


    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        Enseignant enseignant = enseignants.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.enseignant_layout, null);

        TextView nom = (TextView) view.findViewById(R.id.nom);
        TextView fonction = (TextView) view.findViewById(R.id.fonction);

        //set address and description

//

        //display trimmed excerpt for description
        int motifLength = enseignant.getFonction().length();
        if (motifLength >= 100) {
            String motifTrim =  enseignant.getNom()+" " + enseignant.getPrenom() + " " + enseignant.getFonction().substring(0, 100) + "...";
            nom.setText(motifTrim);
        } else {
            nom.setText(enseignant.getFonction());
        }

        nom.setText(enseignant.getNom()+" " + enseignant.getPrenom());
        fonction.setText( "Email : " +enseignant.getEmail() + " \nFonction : " +enseignant.getFonction()+ " \nNumero : 0" +enseignant.getNumero());
        //+ " " + enseignant.getNumero()
        //get the image associated with this property

        return view;
    }
}
