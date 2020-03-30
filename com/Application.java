package com;

import com.models.Hospital;
import com.models.Resident;
import com.problem.HRInstancesGenerator;
import com.problem.Matching;
import com.problem.Problem;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Ioan Sava
 */
public class Application {
    /**
     * @param residents current list of residents
     * @param hospitals current list of available hospitals
     * @return residentsPreferences the hospital rankings of each resident
     */
    public static Map<Resident, List<Hospital>> buildResidentsMap(Resident[] residents, Hospital[] hospitals) {
        Map<Resident, List<Hospital>> residentsPreferences = new HashMap<>();
        residentsPreferences.put(residents[0], Arrays.asList(hospitals[0], hospitals[1], hospitals[2]));
        residentsPreferences.put(residents[1], Arrays.asList(hospitals[0], hospitals[1], hospitals[2]));
        residentsPreferences.put(residents[2], Arrays.asList(hospitals[0], hospitals[1]));
        residentsPreferences.put(residents[3], Arrays.asList(hospitals[0], hospitals[2]));
        return residentsPreferences;
    }

    /**
     * @param residents current list of residents
     * @param hospitals current list of available hospitals
     * @return hospitalsPreferences hospital rankings
     */
    public static Map<Hospital, List<Resident>> buildHospitalsMap(Resident[] residents, Hospital[] hospitals) {
        Map<Hospital, List<Resident>> hospitalsPreferences = new LinkedHashMap<>();
        hospitalsPreferences.put(hospitals[0], Arrays.asList(residents[3], residents[0], residents[1], residents[2]));
        hospitalsPreferences.put(hospitals[1], Arrays.asList(residents[0], residents[2], residents[1]));
        hospitalsPreferences.put(hospitals[2], Arrays.asList(residents[0], residents[1], residents[3]));
        return hospitalsPreferences;
    }

    /**
     * The method returns a list of residents who find acceptable
     * some hospitals, provided as a parameter in {@param targetedHospitals}
     */
    public static List<Resident> getResidentsByTarget(List<Resident> listOfResidents, Map<Resident, List<Hospital>> residentsPreferences, List<Hospital> targetedHospitals) {
        return listOfResidents.stream()
                .filter(resident -> residentsPreferences.get(resident).containsAll(targetedHospitals))
                .collect(Collectors.toList());
    }

    /**
     * The method return a list of hospitals that have a specific resident
     * {@param targetedResident} as their top preference.
     */
    public static List<Hospital> getHospitalsByTopTarget(Set<Hospital> listOfHospitals, Map<Hospital, List<Resident>> hospitalsPreferences, Resident targetedResident) {
        return listOfHospitals.stream()
                .filter(hospital -> hospitalsPreferences.get(hospital).get(0).equals(targetedResident))
                .collect(Collectors.toList());
    }

    public static void checkIfMatchingIsStable(Matching matching, Map<Resident, List<Hospital>> residentsPreferences, Map<Hospital, List<Resident>> hospitalsPreferences) {
        if (matching.isStable(residentsPreferences, hospitalsPreferences)) {
            System.out.println("Matching is stable");
        } else {
            System.out.println("Matching is not stable");
        }
    }

    public static Resident[] generateResidents(int numberOfResidents) {
        return IntStream.rangeClosed(0, numberOfResidents - 1)
                .mapToObj(indexOfResident -> new Resident("R" + indexOfResident))
                .toArray(Resident[]::new);
    }

    public static void printResidents(Resident[] residents) {
        System.out.print("Residents: ");
        for (Resident resident : residents) {
            System.out.print(resident + " ");
        }
        System.out.println();
    }

    public static Hospital[] generateHospitals(int numberOfHospitals) {
        return IntStream.rangeClosed(0, numberOfHospitals - 1)
                .mapToObj(indexOfHospital -> new Hospital("H" + indexOfHospital))
                .toArray(Hospital[]::new);
    }

    public static void setHospitalsCapacities(Hospital[] hospitals) {
        hospitals[0].setCapacity(1);
        hospitals[1].setCapacity(2);
        hospitals[2].setCapacity(2);
    }

    public static void printHospitals(Hospital[] hospitals) {
        System.out.print("Hospitals: ");
        for (Hospital hospital : hospitals) {
            System.out.print(hospital + " ");
        }
        System.out.println();
    }

    /**
     * Generate a random instance of Hospitals/Residents Problem (HR)
     * and solve it using a matching algorithm.
     */
    public static void solveProblemWithRandomInstance(int numberOfResidents, int numberOfHospitals) {
        HRInstancesGenerator HR = new HRInstancesGenerator(numberOfResidents, numberOfHospitals);
        Set<Resident> residents1 = HR.generateResidents();
        Set<Hospital> hospitals1 = HR.generateHospitals();
        Map<Resident, List<Hospital>> residentsPreferences = HR.generateResidentsPreferences(residents1, hospitals1);
        Map<Hospital, List<Resident>> hospitalsPreferences = HR.generateHospitalsPreferences(hospitals1, residents1);
        Problem P1 = new Problem(residents1, hospitals1, residentsPreferences, hospitalsPreferences);
        Matching M1 = P1.solve();
        System.out.println(M1);
        checkIfMatchingIsStable(M1, residentsPreferences, hospitalsPreferences);
    }

    public static void main(String[] args) {
        Resident[] residents = generateResidents(4);
        printResidents(residents);

        Hospital[] hospitals = generateHospitals(3);
        setHospitalsCapacities(hospitals);
        printHospitals(hospitals);

        List<Resident> listOfResidents = new ArrayList<>(Arrays.asList(residents));
        System.out.println("List of Residents: " + listOfResidents);
        listOfResidents.sort(Comparator.comparing(Resident::getName));
        System.out.println("Sorted list of Residents: " + listOfResidents);

        Set<Hospital> listOfHospitals = new TreeSet<>(Arrays.asList(hospitals));
        System.out.println("TreeSet of Hospitals: " + listOfHospitals);

        //build HashMap
        Map<Resident, List<Hospital>> residentsPreferences = buildResidentsMap(residents, hospitals);
        System.out.println("HashMap: " + residentsPreferences);

        //build LinkedHashMap
        Map<Hospital, List<Resident>> hospitalsPreferences = buildHospitalsMap(residents, hospitals);
        System.out.println("LinkedHashMap: " + hospitalsPreferences);

        List<Hospital> targetedHospitals = Arrays.asList(hospitals[0], hospitals[2]);
        System.out.println("Residents who find acceptable H0 and H2: " + getResidentsByTarget(listOfResidents, residentsPreferences, targetedHospitals));

        System.out.println("Hospitals that have R0 as their top preferance: " + getHospitalsByTopTarget(listOfHospitals, hospitalsPreferences, residents[0]));

        System.out.println("Solution for the example: ");
        Problem P = new Problem(new HashSet<>(listOfResidents), listOfHospitals, residentsPreferences, hospitalsPreferences);
        Matching M = P.solve();
        System.out.println(M);
        checkIfMatchingIsStable(M, residentsPreferences, hospitalsPreferences);

        System.out.println("Solution for a problem with random instance: ");
        solveProblemWithRandomInstance(6, 3);
    }
}
