package lecture.examples.lecture6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Source code from
 * <a href="https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-concurrency-2/src/main/java/com/baeldung/concurrent/parallel/Processor.java">...</a>
 */


public class ParallelOperations {


    public static void processSerially() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread.sleep(10);
        }
    }

    public static void processParallelyWithExecutorService() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, executorService);
            futures.add(future);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executorService.shutdown();
    }

    public static void processParallelyWithStream() {
        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void processParallelyWithStreamSupport() {
        Iterable<Integer> iterable = () -> IntStream.range(0, 100).iterator();
        Stream<Integer> stream = StreamSupport.stream(iterable.spliterator(), true);
        stream.forEach(i -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {


        /**
         * We simulate the execution of a complex operation by calling Thread.sleep(10), which causes the current thread
         * to sleep (= do nothing) for 10ms.
         *
         * The operations are processed on the same thread, in order. Since there are 100 operations, each taking around
         * 10ms each, we expect an execution time of at least 1s + any overhead from thread operations.
         */
        long t1 = System.currentTimeMillis();
        ParallelOperations.processSerially();
        long t2 = System.currentTimeMillis();
        System.out.println("Process serially: " + (t2 - t1) + " miliseconds");

        /**
         * Operations are carried out in parallel, using an ExecutorService. Here, a thread pool of 10 threads is created
         * and the operation's execution is parallelized using the 10 threads. Therefore, we expect an execution time
         * around 10 times shorter than in the example above.
         */
        t1 = System.currentTimeMillis();
        ParallelOperations.processParallelyWithExecutorService();
        t2 = System.currentTimeMillis();
        System.out.println("Process using ExecutorService: " + (t2 - t1) + " miliseconds");

        /*
         * Operations are carried out in parallel, using Java 8 streams. This implementation does not make clear the
         * number of threads used. Usually, implementations will create a number of threads smaller or equal to the
         * number of CPU cores on the machine on which the code is running.
         */
        t1 = System.currentTimeMillis();
        ParallelOperations.processParallelyWithStream();
        t2 = System.currentTimeMillis();
        System.out.println("Process using Java 8 streams: " + (t2 - t1) + " miliseconds");


        /*
         * Operations are carried out in parallel, using the StreamSupport class that provides Java 8 stream
         * functionalities.
         */
        t1 = System.currentTimeMillis();
        ParallelOperations.processParallelyWithStream();
        t2 = System.currentTimeMillis();
        System.out.println("Process using the StreamSupport class: " + (t2 - t1) + " miliseconds");
    }

}
