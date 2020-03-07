package ru.sterlikoff.diplomanotepad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import ru.sterlikoff.diplomanotepad.adapters.NoteAdapter;
import ru.sterlikoff.diplomanotepad.components.App;
import ru.sterlikoff.diplomanotepad.models.Note;

public class MainActivity extends AppCompatActivity {

    public interface OnNoteClickEvents {

        void onClick(Note note);

        boolean onLongClick(Note note);

    }

    ListView listView;
    Button addButton;

    ArrayList<Note> list;
    NoteAdapter adapter;

    protected void initViews() {

        listView = findViewById(R.id.noteList);
        addButton = findViewById(R.id.buttonNewNote);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, NoteFormActivity.class);
                startActivityForResult(intent, App.RESULT_NEW_NOTE);

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        list = new ArrayList<>(App.getNoteRepository().findAll().values());
        Collections.sort(list);

        adapter = new NoteAdapter(list, this, new OnNoteClickEvents() {

            @Override
            public void onClick(Note note) {

                Intent intent = new Intent(MainActivity.this, NoteFormActivity.class);
                intent.putExtra("id", note.getId());
                startActivityForResult(intent, App.RESULT_UPDATE_NOTE);

            }

            @Override
            public boolean onLongClick(final Note note) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage(R.string.deleteNoteConfirmation);

                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        App.getNoteRepository().deleteById(note.getId());

                        int pos = list.indexOf(note);

                        if (pos > -1) {
                            list.remove(pos);
                        }

                        Collections.sort(list);
                        adapter.notifyDataSetChanged();

                    }

                });

                builder.setNegativeButton(R.string.no, null);

                AlertDialog dialog = builder.create();
                dialog.show();

                return false;

            }

        });

        listView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {

            case App.RESULT_NEW_NOTE:
            case App.RESULT_UPDATE_NOTE:

                if (data != null) {

                    int id = data.getIntExtra("id", 0);

                    if (id > 0) {

                        Note model = App.getNoteRepository().findById(id);

                        if (model != null) {

                            int pos = list.indexOf(model);

                            if (pos > -1) {
                                list.remove(pos);
                            }

                            list.add(model);

                            Collections.sort(list);
                            adapter.notifyDataSetChanged();

                        }


                    }

                }

                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.btn_settings:

                Intent intent = new Intent(this, SetPinActivity.class);
                startActivity(intent);

                return true;

            default:
                return true;

        }

    }

}