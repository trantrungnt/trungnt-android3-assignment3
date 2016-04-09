package techkids.mad3.trungnt.employee;

import java.util.Vector;

/**
 * Created by TrungNT on 4/8/2016.
 */
public class EmployeeManager {
    private static EmployeeManager ourInstance = new EmployeeManager();
    private Vector<Employee> vecEmployee;

    public Vector<Employee> getArrEmployee() {
        return vecEmployee;
    }

    private EmployeeManager()
    {
        vecEmployee = new Vector<>();
    }

    public static EmployeeManager getInstance() {
        return ourInstance;
    }
}
