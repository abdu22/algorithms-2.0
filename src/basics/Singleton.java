package basics;

import java.util.HashMap;
import java.util.Map;

public class Singleton {
    public static void main(String[] args) {
        Map<Person, Integer>  m = new HashMap<>();
        int start = 0; int end = 100;
        
        for(int j = 0; j < 10 ; j++) {
            Thread t = new Thread(new MyThread(start, end, m) );
            t.start();
            start = end;
             end += 100;
        } 

      System.out.println( " === === === === === === == : Map Size" + m.size());  
    }
}

class Person {
    private static Person person;

    private Person(){

    }
    public static Person getPerson(){
        if(person == null){
            synchronized (Person.class) {
                person = new Person();
            }  
        }
        return person;
    }
}


class MyThread implements Runnable {
    int start; 
    int end;
    Map<Person, Integer>  m ;

    MyThread(int start, int end, Map<Person, Integer>  m ) {
        this.start = start;
        this.end = end;
        this.m = m;
    }

    @Override
    public void run() {
        for(int i=start; i<end; i++) {
            Person temp = Person.getPerson();
            //System.out.println("person : " + temp.hashCode());
            m.put(temp, 1);
        }
    }
}
