# **The Hospitals Residents Problem (HR)**

Console output:

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
    Matching{{Eldon Cummings=74822 Considine Ferry(2), Cyndi Crooks=917 Darrick Spring(2), Mrs. Kasie Hansen=917 Darrick Spring(2), Alverta Treutel=187 Hessel Station(0)}}
    Matching is stable

