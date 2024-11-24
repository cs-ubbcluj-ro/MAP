package seminar.group322.seminar4streams;

import com.diogonunes.jcolor.Attribute;

import java.util.*;
import java.util.stream.Collectors;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Main {
    public static void main(String[] args) {
        PlayerCollection playersCollection = new PlayerCollection();
        List<Player> playersList = playersCollection.getAll();

        //Lista de jucatori de la o echipa
        //Se afiseaza:
        //Nume echipa - lista jucatori
        //groupingBy -> grupeaza in functie de echipa


        Map<String, List<Player>> teamPlayers = playersList.stream().collect(Collectors.groupingBy(Player::getTeam));
        teamPlayers.forEach((team, playerList) -> {
            System.out.println("Echipa este: " + colorize(team, Attribute.BLUE_TEXT()));
            playerList.forEach(player -> System.out.println(player.getName()));
        });

        //Numarul de jucatori din fiecare tara
        //Se afiseaza tara: numarul de jucatori
        Map<String, Long> nationalityCount = playersList.stream().collect(Collectors.groupingBy(Player::getNationality, Collectors.counting()));
        //have to add Jcolor library to project for colorize to work
        //File -> Project Structure -> Libraries -> + -> Search jcolor
        nationalityCount.forEach((nationality, count) -> System.out.println("Nationalitate: " + colorize(nationality, Attribute.BLUE_TEXT()) + ": " + count));

        //cel mai tanar jucator
        //Optional: https://www.baeldung.com/java-optional
        Optional<Player> youngestPlayer = playersList.stream().min(Comparator.comparingInt(Player::getAge));
        youngestPlayer.ifPresent(player -> System.out.println(player));

        Collection<Player> playersList2 = new ArrayList<>();
        try {
            Player youngestPlayer2 = playersList2.stream()
                    .min(Comparator.comparingInt(Player::getAge))
                    .orElseThrow(() -> new Exception("Nu exista niciun jucator in lista."));
            System.out.println("Youngest player: " + youngestPlayer2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        //Media de varsta in functie de pozitia din teren
        //Se afiseaza: pozitia din teren: media de varsta
        //Pas 1: grupare dupa playingPosition
        //Pas 2: medie
        Map<String, Double> ageAvgPosition = playersList.stream()
                .collect(Collectors.groupingBy(Player::getPlayerPosition, Collectors.averagingInt(Player::getAge)));
        System.out.println("Media de varsta in functie de pozitia din teren:");
        ageAvgPosition.forEach((playingPosition, avgAge) ->
                System.out.println(colorize(playingPosition, Attribute.BOLD()) + ": " + avgAge));


        //youngest player on playing position
        //Se afiseaza: Pozitie din teren: cel mai tanar jucator (nume, varsta)
        Map<String, Optional<Player>> youngestPlayerPerPosition = playersList.stream()
                .collect(Collectors.groupingBy(
                        Player::getPlayerPosition,
                        Collectors.minBy(Comparator.comparingInt(Player::getAge))
                ));
        youngestPlayerPerPosition.forEach((position, youngestPlayerPosition) ->
                youngestPlayerPosition.ifPresent(player -> {
                            System.out.println("Playing position: " + colorize(position, Attribute.BLUE_TEXT()));
                            System.out.println("Youngest player:");
                            System.out.println(player.getName() + " (Age: " + player.getAge() + ")");

                        }
                )
        );

        //Tara cu cei mai multi jucatori
        //Ne folosim de Map-ul creat anterior, nationalityCount
        //in care avem tara - nr. de jucatori
        //pe multimea de perechi (cheie - valoare), aplicam max
        //comparand dupa valoare (valoarea = numarul de jucatori)
        //Se returneaza o pereche tara - nr. jucatori
        Optional<Map.Entry<String, Long>> mostCommonNationality = nationalityCount
                .entrySet().stream()
                .max(Map.Entry.comparingByValue());
        mostCommonNationality.ifPresent(x -> System.out.println("Tara cu cei mai multi jucatori: " +
                colorize(x.getKey(), Attribute.GREEN_TEXT()) + " (" +
                colorize(String.valueOf(x.getValue()), Attribute.RED_TEXT()) + " jucatori)"));


        //set of all nationalities
        Set<String> playerNationalities = playersList.stream().map(Player::getNationality).collect(Collectors.toSet());
        //playerNationalities.forEach(System.out::println);
        System.out.println(playerNationalities.toString());

        playersCollection.closeConnection();
    }
}