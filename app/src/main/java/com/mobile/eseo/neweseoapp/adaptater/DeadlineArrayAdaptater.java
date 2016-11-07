/**
 * Created by WatchYourStep on 17/10/2016.
 */

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
import com.mobile.eseo.neweseoapp.model.DeadLine;

import java.util.ArrayList;
import java.util.List;

public class DeadlineArrayAdaptater extends ArrayAdapter<DeadLine> {

    private Context context;
    private List<DeadLine> deadlines;

    //constructor, call on creation
    public DeadlineArrayAdaptater(Context context, int resource, ArrayList<DeadLine> objects) {
        super(context, resource, objects);

        this.context = context;
        this.deadlines = objects;
    }


    //called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        DeadLine deadline = deadlines.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.deadline_layout, null);

        TextView motif = (TextView) view.findViewById(R.id.motif);
        TextView limite = (TextView) view.findViewById(R.id.limite);
        ImageView imageDeadline = (ImageView) view.findViewById(R.id.imageDeadline);

        //set address and description
        motif.setText(deadline.getMotif());

        //display trimmed excerpt for description
        int motifLength = deadline.getMotif().length();
        if (motifLength >= 100) {
            String motifTrim = deadline.getMotif().substring(0, 100) + "...";
            motif.setText(motifTrim);
        } else {
            motif.setText(deadline.getMotif());
        }

        limite.setText(deadline.getLimite());

        //get the image associated with this property
        imageDeadline.setImageResource(R.drawable.ic_warning_red);

        return view;
    }
}

