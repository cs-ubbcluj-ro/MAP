package seminar.group323;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 1. Avem o lista de numere (din linia de comanda sau generate aleator)
 * - parsam linia de comanda a programului si alegem doar numerele (afisam lucrurile care nu sunt numere intregi)
 * - sa putem adauga un numar de numere aleatorii
 * 2. Afisam toate numerele patrate perfecte
 * 3. CMMDC al tuturor numerelor
 * 4. Iesire din program
 */

public class SeminarOne {

    private static boolean isPerfectSquare(int n) {
        int i = 1;
        while (i * i < n) i += 1;
        return i * i == n;
    }

    private static int gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return SeminarOne.gcd(b, a % b);
        }
    }

    private static int gcd(int... numbers) { // int... numar variabil de parametri
        // TODO De modificat parametrul de intrare in ArrayList<Integer>
        if (numbers.length == 1) {
            return numbers[0];
        }

        int gcdValue = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            gcdValue = SeminarOne.gcd(gcdValue, numbers[i]);
        }
        return gcdValue;
    }

    private static ArrayList<Integer> parseCommandLine(String[] arguments) {
        // int[] este un Array, ar fi "echivalent" cu int[] in C/C++
//        int[] numberArray = new int[arguments.length];
        // ca si liste in Java
        // 1. ArrayList - are la baza un vector
        // 2. LinkedList - are la baza o lista de noduri
        ArrayList<Integer> numberList = new ArrayList<Integer>();

//        int poz = 0;

        // for (int i =0;i<arguments.length;i++) {
        for (String s : arguments) {
            try {
                numberList.add(Integer.parseInt(s));
//                poz += 1;

            } catch (NumberFormatException nfe) {
                // TODO Bad practice
                System.out.println("Nu este numar - " + s);
            }
        }

        return numberList;
    }

    private static void displayPerfectSquares(String[] arguments) {
        // in Python metoda s-ar numi display_perfect_squares(...)
        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> numbersList = parseCommandLine(arguments);
        for (int n : numbersList) {
            if (SeminarOne.isPerfectSquare(n)) {
                sb.append(n).append(" ");
//                sb.append(" ");
            }
        }
        System.out.println("Numerele patrate perfecte - " + sb.toString()); // like __str__ in Python
    }

    private static void displayGCD(String[] arguments) {
        // TODO Should be parsed before calling the method
        ArrayList<Integer> numbersList = parseCommandLine(arguments);
//        int gcdValue = gcd(numbersList.toArray());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Generare numere aleatoare");
            System.out.println("2. Afisez patrate perfecte");
            System.out.println("3. Afisez CMMDC al tuturor numerelor");
            System.out.println("0. Iesire");
            String command = scanner.nextLine();

            if (command.equals("2")) {
                displayPerfectSquares(args);
            }
            if (command.equals("0")) {
                break;
            }
        }
//        ArrayList<Integer> numbers = SeminarOne.parseCommandLine(args);
//        for (int n : numbers)
//            System.out.println(n);
    }
}
