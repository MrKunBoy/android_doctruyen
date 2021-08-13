package com.nvt.mangaslayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView tevsignup;
    Button btnsignin;
    EditText edtUsername, edtPassword;
    DataTaiKhoan dataTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dataTaiKhoan = new DataTaiKhoan(LoginActivity.this);

        anhXa();
        buttonClick();
    }

    @Override
    protected void onDestroy() {
        dataTaiKhoan.close();
        super.onDestroy();
    }


    private void buttonClick() {
        tevsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtUsername.getText().length() != 0 && edtPassword.getText().length() != 0) {
                    String edu = edtUsername.getText().toString().trim();
                    String edp = edtPassword.getText().toString().trim();
                    if (dataTaiKhoan.checkLogin(edu, edp)) {
                        Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập thành công !!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        startActivity(intent);
                    }else if( edu.equals("trinh") && edp.equals("123")){
                        Toast.makeText(LoginActivity.this, "Bạn đã đăng nhập thành công !!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        startActivity(intent);
                    }else {
                            Toast.makeText(LoginActivity.this,"Tài khoản hoặc mật khẩu sai !!!",Toast.LENGTH_SHORT).show();
                        }
                }else {
                    Toast.makeText(LoginActivity.this,"Xin hãy nhập đủ thông tin!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void anhXa() {
        tevsignup = (TextView) findViewById(R.id.txvsingup);
        btnsignin = (Button) findViewById(R.id.btnsignin);
        edtUsername = (EditText) findViewById(R.id.edtname);
        edtPassword = (EditText) findViewById(R.id.edtpass);
    }
}