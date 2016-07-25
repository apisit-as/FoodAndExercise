package rmuti.food;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class DataBase extends AppCompatActivity {

    private String check,name,calories;  // ข้อมูลที่ได้รับมาจาก MainActivity

    //database
    String sql1,sql2;  // db food , db exercise
    private SQLiteDatabase db1; //db food
    private SQLiteDatabase db2; // db exercise
    private Cursor c1,c2;  // db food , db exercise

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database();

        //เรียกมาจาก MainActivity
        name  = getIntent().getExtras().getString("name");
        calories  = getIntent().getExtras().getString("cal");
        check  = getIntent().getExtras().getString("check");

        if(check.equals("0"))       addFood();     // เพิ่มข้อมูลลง db_Food (อาหาร)
        else if(check.equals("1"))  addExercise(); // เพิ่มข้อมูลลง db_Exercise (ออกกำลังกาย)
        else if(check.equals("3"))  showFood();    // show Food  แสดงผล ค่าอาหาร
        else if(check.equals("4"))  showExercise();// show Exercise แสดงผล ค่าออกกำลังกาย
    }

    public void database(){
        // Database  ประกอบด้วย db_Food เก็บข้อมูลอาหาร
        // และ db_Exercise  เก็บข้อมูลการออกกำลังกาย
        db1 = this.openOrCreateDatabase("db_Food",MODE_PRIVATE,null);
        sql1 = ""
                + " CREATE TABLE IF NOT EXISTS Food("
                + "   id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "   name VARCHAR," + " calories VARCHAR" + " )";
        db1.execSQL(sql1);

        db2 = this.openOrCreateDatabase("db_Exercise", MODE_PRIVATE, null);
        sql2 = ""
                + " CREATE TABLE IF NOT EXISTS Exercise("
                + "   id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "   name VARCHAR," + " calories VARCHAR" + " )";
        db2.execSQL(sql2);
    }
    public void addFood(){
        //เพิ่มข้อมูลลง database db_Food
        sql1 = "";
        sql1 = "INSERT INTO Food VALUES(null, ':name', ':calories')";
        sql1 = sql1.replace(":name",name);
        sql1 = sql1.replace(":calories", calories);
        db1.execSQL(sql1);

        Intent intent = new Intent(DataBase.this, MainActivity.class);
        startActivity(intent);
    }
    public void addExercise(){
        //เพิ่มข้อมูลลง database  db_Exercise
        sql2 = "";
        sql2 = "INSERT INTO Exercise VALUES(null, ':name', ':calories')";
        sql2 = sql2.replace(":name",name);
        sql2 = sql2.replace(":calories",calories);
        db2.execSQL(sql2);
        Intent intent = new Intent(DataBase.this, MainActivity.class);
        startActivity(intent);
    }
    public void showFood(){
        //แสดงผลข้อมูลของ อาหาร
        setContentView(R.layout.show_food);
        sql1 = "";
        sql1 = "SELECT * FROM Food";
        c1 = db1.rawQuery(sql1, null);

        ArrayList ListNameFood = new ArrayList();
        ArrayList ListCalFood = new ArrayList();
        while(c1.moveToNext()){
            int index1 = c1.getColumnIndex("name"); //ต้องการส่วน name ใน dababase   db_Food
            int index2 = c1.getColumnIndex("calories"); //ต้องการส่วน name ใน dababase   db_Food
            ListNameFood.add(c1.getString(index1));
            ListCalFood.add(c1.getString(index2));
        }

        TableLayout tlb = (TableLayout)findViewById(R.id.tlb_food);
        for (int i = 0; i < ListNameFood.size(); i++) {
            TableRow tRow = new TableRow(this);
            TextView txt = new TextView(this);
            txt.setText(ListNameFood.get(i)+"      "+ListCalFood.get(i)+"   กิโลแคลอรี่");
            txt.setTextSize(15);
            tRow.addView(txt);
            tlb.addView(tRow);
        }
    }
    public void showExercise(){
        //แสดงผลข้อมูลของ การออกกำลังกาย
        setContentView(R.layout.show_exercise);
        sql2 = "";
        sql2 = "SELECT * FROM Exercise";
        c2 = db2.rawQuery(sql2, null);

        ArrayList ListNameExercise = new ArrayList();
        ArrayList ListCalExercise = new ArrayList();
        while(c2.moveToNext()){
            int index1 = c2.getColumnIndex("name"); //ต้องการส่วน name ใน dababase   db_Exercise
            int index2 = c2.getColumnIndex("calories"); //ต้องการส่วน name ใน dababase   db_Exercise
            ListNameExercise.add(c2.getString(index1));
            ListCalExercise.add(c2.getString(index2));
        }
        TableLayout tlb = (TableLayout)findViewById(R.id.tlb_exercise);
        for (int i = 0; i < ListNameExercise.size(); i++) {
            TableRow tRow = new TableRow(this);
            TextView txt = new TextView(this);
            txt.setText(ListNameExercise.get(i)+"      "+ListCalExercise.get(i)+"   กิโลแคลอรี่/ชั่วโมง");
            txt.setTextSize(15);
            tRow.addView(txt);
            tlb.addView(tRow);
        }
    }
    public void doBack(View v){
        //กลับสู่หน้าหลัก
        Intent intent = new Intent(DataBase.this, MainActivity.class);
        startActivity(intent);
    }

}
