package lecture.examples.lecture11;

public class Fibonacci implements Runnable {
    private int maxNumber;

    Fibonacci(int max) {
        this.maxNumber = max;
    }

    @Override
    public void run() {
        int a = 0;
        int b = 1;
        int n = 0;

        while (n < maxNumber) {
            System.out.println(n + "-th" + " Fibonacci number: = " + a);
            int c = a + b;
            a = b;
            b = c;

            try {
                if (n % 5 == 0) {
                    System.out.println("Thread Fibonacci is put into sleep.");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Error : " + e.getMessage());
            }
            n++;
        }
    }
}
