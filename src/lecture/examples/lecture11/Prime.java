package lecture.examples.lecture11;

public class Prime implements Runnable {
    private final int maxNumber;

    public Prime(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i < (n / 2) + 1; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    @Override
    public void run() {
        int i = 0;
        int prime = 2;
        while (i < this.maxNumber) {
            while (!isPrime(prime))
                prime++;

            System.out.println(i + "-th prime number: " + prime);
            i++;
            prime++;

            try {
                if (i % 5 == 0) {
                    System.out.println("Thread Prime is put into sleep.");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("Error : " + e.getMessage());
            }
        }
    }
}
