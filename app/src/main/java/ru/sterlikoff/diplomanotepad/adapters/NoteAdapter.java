package ru.sterlikoff.diplomanotepad.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ru.sterlikoff.diplomanotepad.MainActivity;
import ru.sterlikoff.diplomanotepad.R;
import ru.sterlikoff.diplomanotepad.models.Note;

public class NoteAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Note> list;
    private MainActivity.OnNoteClickEvents events;
    private MainActivity context;

    public NoteAdapter(List<Note> list, MainActivity context, MainActivity.OnNoteClickEvents events) {

        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.events = events;
        this.context = context;

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

        final Note note = getItem(position);

        if (note != null) {

            TextView titleView = currentView.findViewById(R.id.note_list_item_title);
            TextView textView = currentView.findViewById(R.id.note_list_item_text);
            TextView dateView = currentView.findViewById(R.id.notice_list_item_date);

            if (!note.title.isEmpty()) {
                titleView.setText(note.title);
            } else {
                titleView.setVisibility(View.GONE);
            }

            textView.setText(note.text);

            if (note.dateDeadLine != null) {

                DateFormat dateFormat = new SimpleDateFormat(context.getResources().getString(R.string.dateFormat), Locale.getDefault());
                dateView.setText(dateFormat.format(note.dateDeadLine));


            } else {
                dateView.setVisibility(View.GONE);
            }

            currentView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    events.onClick(note);
                }

            });

            currentView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    return events.onLongClick(note);
                }

            });

        }

        return currentView;

    }

}