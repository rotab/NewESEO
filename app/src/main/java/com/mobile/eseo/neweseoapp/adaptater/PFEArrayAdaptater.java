package com.mobile.eseo.neweseoapp.adaptater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.eseo.neweseoapp.R;
import com.mobile.eseo.neweseoapp.model.PFE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by etudiant on 09/11/2016.
 */

public class PFEArrayAdaptater extends ArrayAdapter<PFE> {


    private Context context;
    private List<PFE> pfes;

    //constructor, call on creation
    public PFEArrayAdaptater(Context context, int resource, ArrayList<PFE> objects) {
        super(context, resource, objects);

        this.context = context;
        this.pfes = objects;
    }


    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        PFE pfe = pfes.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pfe_layout, null);

        TextView titre = (TextView) view.findViewById(R.id.titre);
        TextView problematique = (TextView) view.findViewById(R.id.problematique);

        //set address and description
        titre.setText(pfe.getTitre());

        //display trimmed excerpt for description
        int motifLength = pfe.getTitre().length();
        if (motifLength >= 100) {
            String motifTrim = pfe.getTitre().substring(0, 100) + "...";
            titre.setText(motifTrim);
        } else {
            titre.setText(pfe.getTitre());
        }

        problematique.setText(pfe.getProblematique());

        //get the image associated with this property


        return view;
    }
}
