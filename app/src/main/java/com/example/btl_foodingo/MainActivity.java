package com.example.btl_foodingo;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<SanPham_GioHang> listSanPham = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Code khởi tạo giao diện trang chủ khi chạy chương trình
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TrangChu homeFragment = new TrangChu();

        fragmentTransaction.add(R.id.fragmentContainer, homeFragment);
        fragmentTransaction.addToBackStack(null); // Để cho phép người dùng quay lại các fragment trước đó
        fragmentTransaction.commit();




    }

    //    Hàm xử lí sự kiện chuyển đổi các giao diện(fragment) khi bấm nút
    // lưu ý: Trong các file xml chỗ các nút thêm thuộc tính android:onClick="AddFragment" cho tất cả các nút
    public void AddFragment(View view){
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        androidx.fragment.app.Fragment fragment = null;

        if(view.getId() == R.id.btnTrangChu){
            fragment = new TrangChu();
        }else if(view.getId() == R.id.btnGioHang){
            fragment = new GioHang();
        }else if(view.getId() == R.id.btnCaNhan){
            fragment = new CaNhan();
        }
        if (fragment != null) {
            fragmentTransaction.replace(R.id.fragmentContainer, fragment);
            fragmentTransaction.addToBackStack(null); // Để cho phép người dùng quay lại các fragment trước đó
            fragmentTransaction.commit();
        }
    }

}
