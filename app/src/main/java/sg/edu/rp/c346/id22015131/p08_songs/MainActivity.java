package sg.edu.rp.c346.id22015131.p08_songs;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    RadioGroup rgStar;
    Button btnInsert, btnShowList;

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

        btnInsert.setOnClickListener(v -> {
            if (validate()) {
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
                Toast.makeText(getApplicationContext(), "Song inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Enter empty field(s)", Toast.LENGTH_SHORT).show();
            }
        });

        btnShowList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, viewSong.class);
            startActivity(intent);
        });

    }

    private boolean validate() {
        String title = etTitle.getText().toString();
        String singer = etSinger.getText().toString();
        String year = etYear.getText().toString();


        if (title.isEmpty() && singer.isEmpty() && year.isEmpty()) {
            etTitle.setError("Enter title");
            etSinger.setError("Enter Singer name");
            etYear.setError("Enter year");
            return false;
        } else if (singer.isEmpty() && title.isEmpty()) {
            etTitle.setError("Enter title");
            etSinger.setError("Enter Singer name");
            return false;
        } else if (year.isEmpty() && title.isEmpty()) {
            etYear.setError("Enter year");
            etTitle.setError("Enter title");
            return false;
        } else if (singer.isEmpty() && year.isEmpty()) {
            etSinger.setError("Enter Singer name");
            etYear.setError("Enter year");
            return false;
        } else if (title.isEmpty()) {
            etTitle.setError("Enter title");
            return false;
        } else if (singer.isEmpty()) {
            etSinger.setError("Enter Singer name");
            return false;
        } else if (year.isEmpty()) {
            etYear.setError("Enter year");
            return false;
        } else {
            return true;
        }
    }
}