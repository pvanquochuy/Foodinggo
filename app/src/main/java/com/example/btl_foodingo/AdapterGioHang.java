package com.example.btl_foodingo;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterGioHang extends ArrayAdapter<SanPham_GioHang> {

    Context context;
    List<SanPham_GioHang> listGioHang;
    int resource;
    public static DBHelper db;





    public AdapterGioHang(@NonNull Activity context, int resource, @NonNull List<SanPham_GioHang> objects) {
        super(context, resource, objects);

        this.context = context;
        this.listGioHang = objects;
        this.resource = resource;
    }




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sanpham_giohang, parent, false);
        }



        // Lấy ra các đối tượng view từ layout sanpham_giohang.xml
        ImageView anh_GH = view.findViewById(R.id.anh_GH);
        TextView tieude_GH = view.findViewById(R.id.tieude_GH);
        TextView gia_GH = view.findViewById(R.id.gia_GH);
        TextView soluong_GH = view.findViewById(R.id.soluong_GH);


        // Lấy sản phẩm tại vị trí hiện tại
        SanPham_GioHang sanPham_gioHang = getItem(position);


        // Hiển thị dữ liệu sản phẩm lên các view
        anh_GH.setImageResource(sanPham_gioHang.getAnh());
        tieude_GH.setText(sanPham_gioHang.getTieude());
        int soluong = sanPham_gioHang.getSoluong();
        soluong_GH.setText(String.valueOf(soluong));

        double feeValue = sanPham_gioHang.getGia() * soluong;
        // Tạo đối tượng DecimalFormat với mẫu làm tròn số (ví dụ: "#.##" làm tròn đến 2 chữ số thập phân)
        DecimalFormat df = new DecimalFormat("#.##");
        // Sử dụng đối tượng DecimalFormat để làm tròn số
        String feeValueFormat = df.format(feeValue);
        gia_GH.setText(String.valueOf(feeValueFormat));



        ImageButton btn_xoa = view.findViewById(R.id.btn_xoa);
        btn_xoa.setTag(sanPham_gioHang); // gắn đối tượng sản phẩm với nút
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham_GioHang  sanPham_Xoa = (SanPham_GioHang) view.getTag(); // // Lấy đối tượng sản phẩm từ nút
                // Xử lý sự kiện khi nhấn nút xoas
                int i = listGioHang.indexOf(sanPham_Xoa);
                if(i >= 0){
                    // xóa sản phẩm khỏi danh sách
                    listGioHang.remove(i);
                    // Thông báo cho adapter rằng dữ liệu đã thay đổi
                    notifyDataSetChanged();




                }
            }
        });


        return view;
    }

}
