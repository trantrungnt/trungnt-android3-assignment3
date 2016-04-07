package techkids.mad3.trungnt.employee;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEmployeeActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editTextName, editTextAge, editTextBirthday;
    Button btnAdd;
    DatePickerDialog datePickerDialogBirthday;
    DateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        initView();
        setDataDatePickerDialogBirthday();
    }

    private void initView()
    {
        btnAdd = (Button) this.findViewById(R.id.btnAdd);
        editTextName = (EditText) this.findViewById(R.id.editTextName);
        editTextAge = (EditText) this.findViewById(R.id.editTextAge);
        editTextBirthday = (EditText) this.findViewById(R.id.editTextBirthday);

        btnAdd.setOnClickListener(this);
        editTextBirthday.setOnClickListener(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.editTextBirthday)
            datePickerDialogBirthday.show();
    }

    //dinh nghia phuong thuc hien thi Hop thoai Calendar trong editTextBirthday
    private void setDataDatePickerDialogBirthday()
    {
        editTextBirthday.setInputType(InputType.TYPE_NULL);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialogBirthday = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextBirthday.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }
}
