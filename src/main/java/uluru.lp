%assign the birds to the fields
onOtherField(F,B) :- onField(G,B), F != G, field(F).
1{onField(F,B): bird(B), not onOtherField(F,B)}1 :- field(F).

%2er wishes
%(w2.1)
:~ onCrowded(B), onField(F,B), F < 4.[1,B,1]
%(w2.1)
:~ onUncrowded(B), onField(F,B), F >= 4.[1,B,2]
%(w2.3)
:~ onLongSite(B), onField(F,B), not longSite(F).[1,B,3]
%(w2.4)
:~ onShortSite(B), onField(F,B), not shortSite(F).[1,B,4]

%3er wishes
%(w3.1)
:~ onCornerW(B,C), onField(F,B), onField(G,C), not corner(F,G).[1,B,C,5]
%(w3.2)
:~ onFrontW(B,C), onField(F,B), onField(G,C), not frontOf(F,G).[1,B,C,6]
%(w3.3)
:~ neighbourW(B,C), onField(F,B), onField(G,C), not neighbour(F,G).[1,B,C,7]

%4er
%(w4.1)
%2er copy
onCrowded(B) :- sameW(B,C), onCrowded(C).
onUncrowded(B) :- sameW(B,C), onUncrowded(C).
onLongSite(B) :- sameW(B,C), onLongSite(C).
onShortSite(B) :- sameW(B,C), onShortSite(C).
%3er copy
onCornerW(B,D) :- sameW(B,C), onCornerW(C,D).
onFrontW(B,D) :- sameW(B,C), onFrontW(C,D).
neighbourW(B,D) :- sameW(B,C), neighbourW(C,D).
%4er
onTwoW(B,D) :- sameW(B,C), onTwoW(C,D).
onNoSightW(B,D) :- sameW(B,C), onNoSightW(C,D).
%opposite copy
nonCornerW(B,D) :- sameW(B,C), nonCornerW(C,D).
nonFrontW(B,D) :- sameW(B,C), nonFrontW(C,D).
nneighbourW(B,D) :- sameW(B,C), nneighbourW(C,D).
nonTwoW(B,D) :- sameW(B,C), nonTwoW(C,D).
nonNoSightW(B,D) :- sameW(B,C), nonNoSightW(C,D).
%(w4.2)
:~ onTwoW(B,C), onField(F,B), onField(G,C), not threeAway(F,G).[1,B,C,8]
%(w4.3)
onSight(B,C) :- onField(F,B), onField(G,C), neighbour(F,G).
onSight(B,C) :- onField(F,B), onField(G,C), frontOf(F,G).
:~ onNoSightW(B,C), onSight(B,C).[1,B,C]

%5er
%2er copy
onUncrowded(B) :- oppositeW(B,C), onCrowded(C).
onCrowded(B) :- oppositeW(B,C), onUncrowded(C).
onShortSite(B) :- oppositeW(B,C), onLongSite(C).
onLongSite(B) :- oppositeW(B,C), onShortSite(C).
%3er copy
nonCornerW(B,D) :- oppositeW(B,C), onCornerW(C,D).
nonFrontW(B,D) :- oppositeW(B,C), onFrontW(C,D).
nneighbourW(B,D) :- oppositeW(B,C), neighbourW(C,D).
%4er copy
%oppositeW(B,D) :- oppositeW(B,C), sameW(C,D).
nonTwoW(B,D) :- oppositeW(B,C), onTwoW(C,D).
nonNoSightW(B,D) :- oppositeW(B,C), onNoSightW(C,D).
%3er n copy
onCornerW(B,D) :- oppositeW(B,C), nonCornerW(C,D).
onFrontW(B,D) :- oppositeW(B,C), nonFrontW(C,D).
neighbourW(B,D) :- oppositeW(B,C), nneighbourW(C,D).
%4er n copy
%oppositeW(B,D) :- oppositeW(B,C), sameW(C,D).
onTwoW(B,D) :- oppositeW(B,C), nonTwoW(C,D).
onNoSightW(B,D) :- oppositeW(B,C), nonNoSightW(C,D).

%missing opposite for 5er rule
%opposite(w3.1)
:~ nonCornerW(B,C), onField(F,B), onField(G,C), corner(F,G).[1,B,C,9]
%opposite(w3.2)
:~ nonFrontW(B,C), onField(F,B), onField(G,C), frontOf(F,G).[1,B,C,10]
%opposite(w3.3)
:~ nneighbourW(B,C), onField(F,B), onField(G,C), neighbour(F,G).[1,B,C,11]
%opposite(w4.2)
:~ nonTwoW(B,C), onField(F,B), onField(G,C), threeAway(F,G).[1,B,C,12]
%opposite(w4.3)
:~ nonNoSightW(B,C), not onSight(B,C).[1,B,C,13]

%help symmetrie
corner(G,F) :- corner(F,G).
frontOf(G,F) :- frontOf(F,G).
threeAway(G,F) :- threeAway(F,G).
%calculate neighbour fields
neighbour(F,G):- field(F),field(G), |F-G|=1, not corner(F,G).
