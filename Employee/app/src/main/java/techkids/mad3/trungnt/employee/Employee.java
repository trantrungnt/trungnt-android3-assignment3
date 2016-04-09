package techkids.mad3.trungnt.employee;

/**
 * Created by TrungNT on 4/8/2016.
 */
public class Employee  {
    private String name;
    private String age;
    private String birthday;
    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getBirthday() {
        return birthday;
    }
    private Employee()
    {
        this.name = "";
        this.age = "";
        this.birthday = "";
    }

    public Employee(String name, String age, String birthday)
    {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }
}
