package com.example.th2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class dang_nhap_activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_nhap);
        EditText username = findViewById(R.id.tdn);
        EditText password = findViewById(R.id.mk);
        Button bt_login = findViewById(R.id.bt_dang_nhap);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = username.getText().toString().trim();
                String p = password.getText().toString().trim();
                if (u.isEmpty() || p.isEmpty()) {
                    Toast.makeText(dang_nhap_activity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (u.equals("admin") && p.equals("123456")) {
                    Toast.makeText(dang_nhap_activity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(dang_nhap_activity.this, ds_cong_viec.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(dang_nhap_activity.this, "Tài khoản hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}