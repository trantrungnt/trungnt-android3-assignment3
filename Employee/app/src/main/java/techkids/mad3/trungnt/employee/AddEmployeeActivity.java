package techkids.mad3.trungnt.employee;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

        if (id == R.id.btnAdd)
        {
            if (
                    editTextName.getText().length() == 0
                 || editTextName.getText().toString().isEmpty()
                 || editTextAge.getText().length() == 0
                 || editTextAge.getText().toString().isEmpty()
                 || editTextBirthday.getText().length() == 0
                 || editTextBirthday.getText().toString().isEmpty()
            )

            {
                Context context = this;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title
                alertDialogBuilder.setTitle("WARNING ...");

                // set dialog message
                alertDialogBuilder
                        .setMessage("You need to type your data!")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }else
            {
                //luu du lieu hoac hien thi du lieu ....
                String name = editTextName.getText().toString();
                String age = editTextAge.getText().toString();
                String birthday = editTextBirthday.getText().toString();

                Employee employee = new Employee(name, age, birthday);
                Bundle bundle = new Bundle();
                bundle.putSerializable("emp1", employee);
                Intent intent = new Intent(AddEmployeeActivity.this, DisplayActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
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
