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

public class DangKi extends AppCompatActivity {

    EditText taikhoan_dki, matkhau_dki, matkhau_nhaplai;
    ConstraintLayout btn_dangki;
    TextView txt_dangnhap;
    DBHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ki);

        taikhoan_dki = (EditText) findViewById(R.id.taikhoan_dki);
        matkhau_dki = (EditText) findViewById(R.id.matkhau_dki);
        matkhau_nhaplai = (EditText) findViewById(R.id.matkhau_nhaplai);
        btn_dangki = (ConstraintLayout) findViewById(R.id.btn_dangki);
        txt_dangnhap = (TextView) findViewById(R.id.txt_dangnhap);
        db = new DBHelper(this);

        btn_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtUsername = taikhoan_dki.getText().toString();
                String txtPassword = matkhau_dki.getText().toString();
                String txtRePassword = matkhau_nhaplai.getText().toString();
                if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtPassword) || TextUtils.isEmpty(txtRePassword))
                    Toast.makeText(DangKi.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                else {
                    if (txtPassword.equals(txtRePassword)) {
                        // Tạo biến kiểm tra tên đăng nhập
                        Boolean checkUsername = db.checkUsername(txtUsername);
                        if (checkUsername == false) {
                            Boolean insert = db.insertUser(txtUsername, txtPassword);
                            if (insert == true) {
                                Toast.makeText(DangKi.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else
                                Toast.makeText(DangKi.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();

                        } else
                            Toast.makeText(DangKi.this, "Người dùng đã tồn tại", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(DangKi.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });


        txt_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), DangNhap.class);
                startActivity(intent);
            }
        });



    }
}
