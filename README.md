[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/k3TSQYDa)
# Obligatorisk Oppgave 2 i DATS2300 - Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i DATS2300 - Algoritmer og datastrukturer. Den er innlevert av følgende studenter:
* s374201@oslomet.no 


## Arbeidsfordeling
I oppgaven har vi hatt følgende arbeidsfordeling:
* s374201 gjorde alle oppgavene, selvom jeg returnerer 1 på oppgave 0, så skal oppgave 7,9 og 10 fungere som de skal(har testet ved returnere større nummer).

## Oppgavebeskrivelser
.
# Oppgave 1
denne koden lager en spesiell liste fra en haug med ting, 
og hopper over ting som er ingenting (null). 
Listen holder orden på tingenes rekkefølge, som en kjede av magiske bokser.
Hver boks har noe spesielt inni (en ting),
og veiviseren oppdaterer deg om hvor mange ting du har på den magiske listen din.
Hvis du har en boks med ingenting i, så er det ikke en boks, og du har ikke den boksen.
Hvis du har en boks med en ting i, så har du en ting, og du har den tingen. :D

# Oppgave 2
toString() metoden lager en streng som inneholder alle tingene i listen din,
denne koden hjelper den vennlige hjelperen din med å vise deg en fin liste over favoritt tingene dine, 
og hopper over alle som er ingenting (null).
Det er som å legge favorittlekene dine i en kurv og vise dem én etter én.

leggInn() metoden legger til en ting i listen din,
denne koden hjelper den vennlige hjelperen din å legge til en ny favoritt ting til den magiske listen din,
enten å gjøre den til den første og siste tingen eller legge den til på slutten,
avhengig av om listen din er tom eller ikke. Og hjelperen holder selvfølgelig styr 
på hvor mange ting du har på listen din og hvor mange ganger du har gjort endringer.
Det er som et lite målkort for listen din!

# Oppgave 3
koden i metoden finnNode() hjelper en vennlig "Helper" med å finne en av "favoritt" tingene i listen din,
ved å bruke en hjelpevariabel som holder styr på hvor mange ting du har på listen din,

koden i metoden hent()  hjelper en vennlig "Helper" med å få en av "favoritt" tingene dine fra et bestemt
sted i listen din, den sjekker om stedet er i orden, finner den spesielle boksen(Noden) på det stedet,
og gir deg favoritt tingen din som er inni.

koden i metoden hjelper en vennlig hjelper med å oppdatere en av favoritttingene dine i den magiske listen din. 
Den sørger for at den nye tingen er bra, sjekker om stedet er i orden, 
oppdaterer listen, holder styr på endringen og gir deg tilbake den gamle favoritttingen.

koden i metoden definerer denne koden definerer en metode som oppretter en underliste av en dobbeltlenket liste 
ved å iterere gjennom et spesifisert utvalg av indekser, trekke ut de tilsvarende nodene
fra den opprinnelige listen og legge til verdiene deres til en ny underliste. Underlisten returneres deretter.
(Det er klokka 01:00 på en lørdag ;) )

# Oppgave 4

Koden i metoden indeksTil() søker etter en bestemt verdi (verdi) i en koblet liste. 
Den itererer gjennom listen, sammenligner verdiene i hver node med input verdien, 
og returnerer indeksen for den første forekomsten hvis den blir funnet.
Hvis verdien ikke blir funnet, returnerer den -1.

# Oppgave 5
Denne koden er som en oppskrift for å legge til noe i en dynamisk liste.
Se for deg listen som en samling av elementer, og du prøver å sette inn et nytt element på et bestemt sted
Så, i et nøtteskall, er denne koden et sett med instruksjoner
for å legge til et nytt element i en liste, sørge for at alt er på rett plass og holde styr på endringene.

# Oppgave 6
Metodene er som intruksjoner for å fjerne noe fra listen. enten du kjenner posisjonen eller vet 
hvordan tingen ser ut(verdi). Målet er å holde listen organisert etter å ha fjernet noe.

I metoden fjern(int indeks) håndterer fjerning av et element ved en gitt indeks.
Den sjekker om indeksen er 0, og fjerner i så fall elementet fra toppen av listen. 
Ellers finner den noden ved den angitte indeksen, justerer pekerne for å ekskludere den noden, 
og oppdaterer hode- eller halepekerne tilsvarende. 
Metoden reduserer deretter antallet elementer og øker antallet modifikasjoner.

I metoden fjern(T verdi) fjernet et element etter verdien. Den iterer gjennom listen,
finner noden med den angitte verdien og fjerner den ved å justere pekerne.
Metoden oppdaterer også hode- eller halepekerne hvis noden som fjernes, 
er i hver ende av listen. I likhet med den forrige metoden øker den antallet modifikasjoner og reduserer 
antallet elementer.

# Oppgave 7
metoden nullstill(), fjerner alle elementene fra listen din, og setter antallet modifikasjoner til 0.
selvsagt fra metodens navn, som nullstilles listen din. 
# Oppgave 8
Metodene next(), iterator(), DobbeltLenketListeIterator() og iterator(indeks) bidrar til iterasjonsmulighetene
til den dobbeltlenkede liste, og gir en måte å gå gjenom elementene og iterator på en dynamisk liste. og håndtere
samtidge modifikasjoner. Iteratoren støtter både generell iterasjon iterator() 
og fra en spesifikk indeks iterator(indeks).

# Oppgave 9
Metoden remove() fungerer som en "assistent" i å administrere listen din, slik at du selektivt kan ta
ut gjenstander når de er klare. det innebærer å sjekke først om det er lov å ta det ut med next() metoden.
og hvis det er lov, så tar den ut gjenstanden og holder orden på endringene.

# Oppgave 10
Metoden sorter() er en hjelpemetode som ordner en samling av elementer i en liste. Hvis samlingen er tom, 
gjør den ingenting. ellers sammenlikner den hvert element med de andre, ved å bruke compareTo() metoden,
det er for å bestemme hva som skal komme først altså ordner det i en stiggende rekkefølge. hvis den finner 
to elementer i feil rekkefølge, bytter dem rundt. Denne prosessen gjentas til listen er sortert. 

