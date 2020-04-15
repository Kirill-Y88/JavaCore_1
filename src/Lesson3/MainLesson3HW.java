package Lesson3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class MainLesson3HW {

    public static void main(String[] args) {

        String[] hurt = {"What" ,  "have" ,  "I" , "become?" ,
                "My" , "sweetest" , "friend" ,
                "Everyone" , "I" , "know" ,
                "Goes" , "away" , "in" , "the", "end" ,
                "You", "could" , "have" , "it" , "all" ,
                "My" , "empire" , "of" , "dirt" ,
                "I" , "will" , "let" , "you" , "down" ,
                "I" , "will" , "make" , "you" , "hurt "};


        listWord(hurt);
        lotRepeat(hurt);



        HashMap<String,String[]> mybook = new HashMap<>();
        mybook.put("Ivanov", new String[]{"+79514343", "Ivanov@mail.ru"});
        mybook.put("Sidorov", new String[]{"+892734", "Sidorov@mail.ru"});
        mybook.put("Petrov", new String[]{"+66994343", "Petrov@mail.ru"});
        mybook.put("Smirnov", new String[]{"+66994343", "Smirnov@mail.ru"});
        mybook.put("Tinkof", new String[]{"+16954598", "Tinkof@mail.ru"});
        mybook.put("Ershov", new String[]{"+782246", "Ershov@mail.ru"});

    PhoneBook my = new PhoneBook(mybook);

        System.out.println(my.number("Ivanov"));
        System.out.println(my.email("Ivanov"));
        System.out.println(my.number("Petrov"));



    }

    private static void listWord(String[] hurt) {
        Set<String> song = new TreeSet<>();
        for (int i =0; i < hurt.length; i++){
            song.add(hurt[i]);
        }
        System.out.println(song);
    }

    private static void lotRepeat(String[] hurt) {
        HashMap<String, Integer> song = new HashMap<>();

        for(int i =0; i < hurt.length; i++) {
            int val = 0;
            for (int j = i   ; j < hurt.length; j++) {
                if (hurt[i].hashCode() == hurt[j].hashCode()) val++;
            }
            //не понял почему val везде 1, прогнал через дебагер - на некоторых словах val доходит до 4-х на нижестоящей строке...в общем - боль...
            song.put(hurt[i], val);
        }
        System.out.println(song);

    }


}
