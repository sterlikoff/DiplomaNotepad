package ru.sterlikoff.diplomanotepad.classes;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ru.sterlikoff.diplomanotepad.MainActivity;
import ru.sterlikoff.diplomanotepad.NoteFormActivity;
import ru.sterlikoff.diplomanotepad.R;
import ru.sterlikoff.diplomanotepad.components.App;
import ru.sterlikoff.diplomanotepad.models.Note;

public class NoteAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Note> list;
    private MainActivity context;

    public NoteAdapter(List<Note> list, MainActivity context) {

        this.list = list;
        this.inflater = LayoutInflater.from(context);
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
            TextView idView = currentView.findViewById(R.id.note_list_item_id);

            titleView.setText(note.title);
            textView.setText(note.text);
            dateView.setText(note.dateDeadLine.toString());
            idView.setText("# " + Integer.toString(note.getId()));

            currentView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, NoteFormActivity.class);
                    intent.putExtra("id", note.getId());

                    context.startActivityForResult(intent, App.RESULT_UPDATE_NOTE);

                }

            });

        }

        return currentView;
    }

}
