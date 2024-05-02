package com.example.btl_foodingo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CaNhan extends Fragment {

    TextView tennguoidung;
    TextView sl_donHang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ca_nhan, container, false);

        tennguoidung = view.findViewById(R.id.tennguoidung);
        tennguoidung.setText(DangNhap.getUser());

        sl_donHang = view.findViewById(R.id.sl_donHang);
        String sl_donhang_format = String.valueOf(GioHang.getSoluong_don());
        sl_donHang.setText(sl_donhang_format);
        return view;
    }
}

