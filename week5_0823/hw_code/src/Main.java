import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Problem 1.
        // animalList initialize
        String[][] animalArray = {{"shark", "ocean"},
                                    {"bear", "land"},
                                    {"moose", "land"},
                                    {"frog", "swamp"},
                                    {"jelly fish", "ocean"},
                                    {"heron", "swamp"},
                                    {"whale", "ocean"}};
        List<Map<String, String>> animalList = new ArrayList<>();
        for (String[] element : animalArray) {
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("name", element[0]);
            tempMap.put("habitat", element[1]);
            animalList.add(tempMap);
        }

        // animalList convert to animalMap
        List<String> habitatList = animalList.stream()
                                            .map(element->element.get("habitat"))
                                            .distinct()
                                            .collect(Collectors.toList());
        Map<String, List<String>> animalMap = new HashMap<>();
        for (String habitat : habitatList) {
            List<String> animals = animalList.stream()
                                            .filter(element -> habitat.equals(element.get("habitat")))
                                            .map(element -> element.get("name"))
                                            .collect(Collectors.toList());
            animalMap.put(habitat, animals);
        }
        System.out.println("\n==========================\nProblem 1.");
        animalMap.forEach((habitat, animal) -> System.out.println(habitat + ": " + animal.stream().collect(Collectors.joining(", "))));

        // Problem 2.
        String[][] capitalArray = {{"USA", "Washington"},
                                    {"Japan", "Tokyo"},
                                    {"Thailand", "Bangkok"},
                                    {"UK", "London"},
                                    {"Australia", "Canberra"},
                                    {"Denmark", "Copenhagen"},
                                    {"Egypt", "Cairo"},
                                    {"Vietnam", "Hanoi"},
                                    {"Italy", "Romi"},
                                    {"Brazil", "Brazilia"}};
        Map<String, String> capitalMap = new HashMap<>();
        for (String[] capital : capitalArray) {
            capitalMap.put(capital[0], capital[1]);
        }
        System.out.println("\n==========================\nProblem 2.");
        capitalMap.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}