package techkids.mad3.trungnt.employee;

import android.app.Application;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by TrungNT on 4/8/2016.
 */
public class EmployeeManager extends Application { //Application class ban than no da la 1 singleton nen ta khong can khai bao bien static
    private static EmployeeManager ourInstance = new EmployeeManager();
    private ArrayList<Employee> arrListEmployee = new ArrayList<>();

    public ArrayList<Employee> getArrEmployee() {
        return arrListEmployee;
    }

    public static EmployeeManager getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }
}
