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

    protected void initViews() {

        submitButton = findViewById(R.id.btn_note_form_submit);

        editNoteTitle = findViewById(R.id.edit_note_form_title);
        editNoteText = findViewById(R.id.edit_note_form_text);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Note model = new Note(editNoteTitle.getText().toString(), editNoteText.getText().toString(), new Date());

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

    }
}
