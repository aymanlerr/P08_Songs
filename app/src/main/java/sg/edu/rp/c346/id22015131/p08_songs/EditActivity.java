package sg.edu.rp.c346.id22015131.p08_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    RadioButton star1, star2, star3, star4, star5;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rgStars;

    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etTitle = findViewById(R.id.etNewTitle);
        etSinger = findViewById(R.id.etNewSinger);
        etYear = findViewById(R.id.etNewYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);
        btnDelete = findViewById(R.id.btnDelete);
        star1 = findViewById(R.id.newStar1);
        star2 = findViewById(R.id.newStar2);
        star3 = findViewById(R.id.newStar3);
        star4 = findViewById(R.id.newStar4);
        star5 = findViewById(R.id.newStar5);
        rgStars = findViewById(R.id.rgNewStar);


        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("song");

        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(Integer.toString(data.getYear()));

        if (data.getStars()==1) {
            star1.setChecked(true);
        } else if (data.getStars() == 2) {
            star2.setChecked(true);
        } else if (data.getStars() == 3) {
            star3.setChecked(true);
        } else if (data.getStars() == 4) {
            star4.setChecked(true);
        } else if (data.getStars() == 5) {
            star5.setChecked(true);
        }

        btnCancel.setOnClickListener(v -> {
            finish();
        });

        btnDelete.setOnClickListener(v -> {
            DBHelper db = new DBHelper(EditActivity.this);
            db.deleteSong(data.getId());
            finish();
            Intent intent = new Intent(EditActivity.this, viewSong.class);
            startActivity(intent);
        });

        btnUpdate.setOnClickListener(v -> {
            DBHelper db = new DBHelper(EditActivity.this);

            int selectedRgStar = rgStars.getCheckedRadioButtonId();
            int stars = 0;
            if (selectedRgStar == R.id.newStar1) {
                stars = 1;
            } else if (selectedRgStar == R.id.newStar2) {
                stars = 2;
            } else if (selectedRgStar == R.id.newStar3) {
                stars = 3;
            } else if (selectedRgStar == R.id.newStar4) {
                stars = 4;
            } else if (selectedRgStar == R.id.newStar5) {
                stars = 5;
            }

            String title = etTitle.getText().toString();
            String singer = etSinger.getText().toString();
            int year = Integer.parseInt(etYear.getText().toString());

            data.setSongContent(title, singer, year, stars);
            db.updateSong(data);
            db.close();
            finish();
            Intent intent = new Intent(EditActivity.this, viewSong.class);
            startActivity(intent);
        });

    }
}