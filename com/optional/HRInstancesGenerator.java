package com.optional;

import com.compulsory.Hospital;
import com.compulsory.Resident;
import com.github.javafaker.Faker;

import java.util.*;

/**
 * Class which is able to generate random
 * instances for Hospitals/Residents Problem (HR)
 *
 * @author Ioan Sava
 */
public class HRInstancesGenerator {
    private int numberOfResidents;
    private int numberOfHospitals;

    public HRInstancesGenerator(int numberOfResidents, int numberOfHospitals) {
        this.numberOfResidents = numberOfResidents;
        this.numberOfHospitals = numberOfHospitals;
    }

    public Set<Resident> generateResidents() {
        Faker faker = new Faker();
        Set<Resident> listOfResidents = new HashSet<>();

        int currentResident = 0;
        while (currentResident < numberOfResidents) {
            String name = faker.name().fullName();
            Resident resident = new Resident(name);
            listOfResidents.add(resident);
            ++currentResident;
        }

        return listOfResidents;
    }

    public Set<Hospital> generateHospitals() {
        Faker faker = new Faker();
        Random random = new Random();
        Set<Hospital> listOfHospitals = new HashSet<>();

        int currentHospital = 0;
        while (currentHospital < numberOfHospitals) {
            String name = faker.address().streetAddress();
            int capacity = random.nextInt(numberOfResidents) + 1;
            Hospital hospital = new Hospital(name, capacity);
            listOfHospitals.add(hospital);
            ++currentHospital;
        }

        return listOfHospitals;
    }

    /**
     * In order to generate hospitals preferences for each resident,
     * the list with available hospitals will be shuffled and then
     * a random number of hospitals will be chosen.
     */
    public Map<Resident, List<Hospital>> generateResidentsPreferences(Set<Resident> listOfResidents, Set<Hospital> listOfHospitals) {
        Map<Resident, List<Hospital>> residentsPreferences = new HashMap<>();
        Random random = new Random();

        for (Resident resident : listOfResidents) {
            List<Hospital> hospitals = new ArrayList<>(listOfHospitals);
            Collections.shuffle(hospitals);
            int randomNumberOfPreferences = random.nextInt(numberOfHospitals) + 1;
            residentsPreferences.put(resident, hospitals.subList(0, randomNumberOfPreferences));
        }

        return residentsPreferences;
    }

    /**
     * In order to generate residents rankings for each hospital,
     * the list with current residents will be shuffled and then
     * a random number of residents will be chosen.
     */
    public Map<Hospital, List<Resident>> generateHospitalsPreferences(Set<Hospital> listOfHospitals, Set<Resident> listOfResidents) {
        Map<Hospital, List<Resident>> hospitalsPreferences = new HashMap<>();
        Random random = new Random();

        for (Hospital hospital : listOfHospitals) {
            List<Resident> residents = new ArrayList<>(listOfResidents);
            Collections.shuffle(residents);
            int randomNumberOfPreferences = random.nextInt(numberOfResidents) + 1;
            hospitalsPreferences.put(hospital, residents.subList(0, randomNumberOfPreferences));
        }

        return hospitalsPreferences;
    }
}
