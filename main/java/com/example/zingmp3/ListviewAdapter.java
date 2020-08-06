package com.example.zingmp3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListviewAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<Song> songs;
    OnItemlickListener onItemlickListener;

    public void setOnItemlickListener(OnItemlickListener onItemlickListener) {
        this.onItemlickListener = onItemlickListener;
    }

    public ListviewAdapter(Context context, int layout, ArrayList<Song> songs) {
        this.context = context;
        this.layout = layout;
        this.songs = songs;
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout, null);
        TextView txtSong = convertView.findViewById(R.id.song_title);
        txtSong.setText(songs.get(position).getName());
        txtSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemlickListener.OnClick(songs.get(position), position);
            }
        });
        return convertView;
    }
}
