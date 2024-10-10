package seminar.group322;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
      Se lucreaza cu o lista de numere. Aceste numere se pot citi in doua moduri:
         - din linia de comanda
         - daca nu exista argumente in linia de comanda, de la tastatura
      In orice mod ar fi citita lista, este necesara gestionarea cazului in care
      se citeste si altceva decat un numar.

      Scrieti o aplicatie cu un meniu care are are urmatoarele functionalitati:
        1. Cautare numar dat de la tastatura
        2. Afisati cel mai mic numar prim din lista de numere
        3. Eliminati numerele perfecte din lista (numar perfect = numar egal cu suma divizorilor sai proprii + 1).
        4. Gasiti cea mai lunga secventa de numere pentru care diferenta intre doua elemente consecutive sa fie maxim 5.
            11 19 12 13 8 1 11 90 -> 12 13 8
            1 2 3 4 5 -> 1 2 3 4 5
            90 100 120  -> 90 sau 100 sau 120
        5. Iesire
 */

public class SeminarOne {
    public static void main(String[] args) {
        //String[] arrTest = new String[50];
        List<Integer> listaNumere;
        Scanner scanner = new Scanner(System.in);

        if (args.length == 0) {
            System.out.print("Nu s-a gasit nimic in linia de comanda. Introduceti lista: ");
            String listaNumereString = scanner.nextLine();
            System.out.println("Am citit string-ul:" + listaNumereString);
            String[] splitList = listaNumereString.split(" ");
            for (String element : splitList) {
                System.out.println(element);
            }
            System.out.println("Se vor citi numerele de la tastatura");
            listaNumere = convertToIntList(splitList);
        } else {
            //exista elemente in linia de comanda
            listaNumere = convertToIntList(args);
        }
        System.out.println("Lista este: " + listaNumere.toString());
        boolean appIsRunning = true;
        while (appIsRunning) {
            afisareMeniu();
            System.out.print(">>>");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Numarul cautat: ");
                    int nrCautat = scanner.nextInt();
                    boolean existaInLista = listaNumere.contains(nrCautat);
                    if (existaInLista)
                        System.out.println("Numarul se regaseste in lista.");
                    else
                        System.out.println("Numarul nu se regaseste in lista.");
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    List<Integer> secventa = longestSequence(listaNumere);
                    System.out.println(secventa.toString());
                    break;
                case 5:
                    appIsRunning = false;
                    break;
                default:
                    System.out.println("Optiunea este gresita.");
            }
        }

    }

    private static List<Integer> longestSequence(List<Integer> listaNumere) {
        int lungmax = 0, lungcurenta = 0, pozitiefinala = 0;
        for (int i = 0; i < listaNumere.size() - 1; i++) {
            if (Math.abs(listaNumere.get(i) - listaNumere.get(i + 1)) <= 5) {
                lungcurenta++;
            } else {
                if (lungcurenta > lungmax) {
                    lungmax = lungcurenta;
                    pozitiefinala = i;
                }
                lungcurenta = 0;
            }
        }
        if (lungcurenta > lungmax) {
            lungmax = lungcurenta;
            pozitiefinala = listaNumere.size() - 1;
        }
        List<Integer> Rezultat = new ArrayList<>();
        for (int i = pozitiefinala - lungmax; i <= pozitiefinala; i++) {
            Rezultat.add(listaNumere.get(i));
        }
        return Rezultat;
    }

    private static void afisareMeniu() {
        System.out.println("1. Cautare numar dat de la tastatura");
        System.out.println("2. Afisati cel mai mic numar prim din lista de numere");
        System.out.println("3.Eliminati numerele perfecte din lista (numar perfect = numar egal cu suma divizorilor sai proprii + 1)");
        System.out.println("4. Gasiti cea mai lunga secventa de numere pentru care diferenta intre doua elemente consecutive sa fie maxim 5.");
//        11 19 12 13 8 1 11 90 -> 12 13 8
//        1 2 3 4 5 -> 1 2 3 4 5
//        90 100 120  -> 90 sau 100 sau 120
        System.out.println("5. Iesire");
    }

    private static List<Integer> convertToIntList(String[] args) {
        List<Integer> listaNumere = new ArrayList<>();
        for (String arg : args) {
            try {
                int iarg = Integer.parseInt(arg);
                System.out.println(iarg);
                listaNumere.add(iarg);
            } catch (NumberFormatException ex) {
                System.out.println(arg + " nu a putut fi transformat in int.");
            }
        }
        return listaNumere;
    }
}
