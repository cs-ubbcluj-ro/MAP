package lecture.livecoding;

class Util {

    static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static int[] generateArray(int n) {
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = (int) (Math.random() * 1000000);
        }
        return numbers;
    }
}

class PrimeRunnable implements Runnable {

    int[] numberArray; // vectorul cu toate numerele

    int startIndex; // primul index verificat in acest thread

    int step; // cate numere sarim

    int result;

    public PrimeRunnable(int[] numberArray, int startIndex, int step) {
        this.numberArray = numberArray;
        this.startIndex = startIndex;
        this.step = step;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < numberArray.length; i += step) {
            if (Util.isPrime(numberArray[i])) {
                result += 1;
            }
        }
    }

    public int getResult() {
        return result;
    }
}

public class Lecture11 {
    public static void main(String[] args) throws InterruptedException {


//        System.out.println("Inainte de marea asteptare");
//        // aici se porneste "efectiv" firul de executie
//        waiter.start();
//        System.out.println("asteptam ...");
//        waiter.join();
//        System.out.println("Dupa marea asteptare");

//        int[] numere = Util.generateArray(5);
//        for (var n : numere) {
//            System.out.println(n);
//        }

//        var numere = Util.generateArray(10000);
//        var numarNumerePrime = 0;
//        System.out.println("start");
//        var t1 = System.currentTimeMillis();
//
//        for (int j : numere) {
//            if (Util.isPrime(j)) {
//                numarNumerePrime += 1;
//            }
//        }
//        var t2 = System.currentTimeMillis();
//        System.out.println("stop, numere prime = " + Integer.toString(numarNumerePrime));
//        System.out.println("calculul a durat =" + Long.toString(t2 - t1) + " milisecunde.");

//        var numere = Util.generateArray(10000);
//        var runnable = new PrimeRunnable(numere, 0, 1);
//        Thread waiter = new Thread(runnable);
//        waiter.start();
//        waiter.join();
//        System.out.println(runnable.getResult());

        var numbers = Util.generateArray(1000000);
        int numberOfThreads = 8;

        PrimeRunnable[] primeRunnables = new PrimeRunnable[numberOfThreads];
        for (var i = 0; i < numberOfThreads; i++) {
            primeRunnables[i] = new PrimeRunnable(numbers, i, numberOfThreads);
        }

        Thread[] threads = new Thread[numberOfThreads];
        for (var i = 0; i < numberOfThreads; i++) {
            threads[i] = new Thread(primeRunnables[i]);
        }

        int total = 0;
        var t1 = System.currentTimeMillis();
        for (var i = 0; i < numberOfThreads; i++) {
            threads[i].start();
        }

        for (var i = 0; i < numberOfThreads; i++) {
            threads[i].join();
        }

        for (var pr : primeRunnables) {
            total += pr.getResult();
        }
        var t2 = System.currentTimeMillis();
        System.out.println("calculul a durat =" + Long.toString(t2 - t1) + " milisecunde.");
        System.out.println(total);

    }
}
