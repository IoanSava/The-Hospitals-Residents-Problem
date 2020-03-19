package com.optional;

import com.compulsory.Hospital;
import com.compulsory.Resident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Matching M is an assignment such that |M(resident)| <= 1
 * for each resident and |M(hospital)| <= hospital.capacity
 * for each hospital (i.e. no resident is assigned to an
 * unacceptable hospital, each resident is assigned to at
 * most one hospital, and no hospital is over-subscribed).
 *
 * @author Ioan Sava
 */
public class Matching {
    private Map<Resident, Hospital> listOfPairs = new HashMap<>();

    public void addPair(Resident firstElement, Hospital secondElement) {
        listOfPairs.put(firstElement, secondElement);
    }

    /**
     * The method checks if there is a Resident - Hospital pair
     * that has not been added to the Matching and which is "better"
     * (relative to residents and hospitals preferences ranking)
     * than the pair provided as a parameter
     * {@param resident} - {@param hospital}.
     */
    private boolean betterPair(Resident resident, Hospital hospital, Map<Resident, List<Hospital>> residentsPreferences, Map<Hospital, List<Resident>> hospitalsPreferences) {
        for (Hospital currentHospital : residentsPreferences.get(resident)) {
            if (currentHospital != hospital) {
                boolean startChecking = false;
                for (Resident currentResident : hospitalsPreferences.get(currentHospital)) {
                    if (startChecking) {
                        for (Map.Entry<Resident, Hospital> pair : listOfPairs.entrySet()) {
                            if (pair.getKey() == currentResident && pair.getValue() == currentHospital) {
                                return true;
                            }
                        }
                    }

                    if (currentResident == resident) {
                        startChecking = true;
                    }
                }
            }
            else {
                return false;
            }
        }

        return false;
    }

    /**
     * A matching is stable if there is no pair (r, h) such that r
     * is assigned to h' but r prefers h better than h' and h prefers
     * r better than some of its assigned residents.
     *
     * The method tries to find such a pair.
     */
    public boolean isStable(Map<Resident, List<Hospital>> residentsPreferences, Map<Hospital, List<Resident>> hospitalsPreferences) {
        for (Map.Entry<Resident, Hospital> pair : listOfPairs.entrySet()) {
            Resident resident = pair.getKey();
            Hospital hospital = pair.getValue();

            if (betterPair(resident, hospital, residentsPreferences, hospitalsPreferences)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "Matching{" + listOfPairs + '}';
    }
}
