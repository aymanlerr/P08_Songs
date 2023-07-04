package sg.edu.rp.c346.id22015131.p08_songs;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    RadioGroup rgStar;
    Button btnInsert, btnShowList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStar = findViewById(R.id.rgStar);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        lv = findViewById(R.id.lv);

        btnInsert.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String singer = etSinger.getText().toString();
            int year = Integer.parseInt(etYear.getText().toString());
            int star = 0;

            int selectedRgStar = rgStar.getCheckedRadioButtonId();
            if (selectedRgStar == R.id.star1) {
                star = 1;
            } else if (selectedRgStar == R.id.star2) {
                star = 2;
            } else if (selectedRgStar == R.id.star3) {
                star = 3;
            } else if (selectedRgStar == R.id.star4) {
                star = 4;
            } else if (selectedRgStar == R.id.star5) {
                star = 5;
            }

            DBHelper db = new DBHelper(MainActivity.this);
            db.insertSong(title, singer, year,star);
        });

        btnShowList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, viewSong.class);
            startActivity(intent);
        });

    }
}