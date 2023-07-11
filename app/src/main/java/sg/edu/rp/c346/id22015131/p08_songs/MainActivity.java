package sg.edu.rp.c346.id22015131.p08_songs;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    RadioGroup rgStar;
    Button btnInsert, btnShowList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etNewTitle);
        etSinger = findViewById(R.id.etNewSinger);
        etYear = findViewById(R.id.etNewYear);
        rgStar = findViewById(R.id.rgStar);
        btnInsert = findViewById(R.id.btnUpdate);
        btnShowList = findViewById(R.id.btnShowList);
        lv = findViewById(R.id.lv);

        btnInsert.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String singer = etSinger.getText().toString();
            int year = Integer.parseInt(etYear.getText().toString());
            int star = 0;

            int selectedRgStar = rgStar.getCheckedRadioButtonId();
            if (selectedRgStar == R.id.newStar1) {
                star = 1;
            } else if (selectedRgStar == R.id.newStar2) {
                star = 2;
            } else if (selectedRgStar == R.id.newStar3) {
                star = 3;
            } else if (selectedRgStar == R.id.newStar4) {
                star = 4;
            } else if (selectedRgStar == R.id.newStar5) {
                star = 5;
            }

            DBHelper db = new DBHelper(MainActivity.this);
            db.insertSong(title, singer, year,star);
            etTitle.getText().clear();
            etSinger.getText().clear();
            etYear.getText().clear();
            rgStar.clearCheck();
        });

        btnShowList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, viewSong.class);
            startActivity(intent);
        });

    }
}