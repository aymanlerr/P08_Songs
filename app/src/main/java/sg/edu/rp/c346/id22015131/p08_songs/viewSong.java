package sg.edu.rp.c346.id22015131.p08_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class viewSong extends AppCompatActivity {

    ListView lv;
    Button btnBack;
    ToggleButton btnShowSong;
    Spinner spinner1;
    ArrayList<Song> songList;
    ArrayList<String> yearList;
    ArrayAdapter<String> aaSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_song);

        lv = findViewById(R.id.lv);
        btnBack = findViewById(R.id.btnBack);
        btnShowSong = findViewById(R.id.btnShow);
        spinner1 = findViewById(R.id.spinner1);
        songList = new ArrayList<Song>();
        yearList = new ArrayList<>();

        DBHelper db = new DBHelper(getApplicationContext());
        ArrayList<Song> songs = db.getSongs();

        ArrayAdapter aaSongs = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songs);
        db.close();
        lv.setAdapter(aaSongs);

        for (int a = 0; a < songs.size(); a ++) {
            if (!yearList.contains(Integer.toString(songs.get(a).getYear()))) {
                yearList.add(Integer.toString(songs.get(a).getYear()));
            }
        }

        aaSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, yearList);
        spinner1.setAdapter(aaSpinner);


        btnBack.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(viewSong.this, MainActivity.class);
            startActivity(intent);
        });


        btnShowSong.setOnClickListener(v -> {
            boolean isChecked = btnShowSong.isChecked();
            if (isChecked) {
            ArrayList<Song> filteredSongs = db.getFilteredSongs();
            ArrayAdapter aaFilteredSongs = new ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredSongs);
            db.close();
            lv.setAdapter(aaFilteredSongs);
            aaFilteredSongs.notifyDataSetChanged();
        } else {
                lv.setAdapter(aaSongs);
            }
        });

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Song data = songs.get(position);
            Intent i = new Intent(viewSong.this, EditActivity.class);
            i.putExtra("song", data);
            startActivity(i);
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String yearSelected = parent.getItemAtPosition(position).toString();
                ArrayList<Song> songsByYear = db.getSongsByYear(Integer.parseInt(yearSelected));
                ArrayAdapter aaSongSpinner = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, songsByYear);
                db.close();
                lv.setAdapter(aaSongSpinner);
                aaSongSpinner.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}