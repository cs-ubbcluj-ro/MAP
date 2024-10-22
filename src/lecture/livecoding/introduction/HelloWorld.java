package lecture.livecoding.introduction;

import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
//        System.out.println("Hello World!");


        System.out.println("Numar parametri: " + args.length);
        int total = 0;
        for (int i = 0; i < args.length; i++) {
            try {
                total += Integer.parseInt(args[i]);
            } catch (NumberFormatException ex) {
                System.out.println("Parametru care nu e intreg - " + args[i]);
            }
        }

        System.out.println("Introducem numere de la tastatura");
        Scanner input = new Scanner(System.in);

        int readValue = 0;
        do {

            try {
                readValue = Integer.parseInt(input.nextLine());
                total += readValue;
//            } catch (InputMismatchException ex) {
            } catch (NumberFormatException ex) {
//            } catch (Exception ex) {
                System.out.println("Nu e valoare intreaga");
                readValue = 1;
            }

        } while (readValue != 0);


        System.out.println("Total = " + total);

    }
}
