package techkids.mad3.trungnt.employee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddEmployeeActivity extends AppCompatActivity {
    EditText editTextName, editTextAge, editTextBirthday;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        initView();
    }

    private void initView()
    {
        btnAdd = (Button) this.findViewById(R.id.btnAdd);

    }
}
