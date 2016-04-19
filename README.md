# trungnt-android3-assignment3
##Yêu cầu
+ Tạo giao diện Add Employee gồm các field: Tên, Tuổi và Ngày Tháng Năm sinh
+ Tạo giao diện Hiển thị tối đa 10 người Employee (fixed cứng hiển thị 10 người và có sử dụng Scroll View trong giao diện Hiển thị)
+ Trong giao diện Hiển thị Danh sách Employee, vùng chứa nút Add Button không bị Scroll (nút này luôn được giữ nguyên khi ta vuốt màn hình và xem danh sách Employee)
+ Trong từng Item của ListView, Name để chữ đậm màu đen căn trái, Age để chữ xanh là cây căn phải, Birthday để chữ đỏ căn phải
+ Các nút Button để tròn 4 góc
+ Chuyển giao diện Add Employee sang dạng Relative Layout
+ Chuyển giao diện Display Employee sang dạng Relative layout

![Ảnh BTVN Asssigment 3](http://i477.photobucket.com/albums/rr132/trungepu/12974365_1017213228354117_7997807231138500768_n_zpsjaq0k9hr.jpg)

## Yêu cầu cải tiến bài Employee hiển thị như hình dưới:
+ Thêm thông tin Address, Job, Ảnh của Employee
+ Có nút X để Xóa được Item của ListView Employee
+  Có nút Detail để khi nhấn vào nút này, User có thể xem được thông tin chi tiết của Employee được chọn
+ Khi nhấn nút Detail thì hiển thi ra Dialog hiển thị chi tiết thông tin của Employee được chọn

![Ảnh BTVN Assigment 3 - cải tiến 1](http://i477.photobucket.com/albums/rr132/trungepu/12963559_1021955897879850_8709784403973420724_n_zpsneaf0tfd.jpg)

##Chú ý khi code
+ Có nhiều cách chuyển data từ Anctivity này sang Activity khác (dùng bundle kết hợp Intent hoặc Dùng Singleton). Ở đây, mình dùng Singleton: 1 thực thể EmployeeManager để dùng mảng Vector lưu trữ các Employe khi được add vào vector có kiểu dữ  liệu là Employee (định nghĩa Singleton này và đính kèm singleton này vào application hiện tại của ta)
```
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

```

+ Mỗi lần nhập dữ liệu từ Form Add Employee, ta lưu các giá trị của các thuộc tính của Employee class vào mảng Vector có kiểu Employee
```
 //luu du lieu hoac hien thi du lieu ....
                String name = editTextName.getText().toString();
                String age = editTextAge.getText().toString();
                String birthday = editTextBirthday.getText().toString();

                Employee employee = new Employee(name, age, birthday);
                //String emp = employee.getName() + "\n" + employee.getAge() + "\n" + employee.getBirthday();
                EmployeeManager.getInstance().getArrEmployee().addElement(employee);

                Intent intent = new Intent(AddEmployeeActivity.this, DisplayActivity.class);
                startActivity(intent);
                this.finish();
```


+  Truy cập mảng Vector kiểu dữ liệu Employee để lấy dữ liệu, sau đó chuyển dữ liệu này vào ArrayList và cho hiển thị với ListView
```
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

        Vector<Employee> vecListEmp = EmployeeManager.getInstance().getArrEmployee();
        arrayListEmployee = new ArrayList<String>();

        for (Employee employee : vecListEmp)
             arrayListEmployee.add(employee.getName() + "\n" + employee.getAge() + "\n" + employee.getBirthday());

        listViewEmployee = (ListView) this.findViewById(R.id.listViewListEmployee);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListEmployee);
        listViewEmployee.setAdapter(adapter);
    }
```

+ Chuyển EmployeeManager thành Singleton kế thừa từ class Application. Trong file Manifext.xml, ta chỉ rõ name Application là EmployeeManager
```
<application
        android:name=".EmployeeManager"
       ...
>
...
</application>

```

+ Định nghĩa Singleton: EmployeeManaager và kế thừa Application class. Bản thân Application class của Android đã là 1 singleton. Do thực thể Singleton này ở mức Application nên nếu các activity mà bị chết Singleton này vẫn tồn tại. Trong  Hệ điều hành Android, nó sẽ làm việc với mức Application trước, sau đó activity nào đặt là Main thì nó mới load đến Activity đó.Ở đây, EmployeeManager có nhiệm vụ lưu trữ mảng ArrayList có kiểu dữ liệu là Employee (mỗi lần nhập từ Form Add Employee thì mảng này có nhiệm vụ giữ giá trị thuộc tính của Employee vừa nhập)
```
public class EmployeeManager extends Application { //Application class ban than no da la 1 singletonbien
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
```

+ Định nghĩa giao diện Layout cho Item trong ListView  trong file activity_employee_list với yêu cầu: Name để chữ đậm căn trái, Age để chữ màu xanh lá cây căn phải và Birthday để chữ màu đỏ căn phải 

+ Định nghĩa class CustomAdapter để dùng gắn vào Adapter của ListView ( ta sẽ kết nối file giao diện activity_employee_list.xml của Item trong ListView bên trong CustomAdapter class này)
```
public class CustomAdapter extends ArrayAdapter<Employee>{
  ....
   @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Employee employee = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_employee_list, parent, false);
        }
        // Lookup view for data population
        tvName = (TextView) convertView.findViewById(R.id.txtDisplayName);
        tvAge = (TextView) convertView.findViewById(R.id.txtDisplayAge);
        tvBirthday = (TextView) convertView.findViewById(R.id.txtDisplayBirthday);
        // Populate the data into the template view using the data object
        tvName.setText(employee.getName());
        tvAge.setText(employee.getAge());
        tvBirthday.setText(employee.getBirthday());
        // Return the completed view to render on screen
        return convertView;
    }

}
```

+ Trong DisplayActivity, ta khởi tạo adapter có kiểu dữ liệu CustomAdapter class và lấy dữ liệu từ mảng Employee do Singleton EmployeeManager quản lý. Sau đó, ta gắn adapter này vào ListView có ID là R.id.listViewListEmployee
```
        CustomAdapter adapter = new CustomAdapter(this, EmployeeManager.getInstance().getArrEmployee());
        listViewEmployee = (ListView) this.findViewById(R.id.listViewListEmployee);
        listViewEmployee.setAdapter(adapter);
```

+ Thuộc tính android:keepScreenOn="true" sẽ giữ cho nút Button không bị Scroll
```
    android:keepScreenOn="true"
```

+ Lúc load dữ liệu, xử lý dữ liệu nặng thì phải để trong hàm onResume(), hàm này được load khi User tương tác Activity. Hàm start nên để xử lý các dạng nhẹ nhất có thể
```
@Override
    protected void onResume()
    {
        super.onResume();
        //load data nen de trong phuong thuc onResume
        loadListEmployee();
    }
```

+ Để  Listview nhận được sự kiện click từng Item, ta phải thêm thuộc tính 
Trong file giao diện thiết kế hiển thị 1 Item activity_employee_list_new.xml, ta thêm thuộc tính android:descendantFocusability="blocksDescendants"
```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal"
    android:descendantFocusability="blocksDescendants"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    ...
</LinearLayout>
```
Trong file giao diện chính chưa ListView (cụ thể là file activity_display_employee.xml), ta thêm các thuộc tính:
```
<ListView
            android:id="@+id/listViewListEmployee"
            android:focusableInTouchMode="false"
            android:focusable="false"
            android:clickable="false"
            android:layout_width="match_parent"
            android:layout_height="@dimen/list_view_employee_height">
</ListView>
```
Sau đó, ta cài đặt trong code Java sự kiện click Item
```
        ListEmployeeAdapter listEmployeeAdapter = new ListEmployeeAdapter((Context) this, R.layout.activity_add_employee_relativelayout_new , EmployeeManager.getInstance().getArrEmployee());
        listViewEmployee = (ListView) this.findViewById(R.id.listViewListEmployee);
        listViewEmployee.setAdapter(listEmployeeAdapter);

        listViewEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ID selected: ", String.valueOf(position));
            }
        });
```

+ Trong ListView ListEmployeeAdapter, ta xóa dữ liệu 
```
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
```

+ Định nghĩa template cho giao diện của Dialog Detial Dialog trong file activity_employee_detail
+ Trong file ListEmployeeAdapter, ta định nghĩa Dialog Alert Detail Employee và kết nối giao diện cho Dialog này. Dialog Alert Detail Employee hiển thị thông tin chi tiết của 1 Employee mà ta chọn khi nhấn nút Detail trong ListView: ListEmployee
```
 btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDetail = new Dialog(mContext);

                //su dung template activity_employee_detail cho UI cua Dialog Alert
                dialogDetail.setContentView(R.layout.activity_employee_detail);
                //tao title cho AlertDialog
                dialogDetail.setTitle(titleDialogAlertDetail);

                avatarDetail = (ImageView) dialogDetail.findViewById(R.id.imgEmployeeDetail);
                tvDetailName = (TextView) dialogDetail.findViewById(R.id.txtDisplayDetailNameNew);
                tvDetailAge = (TextView) dialogDetail.findViewById(R.id.txtDisplayDetailAge);
                tvDetailBirthday = (TextView) dialogDetail.findViewById(R.id.txtDisplayDetailBirthday);
                tvDetailAddress = (TextView) dialogDetail.findViewById(R.id.txtDisplayDetailAddress);
                tvDetailJob = (TextView) dialogDetail.findViewById(R.id.txtDisplayDetailJob);
                btnDetailBack = (Button) dialogDetail.findViewById(R.id.btnDetailBack);

                //day du lieu vao AlertDialog
                avatarDetail.setImageResource(R.drawable.trungnt0);
                tvDetailName.setText(employee.getName().toString());
                tvDetailAge.setText(employee.getAge().toString());
                tvDetailBirthday.setText(employee.getBirthday().toString());
                tvDetailAddress.setText(employee.getAddress().toString());
                tvDetailJob.setText(employee.getJob().toString());

                //su kien cua nut btnDetailBack trong Dialog Detail
                btnDetailBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogDetail.dismiss();
                    }
                });

                dialogDetail.show();
            }
        });
```


##Môi trường phát triển
+ Bộ công cụ Android Studio 
+ App phát triển với api18 và trên hệ điều hành Android version 4.3
+ Dùng hệ điều hành ảo Genymotion: sử dụng api18 và hệ điều hành Android version 4.3

##Demo bài tập
+ [Video demo bài tập Assigment số 3 xem tại đây](https://youtu.be/wch_cK3ha2U)
+ [Video demo sử dụng Relative khi sử thiết kế Layout cho giao diện Add Employee và DisplayEmployee](https://youtu.be/lyoeIsFwW5o)

##Tham khảo
+ [Tham khảo cách làm tròn 4 góc của nút Button](http://belencruz.com/2012/12/rounded-button-with-shadow-in-android/)
+ [How to customize different buttons in Android](http://mrbool.com/how-to-customize-different-buttons-in-android/27747)
+ [Change style of button](http://slidenerd.com/2014/09/01/3-making-buttons-with-rounded-corners-custom-drawables-in-android/)
+ [Sử dụng Application Object trong Android](https://www.mobomo.com/2011/05/how-to-use-application-object-of-android/)
+ [Android: Make use of Android Application Class as Singleton Object](https://tausiq.wordpress.com/2013/01/27/android-make-use-of-android-application-class-as-singleton-object/)
+ [Using lists in Android (ListView) - Tutorial](http://www.vogella.com/tutorials/AndroidListView/article.html)
+ [Using an ArrayAdapter with ListView](https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView)
+ [Tham khảo Relative Layout](http://code5s.com/di-dong/android/relative-layout.html)
+ [Make a custom listview row with clickable buttons in it selectable using a custom cursoradapter ](https://syedasaraahmed.wordpress.com/2013/02/08/make-a-custom-listview-row-with-clickable-buttons-in-it-selectable-using-a-custom-cursoradapter/)
+ [Tham khảo Custom Diaglog trong Android](http://androidexample.com/Custom_Dialog_-_Android_Example/index.php?view=article_discription&aid=88&aaid=111)
+ [Update Application for on Screen](http://android-developers.blogspot.com/2009/04/updating-applications-for-on-screen.html)
