package com.example.btl_foodingo;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterSanPham extends ArrayAdapter<SanPham> {

    // Khai bao thuoc tinh cua AdapterSanPham

    Activity context;
    List<SanPham> listSanPham;
    int resource;



    public AdapterSanPham(@NonNull Activity context, int resource, @NonNull List<SanPham> objects) {
        super(context, resource, objects);

        this.context = context;
        this.listSanPham = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent){
        // Load du lieu dungLayouInflater
//        LayoutInflater inflater = context.getLayoutInflater();
//        View view = inflater.inflate(resource, null);

        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.san_pham, parent, false);
        }

        // Lay ra cac doi tuong view
        ImageView anh = view.findViewById(R.id.anh);
        TextView tieude = view.findViewById(R.id.tieude);
        TextView gia = view.findViewById(R.id.gia);

/////////////////////////////////////////////////// Xử lý tăng giảm sản phẩm ///////////////////////////////////////
        TextView soluong = view.findViewById(R.id.soluong);
        ImageButton btnTang = view.findViewById(R.id.btn_tang);
        ImageButton btnGiam = view.findViewById(R.id.btn_giam);


        // set value hien thi len doi tuong view
        SanPham sanPham = this.getItem(position);



// Lắng nghe sự kiện khi nút Plus (+) được bấm
        btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sanPham.tangSoLuong(); // Gọi phương thức tăng số lượng sản phẩm
                soluong.setText(String.valueOf(sanPham.getSoluong()));
                notifyDataSetChanged(); // Cập nhật lại giao diện
            }
        });

        // Lắng nghe sự kiện khi nút Minus (-) được bấm
        btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sanPham.giamSoLuong(); // Gọi phương thức tăng số lượng sản phẩm
                soluong.setText(String.valueOf(sanPham.getSoluong()));
                notifyDataSetChanged(); // Cập nhật lại giao diện
            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////


        anh.setImageResource(sanPham.getAnh());
        tieude.setText(sanPham.getTieude());

        double feeValue = sanPham.getGia();
        String feeString = String.valueOf(feeValue);

        gia.setText(feeString);


        ImageButton button = view.findViewById(R.id.btn_them);

        button.setTag(sanPham); // gắn đối tượng sản phẩm với nút
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sanPham = (SanPham) view.getTag(); // Lấy đối tượng sản phẩm từ nút
                // Xử lý sự kiện khi nhấn nút được nhấn và dữ liệu của mục (SanPham) tại đây

                SanPham_GioHang  sanPham_gioHang = new SanPham_GioHang(sanPham.getTieude(), sanPham.getAnh(), sanPham.getGia(), sanPham.getSoluong());

                boolean existed = false; // tạo biến để kiểm tra sản phẩm đã có trong giỏ hàng chưa
                for(SanPham_GioHang item : MainActivity.listSanPham){
                    if(item.getTieude().equals(sanPham_gioHang.getTieude())){
                        existed = true;
                        item.setSoluong(item.getSoluong() + sanPham_gioHang.getSoluong());
                        break;
                    }
                }
                if(!existed){
                    // Sản phẩm chưa tồn tại, thêm vào danh sách
                    MainActivity.listSanPham.add(sanPham_gioHang);
                }

                String message = "Bạn đã nhấn vào sản phẩm: Ảnh: " + sanPham_gioHang.getAnh()  +
                        "\nTitle: " + sanPham_gioHang.getTieude() + "\nGiá: "  +sanPham_gioHang.getGia() + "\nSL: " + sanPham_gioHang.getSoluong();
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

            }
        });

        return view;

    }
}
