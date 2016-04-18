package techkids.mad3.trungnt.employee;

/**
 * Created by TrungNT on 4/8/2016.
 */
public class Employee  {
    private String name;
    private String age;
    private String birthday;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    private int avatar;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    private String address;
    private String job;

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
        this.address = "";
        this.job = "";
        this.avatar = 0;
    }

    public Employee(String name, String age, String birthday, String address, String job, int avatar)
    {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.address = address;
        this.job = job;
        this.avatar = avatar;
    }
}
