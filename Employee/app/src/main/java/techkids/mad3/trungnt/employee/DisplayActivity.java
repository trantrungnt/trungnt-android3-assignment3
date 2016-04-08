package techkids.mad3.trungnt.employee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by TrungNT on 4/7/2016.
 */
public class DisplayActivity extends AppCompatActivity {
    Employee employee;
    Bundle bundle;
    ListView listViewEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_employee);
        loadListEmployee();
    }

    private void loadListEmployee()
    {
        bundle = getIntent().getExtras();
        employee = (Employee) bundle.getSerializable("emp1");

        String[] arrEmployee = {employee.getName() + "\n" + employee.getAge() + "\n" + employee.getBirthday()};
        listViewEmployee = (ListView) this.findViewById(R.id.listViewListEmployee);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrEmployee);
        listViewEmployee.setAdapter(adapter);
    }
}
