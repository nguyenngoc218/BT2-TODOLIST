package com.example.th2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

// Class Task viết chung trong file này
class Task {
    private String title;
    private String date;

    public Task(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}

public class ds_cong_viec extends Activity {

    public static final int REQUEST_CODE_ADD = 100;

    private ArrayAdapter<String> adapHomNay;
    private ArrayAdapter<String> adapSapToi;
    private ArrayList<String> arrHomNay;
    private ArrayList<String> arrSapToi;
    private String today;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dscvc);

        ListView lvHomNay = findViewById(R.id.lv_cv);
        ListView lvSapToi = findViewById(R.id.lv_cv_sap_toi);
        ImageButton bt_them_cv = findViewById(R.id.bt_them);

        lvHomNay.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvSapToi.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        ArrayList<Task> allTasks = new ArrayList<>();
        allTasks.add(new Task("Đi chợ", today));
        allTasks.add(new Task("Nấu cơm", today));
        allTasks.add(new Task("Đọc sách", "2026-10-03"));
        allTasks.add(new Task("Làm bài tập Java", "2026-10-05"));

        arrHomNay = new ArrayList<>();
        arrSapToi = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.getDate().equals(today)) {
                arrHomNay.add(task.getTitle());
            } else if (task.getDate().compareTo(today) > 0) {
                arrSapToi.add(task.getTitle() + " (" + task.getDate() + ")");
            }
        }

        adapHomNay = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, arrHomNay);
        lvHomNay.setAdapter(adapHomNay);

        adapSapToi = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice, arrSapToi);
        lvSapToi.setAdapter(adapSapToi);

        bt_them_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(ds_cong_viec.this, them_cong_viec.class);
                startActivityForResult(j, REQUEST_CODE_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD && resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("TASK_TITLE");
            String date = data.getStringExtra("TASK_DATE");

            if (title != null && date != null) {
                if (date.equals(today)) {
                    arrHomNay.add(title);
                    adapHomNay.notifyDataSetChanged();
                } else if (date.compareTo(today) > 0) {
                    arrSapToi.add(title + " (" + date + ")");
                    adapSapToi.notifyDataSetChanged();
                }
            }
        }
    }
}