package com.problem;

import com.models.Hospital;
import com.models.Resident;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class Bipartition {
    private Set<Resident> S;
    private Set<Hospital> T;
}
