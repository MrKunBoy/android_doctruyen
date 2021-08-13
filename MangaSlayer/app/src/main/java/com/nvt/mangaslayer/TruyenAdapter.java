package com.nvt.mangaslayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TruyenAdapter extends BaseAdapter {
    private Context contexttruyen;
    private int layout_item;
    private List<Truyen> truyenList;

    public TruyenAdapter(Context contexttruyen, int layout_item, List<Truyen> truyenList) {
        this.contexttruyen = contexttruyen;
        this.layout_item = layout_item;
        this.truyenList = truyenList;
    }

    @Override
    public int getCount() {
        return truyenList.size();
    }

    @Override
    public Object getItem(int position) {
        return truyenList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) contexttruyen.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout_item, null);

        ImageView imagesp = (ImageView) convertView.findViewById(R.id.list_image_sp);
        TextView tensp = (TextView) convertView.findViewById(R.id.list_ten_sp);
        TextView mota = (TextView) convertView.findViewById(R.id.list_mota_sp);
        TextView yeuthich = (TextView) convertView.findViewById(R.id.yeuthich2_chitiet) ;

        Truyen truyen = truyenList.get(position);

        tensp.setText(truyen.getTen());
        mota.setText(truyen.getMoTa());
        yeuthich.setText(String.valueOf(truyen.getYeuThich()));
        byte[] hinhAnh = truyen.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        imagesp.setImageBitmap(bitmap);

        return convertView;
    }
}
