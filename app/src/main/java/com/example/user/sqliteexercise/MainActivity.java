package com.example.user.sqliteexercise;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final StudentDbHelper helper=new StudentDbHelper(this);
    EditText name,id,email;
    Button save,view,update,delete;
    TextView t1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        id=(EditText)findViewById(R.id.id);
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);

        save=(Button) findViewById(R.id.save);
        update=(Button) findViewById(R.id.update);
        delete=(Button) findViewById(R.id.delete);
        view=(Button) findViewById(R.id.view);

        t1=(TextView)findViewById(R.id.text) ;

        insert();
        view();
        update();
        delete();

    }

    public void insert(){

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean check= helper.insert(name.getText().toString(),id.getText().toString(),email.getText().toString());

                if(check==true)
                    Toast.makeText(MainActivity.this,"Data Succeesfully Inserted ",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data inserted Failed ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void view(){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor=helper.viewData();
                if(cursor.getCount()==0){

                    Toast.makeText(MainActivity.this,"Data not found",Toast.LENGTH_SHORT).show();
                    showData("Error","Nothing found");
                }

                cursor.moveToFirst();

                StringBuffer stringBuffer=new StringBuffer();
                do {
                    stringBuffer.append("name: " +cursor.getString(0)+" \nid: "+cursor.getString(1)+" \nmarks: "+cursor.getString(2)+"\n\n");
                }while (cursor.moveToNext());

                //  t1.setText((stringBuffer.toString()));
                showData("Data",stringBuffer.toString());
            }
        });
    }


    public void update(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUpdate = helper.UpdateData(name.getText().toString(),id.getText().toString(),email.getText().toString());

                if(isUpdate==true)
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Updated ",Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void delete(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer check = helper.DeleteData(id.getText().toString());

                if(check!=0)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Data Not Deleted ",Toast.LENGTH_SHORT).show();
            }
        });

    }


    public  void showData(String tittle,String massage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(tittle);
        builder.setMessage(massage);
        builder.show();
    }
}
