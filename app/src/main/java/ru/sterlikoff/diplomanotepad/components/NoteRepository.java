package ru.sterlikoff.diplomanotepad.components;

import java.util.Map;

import ru.sterlikoff.diplomanotepad.models.Note;

public interface NoteRepository {

    Note findById(int id);
    Map<Integer, Note> findAll();
    boolean save(Note model);
    boolean deleteById(int id);

}
