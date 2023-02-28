package basics;

public class MultiThreadExecutor {
    public static void main(String[] args) {

     String [] words = new String[] {"axx", "bxxxx", "", "dxxxxxxx"};
 
      for(int i=0; i < words.length; i++) {
        Thread t1 = new Thread(new MyTask(words[i]), "Thread-"+i);
        t1.start();
      }        
    }
}

  class MyTask implements Runnable {
    String word;
    public MyTask(String word) {
        this.word = word;
    }

    @Override
    public void run() {
        System.out.println("Thread Name: " + Thread.currentThread().getName());
        System.out.println("Word len: " + word.length());
    }
     
}

