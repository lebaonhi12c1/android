package com.example.lebaonhi_dh51904155;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lebaonhi_dh51904155.database.khoadao;
import com.example.lebaonhi_dh51904155.datahelper.datahelper;
import com.example.lebaonhi_dh51904155.model.classes;

public class UpdateClassesActivity extends AppCompatActivity {
    Button luu;
    EditText tenkhoa;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_classes);
        luu = findViewById(R.id.btn_save_update_class);
        tenkhoa = findViewById(R.id.update_classes_name);
        getgiaodien();
        luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
                Intent intent = new Intent(getApplicationContext(),ProccessClassesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void getgiaodien(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",-1);
        khoadao khoadao = new khoadao(this);

        classes khoa = khoadao.getHhoa(id);
        tenkhoa.setText(khoa.getName());
        Toast.makeText(this,id+"",Toast.LENGTH_SHORT).show();
    }
    public void update(){
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",-1);
        khoadao khoadao = new khoadao(this);

        classes khoa = khoadao.getHhoa(id);
        khoa.setName(tenkhoa.getText().toString());
        Toast.makeText(getApplicationContext(),"successfully",Toast.LENGTH_SHORT).show();

        khoadao.getupdate(khoa);
    }

}