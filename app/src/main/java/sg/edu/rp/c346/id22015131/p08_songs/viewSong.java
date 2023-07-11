package sg.edu.rp.c346.id22015131.p08_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class viewSong extends AppCompatActivity {

    ListView lv;
    Button btnBack, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_song);

        lv = findViewById(R.id.lv);
        btnBack = findViewById(R.id.btnBack);
        btnShow = findViewById(R.id.btnShow);

        DBHelper db = new DBHelper(getApplicationContext());
        ArrayList<Song> songs = db.getSongs();
        ArrayAdapter aaSongs = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songs);
        db.close();
        lv.setAdapter(aaSongs);


        btnBack.setOnClickListener(v -> {
            finish();
            Intent intent = new Intent(viewSong.this, MainActivity.class);
            startActivity(intent);
        });

        btnShow.setOnClickListener(v -> {
            ArrayList<Song> filteredSongs = db.getFilteredSongs();
            ArrayAdapter aaFilteredSongs = new ArrayAdapter(this, android.R.layout.simple_list_item_1, filteredSongs);
            db.close();
            lv.setAdapter(aaFilteredSongs);
            aaFilteredSongs.notifyDataSetChanged();
        });

        lv.setOnItemClickListener((parent, view, position, id) -> {
            Song data = songs.get(position);
            Intent i = new Intent(viewSong.this, EditActivity.class);
            i.putExtra("song", data);
            startActivity(i);
        });
    }
}