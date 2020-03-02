package ru.sterlikoff.diplomanotepad.components;

public interface KeyStore {

    boolean hasPin();
    boolean checkPin(String pin);
    void setPin(String pin);

}