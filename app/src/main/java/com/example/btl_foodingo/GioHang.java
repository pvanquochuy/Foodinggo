package com.example.btl_foodingo;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHang extends Fragment {

    ListView listViewGioHang;
    public static List<SanPham_GioHang> gioHangItems = new ArrayList<>();
    AdapterGioHang adapterGioHang;
    public static DBHelper db;
    public static int soluong_don = 0;
    TextView btn_dathang;

    public static int getSoluong_don() {
        return soluong_don;
    }

    public static void setSoluong_don(int soluong_don) {
        GioHang.soluong_don = soluong_don;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.gio_hang, container, false);
        btn_dathang = view.findViewById(R.id.btn_dathang);



        listViewGioHang = view.findViewById(R.id.listViewGioHang);
        adapterGioHang = new AdapterGioHang((Activity) getContext(), R.layout.sanpham_giohang, MainActivity.listSanPham);
        listViewGioHang.setAdapter(adapterGioHang);




        TextView tongTien_GH = view.findViewById(R.id.tongTien_GH);
        TextView tongTienThanhToan_GH = view.findViewById(R.id.tongTienThanhToan_GH);
        TextView thue = view.findViewById(R.id.thue);
        TextView ship = view.findViewById(R.id.ship);

        int totalItems = adapterGioHang.getCount(); // Số lượng mục trong ListView
        double total = 0;



        for (int i = 0; i < MainActivity.listSanPham.size(); i++){

            SanPham_GioHang item = adapterGioHang.getItem(i); // Lấy một mục từ adapter tại vị trí i
            // Làm gì đó với item, ví dụ:
            double gia = item.getGia();
            int quantity = item.getSoluong();
            // Xử lý dữ liệu theo nhu cầu của bạn
            total += gia * quantity;
        }
        // Tạo đối tượng DecimalFormat với mẫu làm tròn số (ví dụ: "#.##" làm tròn đến 2 chữ số thập phân)
        DecimalFormat df = new DecimalFormat("#.##");
        // Sử dụng đối tượng DecimalFormat để làm tròn số
        String totalFormat = df.format(total);

        tongTien_GH.setText(String.valueOf(totalFormat));

        double totalAfterValue = 0;
        if(totalItems == 0){
            totalAfterValue = 0;
            thue.setText("0");
            ship.setText("0");

        }else {
            totalAfterValue = total + 1.2 + 2.3;
        }
        String totalAfterValueFormat = df.format(totalAfterValue);
        tongTienThanhToan_GH.setText(String.valueOf(totalAfterValueFormat));


        ////////////////
        btn_dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Tạo một đối tượng AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                if(MainActivity.listSanPham.isEmpty()){
                    // Thiết lập tiêu đề và nội dung cho cửa sổ thông báo
                    builder.setTitle("Thông báo");
                    builder.setMessage("Không có đơn hàng nào trong giỏ hàng");
                }else{
                    // Thiết lập tiêu đề và nội dung cho cửa sổ thông báo
                    builder.setTitle("Thông báo");
                    builder.setMessage("Đặt hàng thành công");
                    soluong_don++;

                }
                // Thiết lập nút "OK" và xử lý sự kiện khi nút được click
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý khi nút "OK" được click
                        dialog.dismiss(); // Đóng cửa sổ thông báo

                    }
                });

                // Tạo và hiển thị cửa sổ thông báo
                builder.create().show();
//                listViewGioHang.clearAnimation();
                thue.setText("0");
                ship.setText("0");
                tongTien_GH.setText("0");
                tongTienThanhToan_GH.setText("0");
                adapterGioHang.clear();;
                adapterGioHang.notifyDataSetChanged();
            }
        });


        return view;
    }

}
