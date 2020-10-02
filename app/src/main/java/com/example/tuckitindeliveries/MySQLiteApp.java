package com.example.tuckitindeliveries;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.tuckitindeliveries.DBHelper.CUSTOMER_TABLE;
//import static com.example.tuckitindeliveries.DBHelper.addOne;

public class MySQLiteApp extends AppCompatActivity {

    Button btn_add;
    Button btn_viewAll;
    EditText et_name;
    EditText et_age;
    Switch sw_activeCustomer;
    ListView lv_customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_s_q_lite_app);

        btn_add = findViewById(R.id.btn_Add);
        btn_viewAll = findViewById(R.id.btn_viewAll);
        et_age = findViewById(R.id.et_age);
        et_name = findViewById(R.id.et_name);
        sw_activeCustomer = findViewById(R.id.sw_active);
        lv_customerList = findViewById(R.id.lv_customerList);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerModel customerModel;

                try{

                    customerModel= new CustomerModel(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), sw_activeCustomer.isChecked());
                    Toast.makeText(MySQLiteApp.this,customerModel.toString(), Toast.LENGTH_SHORT).show();

                }catch (Exception e) {
                    Toast.makeText(MySQLiteApp.this,"Error creating Customer", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", 0, false);

                }

                DBHelper dbHelper = new DBHelper(MySQLiteApp.this);
                boolean success;
                if (addOne(customerModel)) success = true;
                else success = false;
                Toast.makeText(MySQLiteApp.this, "Success" + success, Toast.LENGTH_SHORT).show();

            }
        });

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbHelper = new DBHelper(MySQLiteApp.this);
                List<CustomerModel> everyone = dbHelper.getEveryone();

                ArrayAdapter customerArrayAdapter = new ArrayAdapter<CustomerModel>(MySQLiteApp.this, android.R.layout.simple_list_item_1, everyone);
                lv_customerList.setAdapter(customerArrayAdapter);

                //Toast.makeText(MySQLiteApp.this,everyone.toString(), Toast.LENGTH_SHORT).show();

            }
        });


    }

    private boolean addOne(CustomerModel customerModel) {
        return true;
    }

}