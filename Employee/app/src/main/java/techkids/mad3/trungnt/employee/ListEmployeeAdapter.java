package techkids.mad3.trungnt.employee;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TrungNT on 4/18/2016.
 */
public class ListEmployeeAdapter extends BaseAdapter{
    private ArrayList<Employee> arrListEmployee;
    private int layoutItemID;
    private Context mContext;
    private TextView tvName, tvAge, tvJob;
    private ImageView avatar;
    private Button btnClose;

    public ListEmployeeAdapter(Context mContext, int layoutItemID, ArrayList<Employee> arrListEmployee)
    {
        this.mContext = mContext;
        this.arrListEmployee = arrListEmployee;
        this.layoutItemID = layoutItemID;
    }

    @Override
    public int getCount() {
        return arrListEmployee.size();
    }

    @Override
    public Employee getItem(int position) {
        return arrListEmployee.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Employee employee = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_employee_list_new, parent, false);
        }

        avatar = (ImageView) convertView.findViewById(R.id.imgEmployee);
        tvName = (TextView) convertView.findViewById(R.id.txtDisplayNameNew);
        tvAge =(TextView) convertView.findViewById(R.id.txtDisplayAgeNew);
        tvJob = (TextView) convertView.findViewById(R.id.txtDisplayJobNew);

        btnClose = (Button) convertView.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //xoa du lieu tai vi tri position cua Phan tu trong ListView
                arrListEmployee.remove(position);
                //thong bao cho ListView biet du lieu da duoc thay doi
                notifyDataSetChanged();
            }
        });

        avatar.setImageResource(R.drawable.trungnt0);
        tvName.setText(employee.getName().toString());
        tvAge.setText(employee.getAge().toString());
        tvJob.setText(employee.getJob().toString());

        return convertView;
    }
}
