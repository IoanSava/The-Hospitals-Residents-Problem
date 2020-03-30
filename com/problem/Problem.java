package com.problem;

import com.models.Hospital;
import com.models.Resident;
import lombok.Getter;

import java.util.*;

/**
 * This class describes the Hospitals/Residents Problem (HR)
 * and a way to solve it.
 *
 * @author Ioan Sava
 */
@Getter
public class Problem {
    private Bipartition partition;
    private Map<Resident, List<Hospital>> residentsPreferences;
    private Map<Hospital, List<Resident>> hospitalsPreferences;

    public Problem(Set<Resident> residents, Set<Hospital> hospitals, Map<Resident, List<Hospital>> residentsPreferences, Map<Hospital, List<Resident>> hospitalsPreferences) {
        partition = new Bipartition(residents, hospitals);
        this.residentsPreferences = residentsPreferences;
        this.hospitalsPreferences = hospitalsPreferences;
    }

    /**
     * @return the maximum number of residents which a hospital
     * of the available ones takes into consideration
     */
    private int getMostHospitalPreferences() {
        return partition.getT().stream()
                .mapToInt(hospital -> hospitalsPreferences.get(hospital).size())
                .max()
                .getAsInt();
    }

    /**
     * Add residents to hospitals according to hospitals preferences rankings
     * and also if the resident's preferences include the hospital.
     *
     * @return a Matching that could represent a possible
     * allocation of residents to hospitals
     */
    public Matching solve() {
        Set<Resident> listOfResidents = new HashSet<>(partition.getS());
        Set<Hospital> listOfHospitals = new HashSet<>(partition.getT());
        Matching Solution = new Matching();

        int numberOfResidents = listOfResidents.size();
        int position = 0;
        int maxPosition = getMostHospitalPreferences();

        //assign a hospital to every resident
        while (numberOfResidents > 0 && position <= maxPosition) {
            for (Hospital hospital : listOfHospitals) {
                //if the hospital is full, don't add more residents
                if (hospital.getCapacity() > 0 && position < hospitalsPreferences.get(hospital).size()) {
                    Resident currentHospitalOption = hospitalsPreferences.get(hospital).get(position);
                    if (residentsPreferences.get(currentHospitalOption).contains(hospital) &&
                            listOfResidents.contains(currentHospitalOption)) {
                        Solution.addPair(currentHospitalOption, hospital);
                        hospital.setCapacity(hospital.getCapacity() - 1);
                        listOfResidents.remove(currentHospitalOption);
                        --numberOfResidents;
                    }
                }
            }
            ++position;
        }

        return Solution;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "partition=" + partition +
                ", residentsPreferences=" + residentsPreferences +
                ", hospitalsPreferences=" + hospitalsPreferences +
                '}';
    }
}
