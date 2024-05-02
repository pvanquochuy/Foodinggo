package com.example.btl_foodingo;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class TrangChu extends Fragment {

    ListView lvSanPham;
    List<SanPham> listSanPham = new ArrayList<>();
    AdapterSanPham adapterSanPham;
    DBHelper db;
    TextView name;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.trang_chu, container, false);

        name = view.findViewById(R.id.name);
        name.setText("Xin chào " + DangNhap.getUser());

        initData();  // chuan bi du lieu

        // khoi tao adapter
        adapterSanPham = new AdapterSanPham((Activity) getContext(), R.layout.san_pham, listSanPham);
        lvSanPham = view.findViewById(R.id.listViewSanPham);
        // set adapter cho list view
        lvSanPham.setAdapter(adapterSanPham);



        return view;
    }



    public void initData(){


        SanPham pizza = new SanPham("Pizza", R.drawable.pizza1, 15.1, 1);
        SanPham burger = new SanPham("Burger", R.drawable.burger, 13.1, 1);
        SanPham piizaVegetable = new SanPham("Vegetable Pizza",R.drawable.pizza3 ,10.2, 1);
        listSanPham.add(pizza);
        listSanPham.add(burger);
        listSanPham.add(piizaVegetable);


    }

}