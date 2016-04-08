package techkids.mad3.trungnt.employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by TrungNT on 4/7/2016.
 */
public class DisplayActivity extends AppCompatActivity {
    Employee employee;
    Bundle bundle;
    ListView listViewEmployee;
    Button btnBack;
    ArrayList<String> arrayListEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_employee);
        loadListEmployee();

    }

    private void loadListEmployee()
    {
        btnBack = (Button) this.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayActivity.this.finish();

                Intent intent = new Intent(DisplayActivity.this, AddEmployeeActivity.class);
                startActivity(intent);
            }
        });

        Vector<Employee> vecListEmp = EmployeeManager.getInstance().getArrEmployee();
        arrayListEmployee = new ArrayList<String>();

        for (Employee employee : vecListEmp)
             arrayListEmployee.add(employee.getName() + "\n" + employee.getAge() + "\n" + employee.getBirthday());

        listViewEmployee = (ListView) this.findViewById(R.id.listViewListEmployee);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListEmployee);
        listViewEmployee.setAdapter(adapter);

    }
}
