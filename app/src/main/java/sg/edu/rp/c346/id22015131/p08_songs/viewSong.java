package sg.edu.rp.c346.id22015131.p08_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class viewSong extends AppCompatActivity {

    ListView lv;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_song);

        lv = findViewById(R.id.lv);
        btnBack = findViewById(R.id.btnBack);

        DBHelper db = new DBHelper(getApplicationContext());
        ArrayList<Song> songs = db.getSongs();
        ArrayAdapter aaSongs = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songs);
        db.close();
        lv.setAdapter(aaSongs);

        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}