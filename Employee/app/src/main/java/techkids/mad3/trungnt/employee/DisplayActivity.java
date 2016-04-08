package techkids.mad3.trungnt.employee;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by TrungNT on 4/7/2016.
 */
public class DisplayActivity extends AppCompatActivity {
    Employee employee;
    Bundle bundle;
    ListView listViewEmployee;
    Button btnBack;

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
            }
        });
        //bundle = getIntent().getExtras();
        //ArrayList arrListEmp = bundle.getStringArrayList("arrListEmp");
        ArrayList arrListEmp = AddEmployeeActivity.arrListEmployee;
        listViewEmployee = (ListView) this.findViewById(R.id.listViewListEmployee);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrListEmp);
        listViewEmployee.setAdapter(adapter);
    }
}
