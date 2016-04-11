package techkids.mad3.trungnt.employee;

import android.content.Context;
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
    private ListView listViewEmployee;
    private Button btnBack;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_employee_relativelayout);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //load data nen de trong phuong thuc onResume
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


        CustomAdapter adapter = new CustomAdapter(this, EmployeeManager.getInstance().getArrEmployee());
        listViewEmployee = (ListView) this.findViewById(R.id.listViewListEmployee);
        listViewEmployee.setAdapter(adapter);
    }
}
