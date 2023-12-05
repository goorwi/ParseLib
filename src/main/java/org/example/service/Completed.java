package org.example.service;

public class Completed implements OnCompleted {
    @Override
    public void OnCompleted(Object sender) {
        System.out.println("Загрузка окончена!");
    }
}
