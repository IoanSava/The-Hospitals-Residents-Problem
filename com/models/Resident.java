package com.models;

import com.problem.Element;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * Each Resident is seeking a post at one Hospital
 *
 * @author Ioan Sava
 *
 */
@Getter
@AllArgsConstructor
public class Resident implements Comparable<Resident>, Element {
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Resident resident) {
        return this.getName().compareTo(resident.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resident resident = (Resident) o;
        return Objects.equals(name, resident.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
