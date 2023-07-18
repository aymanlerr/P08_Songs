package sg.edu.rp.c346.id22015131.p08_songs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;
    TextView tvSong, tvYear, tvStar, tvArtist;

    public CustomAdapter(@NonNull Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);
        tvSong = rowView.findViewById(R.id.song);
        tvYear = rowView.findViewById(R.id.year);
        tvStar = rowView.findViewById(R.id.star);
        tvArtist = rowView.findViewById(R.id.artist);
        Song currentSong = songList.get(position);
        tvSong.setText(currentSong.getTitle());
        tvYear.setText(Integer.toString(currentSong.getYear()));
        tvStar.setText("  "+ " *".repeat(currentSong.getStars()));
        tvArtist.setText(currentSong.getSingers());
        return rowView;
    }
}
