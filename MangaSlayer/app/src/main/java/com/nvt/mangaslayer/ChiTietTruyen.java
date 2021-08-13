package com.nvt.mangaslayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.nvt.mangaslayer.DataTruyen.COLUMN_IMAGE;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_LUOTXEM;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_MOTA;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_NOIDUNG;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_TACGIA;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_TEN;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_THELOAI;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_TINHTRANG;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_YEUTHICH;

public class ChiTietTruyen extends AppCompatActivity {
    ImageView imgleftct, imgrightct;
    TextView txtbackct;

    TextView ctten,ctyeuthich,ctnoidung,cttacgia,cttheloai,cttinhtrang,ctluotxem;
    ImageView ctimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiettruyen);

        AnhXa();
        Control();
        //lấy intent gọi Activity này
        Intent it=getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle bd= it.getExtras();
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        int id = bd.getInt("idtc");
        if(id > 0 ) {
            Cursor rs = MainActivity.dataTruyen.getData(id);
            rs.moveToFirst();
            String ten = rs.getString(rs.getColumnIndex(COLUMN_TEN));
            String mota = rs.getString(rs.getColumnIndex(COLUMN_MOTA));
            int yt = rs.getInt(rs.getColumnIndex(COLUMN_YEUTHICH));
            byte[] hinh = rs.getBlob(rs.getColumnIndex(COLUMN_IMAGE));
            String nd = rs.getString(rs.getColumnIndex(COLUMN_NOIDUNG));
            String tg = rs.getString(rs.getColumnIndex(COLUMN_TACGIA));
            String tl = rs.getString(rs.getColumnIndex(COLUMN_THELOAI));
            String tt = rs.getString(rs.getColumnIndex(COLUMN_TINHTRANG));
            int lx = rs.getInt(rs.getColumnIndex(COLUMN_LUOTXEM));

            ctten.setText(ten);
            ctyeuthich.setText(String.valueOf(yt));
            ctnoidung.setText("   " + nd);
//            ctimage.setImageBitmap(hinh);
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinh,0,hinh.length);
            ctimage.setImageBitmap(bitmap);
            cttacgia.setText(tg);
            cttheloai.setText(tl);
            cttinhtrang.setText(tt);
            ctluotxem.setText(String.valueOf(lx));
        }

        registerForContextMenu(imgrightct);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_chitiet,menu );
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.chitiet_exit:
                finishAffinity();
                System.exit(0);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void Control() {
        imgleftct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtbackct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        imgleftct = (ImageView) findViewById(R.id.imgback);
        txtbackct = (TextView) findViewById(R.id.textback);
        imgrightct = (ImageView) findViewById(R.id.menutoolbar);

        ctten = (TextView) findViewById(R.id.ten_ct);
        ctnoidung = (TextView) findViewById(R.id.noidung_ct);
        ctyeuthich = (TextView) findViewById(R.id.textyeuthich_ct);
        ctimage = (ImageView) findViewById(R.id.image_ct);
        cttacgia = (TextView) findViewById(R.id.tacgia_ct);
        cttheloai = (TextView) findViewById(R.id.theloai_ct);
        cttinhtrang = (TextView) findViewById(R.id.tinhtrang_ct);
        ctluotxem = (TextView) findViewById(R.id.luotxem_ct);
    }
}