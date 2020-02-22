package ru.sterlikoff.diplomanotepad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import ru.sterlikoff.diplomanotepad.classes.NoteAdapter;
import ru.sterlikoff.diplomanotepad.components.App;
import ru.sterlikoff.diplomanotepad.models.Note;

public class MainActivity extends AppCompatActivity {

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
        adapter = new NoteAdapter(list, this);

        listView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {

            case App.RESULT_NEW_NOTE:

                if (data != null) {

                    int id = data.getIntExtra("id", 0);

                    if (id > 0) {

                        Note model = App.getNoteRepository().findById(id);

                        if (model != null) {

                            list.add(model);
                            adapter.notifyDataSetChanged();

                        }


                    }

                }

                break;

        }

    }

}