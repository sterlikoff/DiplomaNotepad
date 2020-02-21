package ru.sterlikoff.diplomanotepad.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import ru.sterlikoff.diplomanotepad.R;
import ru.sterlikoff.diplomanotepad.models.Note;

public class NoteAdapter extends BaseAdapter {

    private List<Note> list;
    private LayoutInflater inflater;

    public NoteAdapter(List<Note> list, Context context) {

        this.list = list;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Note getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentView =
                convertView == null
                        ? this.inflater.inflate(R.layout.note_list_item, parent, false)
                        : convertView;

        Note note = getItem(position);

        TextView titleView = currentView.findViewById(R.id.note_list_item_title);
        TextView textView = currentView.findViewById(R.id.note_list_item_text);
        TextView dateView = currentView.findViewById(R.id.notice_list_item_date);

        titleView.setText(note.title);
        textView.setText(note.text);
        dateView.setText(note.dateDeadLine.toString());

        return currentView;
    }

}
