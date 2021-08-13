package com.nvt.mangaslayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    Button btnsignup;
    EditText editUsername, editPassword, editEmail;
    DataTaiKhoan dataTaiKhoan;
    String ten, mk, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dataTaiKhoan = new DataTaiKhoan(SignUpActivity.this);

        anhXa();
        buttonClick();
    }

    private void buttonClick() {
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ten = editUsername.getText().toString().trim();
                mk = editPassword.getText().toString().trim();
                email = editEmail.getText().toString().trim();

                if (checkDangKy(ten)) {
                    Toast.makeText(SignUpActivity.this, "Tên đăng nhập đã tồn tại!!!", Toast.LENGTH_SHORT).show();
                } else {

                    TaiKhoan taiKhoan = new TaiKhoan();
                    taiKhoan.setEmail(email);
                    taiKhoan.setUsername(ten);
                    taiKhoan.setPassword(mk);
                    long result = dataTaiKhoan.insertTaiKhoan(taiKhoan);
                    if (result > 0) {
                        Toast.makeText(SignUpActivity.this, "Bạn đã đăng ký thành công!!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SignUpActivity.this, LoginActivity.class);

                        startActivity(i);
                    } else {
                        Toast.makeText(SignUpActivity.this, "Lỗi đăng ký!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void anhXa() {
        //signup
        btnsignup = (Button) findViewById(R.id.btnSingUp);
        editUsername = (EditText) findViewById(R.id.editname);
        editPassword = (EditText) findViewById(R.id.editpass);
        editEmail = (EditText) findViewById(R.id.editemail);
    }

    public boolean checkDangKy(String tendn) {
        List<TaiKhoan> taiKhoanList = dataTaiKhoan.getAllTaiKhoan();
        String tendangnhap = "";
        for (int i = 0; i < taiKhoanList.size(); i++) {
            tendangnhap = taiKhoanList.get(i).getUsername();
            if (tendangnhap.equals(tendn)) {
                return true;
            }
        }
        return false;
    }
}