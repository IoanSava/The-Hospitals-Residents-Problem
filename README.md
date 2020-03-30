

# The Hospitals/Residents Problem (HR)
 
An instance of HR involves a set of _residents_ and a set of _hospitals_, each resident seeking a post at one hospital, and each hospital having a number of available posts (its capacity). Each resident _ranks_ some (acceptable) hospitals in strict order, and each hospital ranks its applicants in strict order. A _matching_ is a set of pairs _(resident, hospital)_ such that each resident is assigned to at most one hospital and the capacities of the hospitals are not exceeded. A matching is _stable_ if there is no pair (r, h) such that r is assigned to h' but r prefers h better than h' and h prefers r better than some of its assigned residents. We consider the problem of creating a stable matching between residents and hospitals.


Console output example:

    Residents: R0 R1 R2 R3 
    Hospitals: H0(1) H1(2) H2(2)
    
    List of Residents: [R0, R1, R2, R3]
    Sorted list of Residents: [R0, R1, R2, R3]
    TreeSet of Hospitals: [H0(1), H1(2), H2(2)]
    
    HashMap: {R3=[H0(1), H2(2)], R0=[H0(1), H1(2), H2(2)], R1=[H0(1), H1(2), H2(2)], R2=[H0(1), H1(2)]}
    LinkedHashMap: {H0(1)=[R3, R0, R1, R2], H1(2)=[R0, R2, R1], H2(2)=[R0, R1, R3]}
    
    Residents who find acceptable H0 and H2: [R0, R1, R3]
    Hospitals that have R0 as their top preferance: [H1(2), H2(2)]
    
    Solution for the example: 
    Matching{{R3=H0(0), R0=H1(0), R1=H2(1), R2=H1(0)}}
    Matching is stable
    
    Solution for a problem with random instance: 
    Matching{{Deana Borer=0481 Grady Isle(1), Wynell Stanton=0481 Grady Isle(1), Nubia Russel=0481 Grady Isle(1), Andre Botsford=0481 Grady Isle(1), Zachary Emmerich=90196 Rolande Parks(5), Sonny Donnelly=1672 Kerluke Island(0)}}
    Matching is stable

