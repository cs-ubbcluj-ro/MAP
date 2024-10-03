package seminar.group321;

import java.util.ArrayList;
import java.util.List;

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
        }
        else{
            listaNumere = citesteTastatura();
        }


    }

    private static List<Integer> citesteTastatura() {

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
