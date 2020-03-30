package com.models;

import com.problem.Element;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 *  Each Hospital have a number of available posts (capacity).
 *  The capacity must not be exceeded.
 *
 * @author Ioan Sava
 */
@Getter
@Setter
@AllArgsConstructor
public class Hospital implements Comparable<Hospital>, Element {
    private String name;
    private int capacity;

    public Hospital(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + "(" + capacity + ")";
    }

    @Override
    public int compareTo(Hospital hospital) {
        return this.getName().compareTo(hospital.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospital hospital = (Hospital) o;
        return Objects.equals(name, hospital.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
