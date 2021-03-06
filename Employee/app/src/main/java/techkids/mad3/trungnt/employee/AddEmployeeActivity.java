package techkids.mad3.trungnt.employee;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

public class AddEmployeeActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener{
    private EditText editTextName, editTextAge, editTextBirthday, editTextAddress, editTextJob;
    private Button btnAdd;
    private DatePickerDialog datePickerDialogBirthday;
    private DateFormat dateFormatter;
    private Context context;
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private Employee employee;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee_relativelayout_new);
        initView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //load du lieu nang thi nen cho vao ham Resume
        setDataDatePickerDialogBirthday();
    }

    private void initView()
    {
        btnAdd = (Button) this.findViewById(R.id.btnAddNew);
        editTextName = (EditText) this.findViewById(R.id.editTextName);
        editTextAge = (EditText) this.findViewById(R.id.editTextAge);
        editTextBirthday = (EditText) this.findViewById(R.id.editTextBirthday);
        editTextAddress = (EditText) this.findViewById(R.id.editTextAddress);
        editTextJob = (EditText) this.findViewById(R.id.editTextJob);

        btnAdd.setOnClickListener(this);
        editTextBirthday.setOnClickListener(this);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        editTextJob.setOnEditorActionListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.editTextBirthday)
                                datePickerDialogBirthday.show();

        String name, age, birthday, address, job;

        name = editTextName.getText().toString();
        age = editTextAge.getText().toString();
        birthday = editTextBirthday.getText().toString();
        address = editTextAddress.getText().toString();
        job = editTextJob.getText().toString();

        if (id == R.id.btnAddNew)
        {
            //TextUtils , shared preference, luu vao file, luu vao sqllite, nen dat ten bien chung trong file dimen ///// persistence storage, su khac nhau khi de startACtivity /// flag full screen
            if ((
                 //textUntils se kiem tra gia tri null, gia tri empty, gia tri null
                 TextUtils.isEmpty(name) ||
                 TextUtils.isEmpty(age) ||
                 TextUtils.isEmpty(birthday) ||
                 TextUtils.isEmpty(address) ||
                 TextUtils.isEmpty(job)
            ) && (displayAlertDialog().isShowing()==false))

            {
                // goi ham alertDialog va show no ra
                displayAlertDialog().show();
            }else
            {
                //luu du lieu hoac hien thi du lieu ....
                employee = new Employee(name, age, birthday, address, job, 0);
                EmployeeManager.getInstance().getArrEmployee().add(employee);

                Intent intent = new Intent(AddEmployeeActivity.this, DisplayActivity.class);
                startActivity(intent);
                this.finish();
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

    //dinh nghia phuong thuc Alert Dialog, sau do luc can hien thi Alert Dialog thi show ra
    private AlertDialog displayAlertDialog()
    {
        context = this;
        alertDialogBuilder = new AlertDialog.Builder(context);
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
        alertDialog = alertDialogBuilder.create();
        return alertDialog;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_DONE) {
            System.out.println("You click DONE");
            imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return true;
        }
        return false;
    }
}
