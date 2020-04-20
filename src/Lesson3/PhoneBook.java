package Lesson3;

import java.util.HashMap;

public class PhoneBook {
    HashMap<String,String[]> phonebook = new HashMap<>();

    public PhoneBook(HashMap<String, String[]> phonebook) {
        this.phonebook = phonebook;
    }

    //не понял необходимость эррейлиста, если по уникальному ключу "фамилии" можно вывести только одно значение
    public String number (String Family){
        String [] data;
        data = phonebook.get(Family);
        return data[0];
    }

    public String email (String Family){
        String [] data;
        data = phonebook.get(Family);
        return data[1];
    }

}
