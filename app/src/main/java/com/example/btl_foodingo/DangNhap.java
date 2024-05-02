package com.example.btl_foodingo;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class DangNhap extends AppCompatActivity {

    EditText taikhoan, matkhau;
    TextView dangki;
    ConstraintLayout btn_dangnhap;
    DBHelper db;

    public static String User;

    public static String getUser() {
        return User;
    }

    public static void setUser(String user) {
        User = user;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);

        taikhoan = (EditText) findViewById(R.id.taikhoan);
        matkhau = (EditText) findViewById(R.id.matkhau);
        dangki = (TextView) findViewById(R.id.txt_dangki);
        btn_dangnhap = (ConstraintLayout) findViewById(R.id.btn_dangnhap);
        db = new DBHelper(this);


        // Xử lý sự kiện cho nút đăng nhập
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtUsername = taikhoan.getText().toString();
                String txtPassword = matkhau.getText().toString();

                if(TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtPassword)){
                    Toast.makeText(DangNhap.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkIfno = db.checkUserNamePassword(txtUsername, txtPassword);
                    if(checkIfno == true){
                        setUser(db.getName(txtUsername, txtPassword));
                        Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(DangNhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangKi.class);
                startActivity(intent);
            }
        });

    }
}
