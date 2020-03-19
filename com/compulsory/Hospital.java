package com.compulsory;

import com.optional.Element;

import java.util.Objects;

/**
 *  Each Hospital have a number of available posts (capacity).
 *  The capacity must not be exceeded.
 *
 * @author Ioan Sava
 */
public class Hospital implements Comparable<Hospital>, Element {
    private String name;
    private int capacity;

    public Hospital(String name) {
        this.name = name;
    }

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
