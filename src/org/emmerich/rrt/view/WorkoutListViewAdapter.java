package org.emmerich.rrt.view;

import java.util.List;

import org.emmerich.rrt.R;
import org.emmerich.rrt.model.Workout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WorkoutListViewAdapter extends ArrayAdapter<Workout> {
	
	private Context context;
	private List<Workout> workouts;

    public WorkoutListViewAdapter(Context context, List<Workout> workouts) {

        super(context, R.layout.workout_list_item, workouts);

        this.context = context;
        this.workouts = workouts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater 
        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.workout_list_item, parent, false);

        // 3. Get the two text view from the rowView
        TextView nameView = (TextView) rowView.findViewById(R.id.workout_name);

        // 4. Set the text for textView 
        nameView.setText(workouts.get(position).getName());

        // 5. retrn rowView
        return rowView;
    }

}
