package ru.sterlikoff.diplomanotepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import ru.sterlikoff.diplomanotepad.components.App;
import ru.sterlikoff.diplomanotepad.models.Note;

public class NoteFormActivity extends AppCompatActivity {

    ImageButton chooseDateButton;

    EditText editNoteTitle;
    EditText editNoteText;
    EditText editNoteDeadLineDate;

    DateFormat dateFormat;

    private int currentId;

    protected void initViews() {

        chooseDateButton = findViewById(R.id.btn_choose_date);

        editNoteTitle = findViewById(R.id.edit_note_form_title);
        editNoteText = findViewById(R.id.edit_note_form_text);
        editNoteDeadLineDate = findViewById(R.id.edit_note_form_deadline_date);

        editNoteDeadLineDate.setKeyListener(null);

        dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

        chooseDateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar todayCalendar = Calendar.getInstance();

                DatePickerDialog datePickerDialog = new DatePickerDialog(NoteFormActivity.this,

                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                Calendar calendar = new GregorianCalendar(year, month, dayOfMonth);
                                editNoteDeadLineDate.setText(dateFormat.format(calendar.getTime()));

                            }

                        },

                        todayCalendar.get(Calendar.YEAR),
                        todayCalendar.get(Calendar.MONTH),
                        todayCalendar.get(Calendar.DAY_OF_MONTH)

                );

                datePickerDialog.show();

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
                editNoteDeadLineDate.setText(dateFormat.format(model.dateDeadLine));

            }



        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_form_menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.btn_save:

                Note model = (currentId > 0) ? App.getNoteRepository().findById(currentId) : new Note();

                model.title = editNoteTitle.getText().toString();
                model.text = editNoteText.getText().toString();

                String deadLineDateString = editNoteDeadLineDate.getText().toString();

                if (!deadLineDateString.isEmpty()) {

                    try {

                        model.dateDeadLine = dateFormat.parse(deadLineDateString);

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

                if (model.validate()) {

                    if (App.getNoteRepository().save(model)) {

                        Intent intent = new Intent();
                        intent.putExtra("id", model.getId());

                        setResult(App.RESULT_NEW_NOTE, intent);
                        finish();

                    } else {

                        Toast.makeText(NoteFormActivity.this, R.string.savingErrorMessage, Toast.LENGTH_LONG).show();

                    }

                } else {

                    Toast.makeText(NoteFormActivity.this, R.string.emptyNoteErrorMessage, Toast.LENGTH_LONG).show();

                }

                return true;

            default:
                return true;

        }

    }

}