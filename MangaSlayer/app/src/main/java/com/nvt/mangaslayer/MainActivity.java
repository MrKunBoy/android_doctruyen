package com.nvt.mangaslayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView img_menu_add, img_back;
    ListView lvTruyen;
    public static List<Truyen> listtruyen;
    public static DataTruyen dataTruyen;
    public static TruyenAdapter truyenAdapter;
    int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //kich hoat database
        dataTruyen = new DataTruyen(MainActivity.this);

        listtruyen = dataTruyen.getAllTruyen();
        anhXa();
        setClick();
        truyenAdapter = new TruyenAdapter(this, R.layout.item_truyen, listtruyen);
        lvTruyen.setAdapter(truyenAdapter);
        registerForContextMenu(lvTruyen);

        clickItem();
    }

    private void clickItem() {
        lvTruyen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Truyen truyen = (Truyen) truyenAdapter.getItem(position);
                Toast.makeText(MainActivity.this, truyen.getTen(), Toast.LENGTH_SHORT).show();

                Intent it = new Intent(MainActivity.this, ChiTietTruyen.class);
                Bundle bd = new Bundle();
                int idtc = truyen.getID();
                bd.putInt("idtc", idtc);
                it.putExtras(bd);

                startActivity(it);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        switch (item.getItemId()) {
            case R.id.menu_update:
                this.currentIndex = index;

                Truyen truyen = (Truyen) truyenAdapter.getItem(index);
                Toast.makeText(MainActivity.this, truyen.getTen(), Toast.LENGTH_SHORT).show();

                Intent it = new Intent(MainActivity.this, AddTruyenActivity.class);
                Bundle bd = new Bundle();
                int idtc = truyen.getID();

                //đưa dữ liệu riêng lẻ vào Bundle
                bd.putInt("currentIndex", index);
                bd.putInt("idtc", idtc);

                it.putExtras(bd);

                startActivity(it);
                return true;
            case R.id.menu_delete:
                int iD = listtruyen.get(index).getID();
                long kq = dataTruyen.deleteTruyen(iD);
                if (kq > 0) {
                    Toast.makeText(MainActivity.this, "Xóa thành công!!!", Toast.LENGTH_SHORT).show();
                    listtruyen.remove(index);
                    truyenAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Lõi xóa!!!", Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        dataTruyen.close();
        super.onDestroy();
    }

    private void setClick() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        img_menu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MainActivity.this, AddTruyenActivity.class);
                Bundle bd = new Bundle();

                //đưa dữ liệu riêng lẻ vào Bundle
                bd.putInt("currentIndex", -1);
                bd.putInt("idtc", 0);
                //Đưa Bundle vào Intent
                it.putExtras(bd);

                startActivity(it);
            }
        });
    }

    private void anhXa() {
        lvTruyen = (ListView) findViewById(R.id.listview_truyen);
        img_menu_add = (ImageView) findViewById(R.id.menu_add);
        img_back = (ImageView) findViewById(R.id.imgback);
    }
}