package com.example.th2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class them_cong_viec extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_cv);

        EditText etTen = findViewById(R.id.cv_moi);
        EditText etNgay = findViewById(R.id.tg);
        Button btnLuu = findViewById(R.id.bt_luu);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = etTen.getText().toString().trim();
                String ngay = etNgay.getText().toString().trim();

                if (ten.isEmpty() || ngay.isEmpty()) {
                    Toast.makeText(them_cong_viec.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intentResult = new Intent();
                intentResult.putExtra("TASK_TITLE", ten);
                intentResult.putExtra("TASK_DATE", ngay);
                setResult(RESULT_OK, intentResult);

                finish(); // Trở về màn hình trước
            }
        });
    }
}