package rmuti.food;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private String name = "";
    private String cal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addFood(View v){  // check 0

        EditText txtName = (EditText) findViewById(R.id.name);
        EditText txtCal = (EditText) findViewById(R.id.cal);

         name = txtName.getText().toString();
         cal  = txtCal.getText().toString();

        if(name.equals("") || cal.equals("")){
            //ในกรณีที่ใส่ข้อมูลไม่ครบช่อง
        }else{
            String check = "0";
            Intent intent = new Intent(MainActivity.this, DataBase.class);
            intent.putExtra("name",name);
            intent.putExtra("cal",cal);
            intent.putExtra("check",check);
            startActivity(intent);
        }
    }   // ส่งค่า check = 0  สำหรับเพิ่มค่าอาหาร

    public void addCal(View v){ // check 1

        EditText txtName = (EditText) findViewById(R.id.name);
        EditText txtCal = (EditText) findViewById(R.id.cal);

        name = txtName.getText().toString();
        cal  = txtCal.getText().toString();

        if(name.equals("") || cal.equals("")){
            //ในกรณีที่ใส่ข้อมูลไม่ครบช่อง
        }else{
            String check = "1";
            Intent intent = new Intent(MainActivity.this, DataBase.class);
            intent.putExtra("name",name);
            intent.putExtra("cal",cal);
            intent.putExtra("check",check);
            startActivity(intent);
        }
    }    // ส่งค่า check = 1  สำหรับเพิ่มค่าออกกำลังกาย

    public void showFood(View v){  // check 3 showFood
        String check = "3";
        Intent intent = new Intent(MainActivity.this, DataBase.class);
        intent.putExtra("check",check);
        startActivity(intent);
    }   // ส่งค่า check = 3  สำหรับแสดงค่าอาหาร

    public void showExercise(View v){
        String check = "4";
        Intent intent = new Intent(MainActivity.this, DataBase.class);
        intent.putExtra("check",check);
        startActivity(intent);
    }   // ส่งค่า check = 4  สำหรับแสดงค่าออกกำลังกาย


}
