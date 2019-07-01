package com.example.notetaking.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.notetaking.R;

import java.util.List;

public class NotesAdapter  extends ArrayAdapter<Note> {
    public NotesAdapter(Context context, int resource, List<Note> noteList) {
        super(context, resource, noteList);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Note note = getItem(position);
        if (convertView == null){


        convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_list_item, parent, false);
    }


        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView textNoteText = convertView.findViewById(R.id.tvNoteText);
        tvTitle.setText(note.getTitle());
        textNoteText.setText(note.getNoteText());


        return convertView;
    }
}
