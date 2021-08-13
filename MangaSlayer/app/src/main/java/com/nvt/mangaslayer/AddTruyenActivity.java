package com.nvt.mangaslayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.nvt.mangaslayer.DataTruyen.COLUMN_IMAGE;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_LUOTXEM;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_MOTA;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_NOIDUNG;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_TACGIA;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_TEN;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_THELOAI;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_TINHTRANG;
import static com.nvt.mangaslayer.DataTruyen.COLUMN_YEUTHICH;

public class AddTruyenActivity extends AppCompatActivity {

    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;
    ImageView addimage;
    EditText addten, addmota, addyeuthich,addnoidung, addtacgia,addtheloai,addtinhtrang,addluotxem;
    ImageButton ibtncamera, ibtnfolder;
    Button btnhuy, btndongy;

    int currentIndex = -1;
    int _id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_truyen);

        anhXa();

        Intent it=getIntent();
        //có intent rồi thì lấy Bundle dựa vào MyPackage
        Bundle bd= it.getExtras();
        //Có Bundle rồi thì lấy các thông số dựa vào soa, sob
        currentIndex = bd.getInt("currentIndex");
        int id = bd.getInt("idtc");

        String ten = "", mota = "", nd = "",tg = "",tl = "",tt = "";
        int yt = 0,lx = 0;
        byte[] hinh = null;

        if(id > 0 ) {
            Cursor rs = MainActivity.dataTruyen.getData(id);
            rs.moveToFirst();
            ten = rs.getString(rs.getColumnIndex(COLUMN_TEN));
            mota = rs.getString(rs.getColumnIndex(COLUMN_MOTA));
            yt = rs.getInt(rs.getColumnIndex(COLUMN_YEUTHICH));
            hinh = rs.getBlob(rs.getColumnIndex(COLUMN_IMAGE));
            nd = rs.getString(rs.getColumnIndex(COLUMN_NOIDUNG));
            tg = rs.getString(rs.getColumnIndex(COLUMN_TACGIA));
            tl = rs.getString(rs.getColumnIndex(COLUMN_THELOAI));
            tt = rs.getString(rs.getColumnIndex(COLUMN_TINHTRANG));
            lx = rs.getInt(rs.getColumnIndex(COLUMN_LUOTXEM));
        }
        ibtncamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });

        ibtnfolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });

        if(currentIndex >=0 && id > 0){
            _id = id;
            addten.setText(ten);
            addmota.setText(mota);
            addyeuthich.setText(String.valueOf(yt));
            addnoidung.setText("   "+nd);
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinh,0,hinh.length);
            addimage.setImageBitmap(bitmap);
            addtacgia.setText(tg);
            addtheloai.setText(tl);
            addtinhtrang.setText(tt);
            addluotxem.setText(String.valueOf(lx));
        }

        btndongy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dten = addten.getText().toString().trim();
                String dmota = addmota.getText().toString();
                byte[] dimage = ImageView_Byte(addimage);
                String dyeuthich = addyeuthich.getText().toString().trim();
                String dnoidung = addnoidung.getText().toString();
                String dtacgia = addtacgia.getText().toString().trim();
                String dtheloai = addtheloai.getText().toString().trim();
                String dtinhtrang = addtinhtrang.getText().toString().trim();
                String dluotxem = addluotxem.getText().toString().trim();
                if(currentIndex >=0){
                    Truyen truyen = new Truyen();
                    truyen.setID(_id);
                    truyen.setTen(dten);
                    truyen.setMoTa(dmota);
                    truyen.setImage(dimage);
                    truyen.setYeuThich(Integer.valueOf(dyeuthich));
                    truyen.setNoiDung(dnoidung);
                    truyen.setTacGia(dtacgia);
                    truyen.setTheLoai(dtheloai);
                    truyen.setTinhTrang(dtinhtrang);
                    truyen.setLuotXem(Integer.valueOf(dluotxem));
                    long result1 = MainActivity.dataTruyen.updateTruyen(truyen);
                    if(result1 > 0){
                        Toast.makeText(AddTruyenActivity.this,"Update thành công !!!",Toast.LENGTH_SHORT).show();
                        MainActivity.listtruyen.get(currentIndex).setTen(dten);
                        MainActivity.listtruyen.get(currentIndex).setMoTa(dmota);
                        MainActivity.listtruyen.get(currentIndex).setImage(dimage);
                        MainActivity.listtruyen.get(currentIndex).setYeuThich(Integer.valueOf(dyeuthich));
                        MainActivity.listtruyen.get(currentIndex).setNoiDung(dnoidung);
                        MainActivity.listtruyen.get(currentIndex).setTacGia(dtacgia);
                        MainActivity.listtruyen.get(currentIndex).setTheLoai(dtheloai);
                        MainActivity.listtruyen.get(currentIndex).setTinhTrang(dtinhtrang);
                        MainActivity.listtruyen.get(currentIndex).setLuotXem(Integer.valueOf(dluotxem));

                        currentIndex = -1;
                    }
                    else {
                        Toast.makeText(AddTruyenActivity.this,"Lỗi update !!!",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Truyen truyen = new Truyen();
                    truyen.setTen(dten);
                    truyen.setMoTa(dmota);
                    truyen.setImage(dimage);
                    truyen.setYeuThich(Integer.valueOf(dyeuthich));
                    truyen.setNoiDung(dnoidung);
                    truyen.setTacGia(dtacgia);
                    truyen.setTheLoai(dtheloai);
                    truyen.setTinhTrang(dtinhtrang);
                    truyen.setLuotXem(Integer.valueOf(dluotxem));
                    long result2 = MainActivity.dataTruyen.insertTruyen(truyen);
                    if(result2 > 0){
                        Toast.makeText(AddTruyenActivity.this,"Insert thành công !!!",Toast.LENGTH_SHORT).show();
                        MainActivity.listtruyen.add(truyen);

                    }
                    else {
                        Toast.makeText(AddTruyenActivity.this,"Lỗi insert !!!",Toast.LENGTH_SHORT).show();
                    }
                }
                MainActivity.truyenAdapter.notifyDataSetChanged();

                finish();
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            addimage.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                addimage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    private void anhXa() {
        addten     = (EditText)findViewById(R.id.add_edtten);
        addmota    = (EditText)findViewById(R.id.add_edtmota);

        addimage  = (ImageView) findViewById(R.id.add_edtimage);

        addyeuthich  = (EditText)findViewById(R.id.add_edtyeuthich);
        addnoidung = (EditText)findViewById(R.id.add_edtnoidung);
        addtacgia  = (EditText)findViewById(R.id.add_edttacgia);
        addtheloai = (EditText)findViewById(R.id.add_edttheloai);
        addtinhtrang = (EditText)findViewById(R.id.add_edttinhtrang);
        addluotxem = (EditText)findViewById(R.id.add_edtluotxem);

        ibtncamera = (ImageButton) findViewById(R.id.btnCamera) ;
        ibtnfolder = (ImageButton) findViewById(R.id.btnFolder) ;

        btnhuy   = (Button)findViewById(R.id.btnhuy);
        btndongy = (Button)findViewById(R.id.btndongy);
    }

    public byte[] ImageView_Byte(ImageView img){
        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

}