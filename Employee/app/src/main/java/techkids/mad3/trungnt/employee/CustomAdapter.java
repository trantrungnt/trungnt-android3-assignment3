package techkids.mad3.trungnt.employee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TrungNT on 4/10/2016.
 */
public class CustomAdapter extends ArrayAdapter<Employee> {
    private TextView tvName, tvAge, tvBirthday;

    public CustomAdapter(Context context, ArrayList<Employee> employees) {
        super(context, 0, employees);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Employee employee = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_employee_list, parent, false);
        }
        // Lookup view for data population
        tvName = (TextView) convertView.findViewById(R.id.txtDisplayName);
        tvAge = (TextView) convertView.findViewById(R.id.txtDisplayAge);
        tvBirthday = (TextView) convertView.findViewById(R.id.txtDisplayBirthday);
        // Populate the data into the template view using the data object
        tvName.setText(employee.getName());
        tvAge.setText(employee.getAge());
        tvBirthday.setText(employee.getBirthday());
        // Return the completed view to render on screen
        return convertView;
    }
}
