package ru.sterlikoff.diplomanotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import ru.sterlikoff.diplomanotepad.components.App;
import ru.sterlikoff.diplomanotepad.models.Note;

public class NoteFormActivity extends AppCompatActivity {

    Button submitButton;
    EditText editNoteTitle;
    EditText editNoteText;

    private int currentId;

    protected void initViews() {

        submitButton = findViewById(R.id.btn_note_form_submit);

        editNoteTitle = findViewById(R.id.edit_note_form_title);
        editNoteText = findViewById(R.id.edit_note_form_text);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Note model = (currentId > 0) ? App.getNoteRepository().findById(currentId) : new Note();

                model.title = editNoteTitle.getText().toString();
                model.text = editNoteText.getText().toString();

                if (model.validate()) {

                    if (App.getNoteRepository().save(model)) {

                        Intent intent = new Intent();
                        intent.putExtra("id", model.getId());

                        setResult(App.RESULT_NEW_NOTE, intent);
                        finish();

                    } else {

                        Toast.makeText(NoteFormActivity.this, "При сохранении заметки произошла ошибка", Toast.LENGTH_LONG).show();

                    }

                } else {

                    Toast.makeText(NoteFormActivity.this, "Что то пошло не так", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_form);

        initViews();

        int id = this.getIntent().getIntExtra("id", 0);

        if (id > 0) {

            Note model = App.getNoteRepository().findById(id);

            if (model == null) {

                finish();

            } else {

                currentId = id;

                editNoteTitle.setText(model.title);
                editNoteText.setText(model.text);

            }



        }

    }
}
