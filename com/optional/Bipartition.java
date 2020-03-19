package com.optional;

import com.compulsory.Hospital;
import com.compulsory.Resident;
import lombok.Getter;

import java.util.Set;

/**
 * This class describes two disjoint sets
 * of elements (like a bipartite graph)
 * for which it is desired to make a Matching.
 *
 * @author Ioan Sava
 */
@Getter
public class Bipartition {
    private Set<Resident> S;
    private Set<Hospital> T;

    public Bipartition(Set<Resident> s, Set<Hospital> t) {
        S = s;
        T = t;
    }
}
