package ru.sterlikoff.diplomanotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.sterlikoff.diplomanotepad.classes.NoteAdapter;
import ru.sterlikoff.diplomanotepad.models.Note;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    protected void initViews() {

        listView = findViewById(R.id.noteList);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        List<Note> list = new ArrayList<>();

        list.add(new Note(
                "Заголовок первой задачи",
                "At the time of inflating the CardView xml inside your ViewPager, pass the ViewGroup in it. this will allow inflater to know which layout parameters to refer.",
                new Date()));

        list.add(new Note(
                "Задача № 2",
                "At the time of inflating the CardView xml inside your ViewPager, pass the ViewGroup in it. this will allow inflater to know which layout parameters to refer.",
                new Date()
        ));

        list.add(new Note(
                "А на третьей задачи мы решили оторваться и сделать длинный предлинный заголовок",
                "This site is in The Inneka Network (also referred to herein as “Inneka” or “Network” or “Inneka.com”) which is a set of related Internet websites and applications. All of our products are focused on providing useful information and knowledge to our reader.",
                new Date()
        ));

        NoteAdapter adapter = new NoteAdapter(list, this);
        listView.setAdapter(adapter);

    }

}