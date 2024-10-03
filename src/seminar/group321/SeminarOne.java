package seminar.group321;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/*
      Se lucreaza cu o lista de numere. Aceste numere se pot citi in doua moduri:
         - din linia de comanda
         - daca nu exista argumente in linia de comanda, de la tastatura
      In orice mod ar fi citita lista, este necesara gestionarea cazului in care
      se citeste si altceva decat un numar.

      Scrieti o aplicatie cu un meniu care are are urmatoarele functionalitati:
        1. Afisati minimul din lista de numere.
        2. Eliminati numerele prime din lista.
        3. Cautare numar dat de la tastatura
        3. Gasiti cea mai lunga secventa de numere impare din lista.
        4. Iesire
 */
public class SeminarOne {
    public static void main(String[] args) {
        //Afisare: System.out.println("Ceva");
        List<Integer> listaNumere;

        if (args.length > 0) {
            listaNumere = procesareListaArgumente(args);
            System.out.println(listaNumere.toString());
        } else {
            System.out.println("Citesc de la tastatura...");
            listaNumere = citesteTastatura();
        }

        System.out.println("Lista citita: " + listaNumere.toString());
        eliminareNrPrime(listaNumere);
        System.out.println("Lista fara numere prime: " + listaNumere.toString());

    }

    private static void eliminareNrPrime(List<Integer> listaNumere) {
//        List<Integer> listaFaraPrime = new ArrayList<>();
//        for (int i = 0; i < listaNumere.size(); i++) {
//            if (!estePrim(listaNumere.get(i)))
//                listaFaraPrime.add(listaNumere.get(i));
//        }
//        return listaFaraPrime;
        int i = 0;
        while (i < listaNumere.size()) {
            if (estePrim(listaNumere.get(i)))
                listaNumere.remove(i);
            else
                i += 1;
//        }
        }
    }

    private static boolean estePrim(Integer nr) {
        if (nr < 2)
            return false;

        if (nr == 2)
            return true;
        if (nr % 2 == 0)
            return false;
        for (int d = 2; d < nr / 2; d++) {
            if (nr % d == 0)
                return false;
        }
        return true;

    }

    private static List<Integer> citesteTastatura() {
        List<Integer> listaNumere = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Numarul de numere citite:");
        //int cateNrCitite = scanner.nextInt();
        while (scanner.hasNext()) {
            String current = scanner.next();
            if (current.equals("stop"))
                break;
            try {
                int nrCurent = Integer.parseInt(current);
                listaNumere.add(nrCurent);
            } catch (NumberFormatException ex) {
                System.out.println(current + " nu este nr. Skipping..");
            }

        }
//        int nrNecitite = 0;
//        for (int i=0; i<cateNrCitite;i++){
//            try {
//                int nrCurent = scanner.nextInt();
//                listaNumere.add(nrCurent);
//            }catch (InputMismatchException ex){
//                nrNecitite+=1;
//            }
//        }

        return listaNumere;
    }

    private static List<Integer> procesareListaArgumente(String[] args) {
        List<Integer> listaNumere = new ArrayList<>();
        for (String arg : args) {
            System.out.println("Un argument este: " + arg);
            try {
                int ai = Integer.parseInt(arg);
                System.out.println(ai);
                listaNumere.add(ai);
            } catch (NumberFormatException ex) {
                System.out.println("Argumentul " + arg + " nu a putut fi transformat in int.");
            }
        }
        return listaNumere;
    }
}
