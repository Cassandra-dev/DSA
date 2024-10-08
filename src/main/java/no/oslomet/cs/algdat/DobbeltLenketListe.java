package no.oslomet.cs.algdat;

import java.util.*;

public class DobbeltLenketListe<T> implements Liste<T> {
    // Innebygd (Trenger ikke endres)


    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;
        private Node<T> forrige, neste;

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    private Node<T> hode;
    private Node<T> hale;
    private int antall;
    private int endringer;

    public void fraTilKontroll(int fra, int til) {
        if (fra < 0) throw new IndexOutOfBoundsException("fra(" + fra + ") er negativ.");
        if (til > antall) throw new IndexOutOfBoundsException("til(" + til + ") er større enn antall(" + antall + ")");
        if (fra > til)
            throw new IllegalArgumentException("fra(" + fra + ") er større enn til(" + til + ") - Ulovlig intervall.");
    }

    // Oppgave 0
    public static int gruppeMedlemmer() {
        return 1;
    }

    // Oppgave 1
    public DobbeltLenketListe() {
        hode = null;
        hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        //feilhåndtering til null tabell a
        Objects.requireNonNull(a, "Tabellen a er null!");
        //Looper gjennom tabellen a og legger inn verdiene i listen (v (Verdi)
        for (T v : a) {
            //sjekker om v er null
            if (v != null) {
                //om lista er tom, legges v inn som hode og hale
                if (hode == null) {
                    hode = hale = new Node<>(v);
                } else {
                    //ellers legges v inn som hale
                    hale = hale.neste = new Node<>(v, hale, null);
                }
                //øker antall
                antall++;
            }
        }
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    // Oppgave 2
    @Override
    public String toString() {
        //sjekker om listen er tom
        if (antall == 0) {
            return "[]";
        }
        //lager en StringBuilder
        StringBuilder s = new StringBuilder("[");
        //looper gjennom listen
        for (Node<T> current = hode; current != null; current = current.neste) {
            //sjekker om current.verdi er null
            if (current.verdi != null) {
                //legger til current.verdi i StringBuilder
                s.append(current.verdi);

                //sjekker om current.neste er null
                if (current.neste != null) {
                    //legger til ", " i StringBuilder
                    s.append(", ");
                }
            }
        }
        //legger til "]" i StringBuilder
        s.append("]");
        //returnerer som String
        return s.toString();
    }


    public String omvendtString() {
        //Akkurat det samme som toString, bare at den looper fra hale til hode
        //for å få omvendt rekkefølge.
        if (antall == 0) {
            return "[]";
        }
        StringBuilder s = new StringBuilder("[");
        for (Node<T> current = hale; current != null; current = current.forrige) {
            if (current.verdi != null) {
                s.append(current.verdi);

                if (current.forrige != null) {
                    s.append(", ");
                }
            }
        }
        s.append("]");
        return s.toString();

    }

    @Override
    public boolean leggInn(T verdi) {
        //feilhåndtering til null-verdier
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");
        //sjekker om listen er tom
        if (tom()) {
            //om den er tom, legges verdi inn som hode og hale
            hode = hale = new Node<>(verdi, null, null);
        } else {
            //ellers legges verdi inn som hale
            hale = hale.neste = new Node<>(verdi, hale, null);
        }
        //øker antall og endringer
        antall++;
        endringer++;
        //returnerer true
        return true;
    }

    // Oppgave 3
    private Node<T> finnNode(int indeks) {
        // Erklær en variabel for å holde gjeldende node.
        Node<T> current;
        // Sjekk om den angitte indeksen er i første halvdel av listen.
        if (indeks < (antall / 2)) {
            // Hvis ja, start fra toppen av listen.
            current = hode;
            // Iterer gjennom listen til du når den angitte indeksen.
            for (int i = 0; i < indeks; i++) {
                current = current.neste;
            }
        } else {
            // Hvis indeksen er i andre halvdel av listen, start fra halen.
            current = hale;
            // Iterer bakover gjennom listen til du når den angitte indeksen.
            for (int i = antall; i > indeks + 1; i--) {
                current = current.forrige;
            }
        }
        // Returner noden funnet ved den angitte indeksen.
        return current;
    }

    @Override
    public T hent(int indeks) {
        // Bekreft at indeksen er innenfor listens grenser.
        indeksKontroll(indeks, false);
        //finner noden som skal hentes
        Node<T> node = finnNode(indeks);
        //returnerer verdien til noden
        return node.verdi;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        // Sørg for at den nye verdien ikke er null; nullverdier er ikke tillatt.
        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");
        // Bekreft at indeksen er innenfor listens grenser.
        indeksKontroll(indeks, false);
        // Finn noden ved den angitte indeksen i listen.
        Node<T> node = finnNode(indeks);
        // Lagre den gamle verdien til noden for senere retur.
        T gammelVerdi = node.verdi;
        // Oppdater verdien til noden med den nye spesifiserte verdien.
        node.verdi = nyverdi;
        // Øk «endringer»-telleren for å indikere en endring i listen.
        endringer++;
        // Returner den gamle verdien som ble erstattet med den nye verdien.
        return gammelVerdi;

    }


    public Liste<T> subliste(int fra, int til) {
        //validerer indeksene for å sørge at de er i intervallet [0, antall]
        fraTilKontroll(fra, til);
        //lager en ny liste
        DobbeltLenketListe<T> sublist = new DobbeltLenketListe<>();

        //looper gjennom listen fra indeks fra til indeks til
        for (int i = fra; i < til; i++) {
            //legger til noden i sublistet
            Node<T> node = finnNode(i);

            sublist.leggInn(node.verdi);
        }
        //returnerer sublist
        return sublist;
    }

    // Oppgave 4
    @Override
    public int indeksTil(T verdi) {
        // Initialiser en referansevariabel 'current' til toppen av listen.
        Node<T> p = hode;
        // Gå gjennom listen for å finne indeksen til den angitte verdien.
        for (int i = 0; i < antall; i++) {
            // Sjekk om verdien til gjeldende node er lik den angitte verdien.
            if (p.verdi.equals(verdi)) {
                // Hvis funnet, returner gjeldende indeks.
                return i;
            }
            // Flytt til neste node i listen.
            p = p.neste;
        }
        // Hvis verdien ikke finnes i listen, returner -1 for å indikere at verdien ikke er til stede.
        return -1;
    }

    @Override
    public boolean inneholder(T verdi) {
        //sjekker om indeksTil(verdi) er -1 også returnerer true hvis den ikke er det
        return indeksTil(verdi) != -1;
    }

    // Oppgave 5
    @Override
    public void leggInn(int indeks, T verdi) {
        // Sørger for at den angitte verdien ikke er null, da nullverdier ikke er tillatt.
        Objects.requireNonNull(verdi, "Ikke tillatt med null-verdier!");
        // Sjekker om den angitte indeksen er innenfor gyldige grenser.
        indeksKontroll(indeks, true);
        // Sjekk om listen er tom.
        if (tom()) {
            // Hvis tom, lag en ny node med den angitte verdien og sett den som både hode og hale.
            hode = hale = new Node<>(verdi, null, null);
        } else if (indeks == 0) {
            // Hvis indeksen er i begynnelsen (0), sett inn en ny node med den angitte verdien som det nye hodet.
            hode = hode.forrige = new Node<>(verdi, null, hode);
        } else if (indeks == antall) {
            // Hvis indeksen er på slutten, sett inn en ny node med den angitte verdien som den nye halen.
            hale = hale.neste = new Node<>(verdi, hale, null);
        } else {
            // Hvis indeksen er i midten, finn noden ved den angitte indeksen.
            Node<T> node = finnNode(indeks);
            // Sett inn en ny node med den angitte verdien før den funnet noden.
            node.forrige = node.forrige.neste = new Node<>(verdi, node.forrige, node);
        }
        // Øk antallet elementer i listen.
        antall++;
        // Øk antallet endringer for å indikere en endring i listen
        endringer++;
    }

    // Oppgave 6
    @Override
    public T fjern(int indeks) {
        // Sørg for at den angitte indeksen er innenfor gyldige grenser.
        indeksKontroll(indeks, false);
        // Variabel for å lagre verdien til den fjernede noden.
        T verdi;
        // Sjekk om indeksen er i begynnelsen av listen (indeks 0).
        if (indeks == 0) {
            // Få verdien av hode-noden.
            verdi = hode.verdi;
            // Sjekk om det bare er ett element i listen.
            // Hvis ja, sett både hode og hale til null.
            if (antall == 1) hode = hale = null;
            // Hvis det er mer enn ett element, flytt hodet til neste node og oppdater referansene.
            else {
                hode = hode.neste;
                hode.forrige = null;
            }

        } else {
            // Hvis indeksen ikke er i begynnelsen, finn noden før den angitte indeksen.
            Node<T> p = finnNode(indeks - 1);
            // Få noden som skal fjernes (den ved den angitte indeksen).
            Node<T> s = p.neste;
            // Få verdien til noden som skal fjernes.
            verdi = s.verdi;
            // Sjekk om noden som skal fjernes er halen på listen.
            if (s == hale) {
                // Hvis ja, flytt halen til forrige node og oppdater referansene.
                hale = p;
                hale.neste = null;
                hale.forrige = p.forrige;
            } else {
                // Hvis noden som skal fjernes ikke er halen, oppdater referansene for å hoppe over noden som skal fjernes
                Node<T> nnNode = s.neste;
                nnNode.forrige = p;
                p.neste = nnNode;
            }
        }
        // Reduser antallet elementer i listen.
        antall--;
        // Øk antallet endringer for å indikere en endring i listen.
        endringer++;
        // Returner verdien til den fjernede noden.
        return verdi;

    }


    @Override
    public boolean fjern(T verdi) {
        // Sjekk om inngangsverdien som skal fjernes er null. I så fall returnerer du false da nullverdier ikke er tillatt.
        if (verdi == null) {
            return false;
        }
        // Lag en referanse til hode av listen.
        Node<T> current = hode;
        // Iterer gjennom hvert element i listen.
        for (int i = 0; i < antall; i++) {
            // Sjekk om gjeldende nodes verdi er lik verdien som skal fjernes.
            if (current.verdi.equals(verdi)) {
                // Sjekk om gjeldende node er toppen av listen.
                if (current == hode) {
                    // Sjekk om det bare er ett element i listen. Sett i så fall både hode og hale til null.
                    if (antall == 1) {
                        hode = hale = null;
                    } else {
                        // Hvis det er mer enn ett element, flytt hodet til neste node og oppdater referansene.
                        hode = hode.neste;
                        hode.forrige = null;
                    }
                } else {
                    // Hvis ja, flytt halen til forrige node og oppdater referansene.
                    if (current == hale) {
                        hale = hale.forrige;
                        hale.neste = null;

                        // Hvis gjeldende node er i midten, oppdater referanser for å hoppe over gjeldende node.
                    } else {
                        current.forrige.neste = current.neste;
                        current.neste.forrige = current.forrige;
                    }
                }
                // Øk antallet endringer for å indikere en endring i listen.
                endringer++;
                // Reduser antallet elementer i listen.
                antall--;
                // Returner true for å indikere vellykket fjerning
                return true;
            }
            // Flytt til neste node i iterasjonen.
            current = current.neste;
        }
        // Hvis løkken fullføres uten å finne verdien, returner false siden den ikke ble funnet i listen.
        return false;
    }


    // Oppgave 7
    @Override
    public void nullstill() {
        // Sjekk om listen allerede er tom. I så fall er det ingenting å tilbakestille, så kom tilbake.
        if (tom()) {
            return;
        }
        // Lag en referanse til toppen av listen.
        Node<T> ref = hode;
        // Iterer gjennom hvert element i listen.
        for (int i = 0; i < antall; i++) {
            // Sett verdien av gjeldende node til null.
            ref.verdi = null;
            // Flytt referansen til neste node i listen
            ref = ref.neste;
        }
        // Etter å ha satt alle verdier til null, tilbakestill hodet og halen til null
        hode = hale = null;
        // Tilbakestill antallet elementer i listen til null
        antall = 0;
        // Øk antallet endringer for å indikere en endring i listen.
        endringer++;
    }

    // Oppgave 8
    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean kanFjerne;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;                   // Starter på første i lista
            kanFjerne = false;              // Settes true når next() kalles
            iteratorendringer = endringer;  // Teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            //sjekker om indeks er 0
            if (indeks < 0) {
                throw new IndexOutOfBoundsException("Indeks: " + indeks + ", Antall: " + antall);
            }
            //setter denne til finnNode(indeks)
            denne = finnNode(indeks);
            //setter kanFjerne til false
            kanFjerne = false;
            //setter iteratorendringer til endringer
            iteratorendringer = endringer;

        }


        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            // Se etter samtidig modifikasjon. Hvis listen har blitt endret utenfor iteratoren, kast et unntak
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException("iteratorendringer er ikke lik endringer");
            }
            // Sjekk om det ikke er flere elementer i iterasjonen. Hvis ja, kast en NoSuchElementException.
            if (!hasNext()) throw new NoSuchElementException("Ingen verdier!");
            // Sett fjerningsflagget til true, noe som indikerer at det er tillatt å fjerne det gjeldende elementet.
            kanFjerne = true;
            // Lagre verdien til gjeldende node før du går til neste node i iterasjonen.
            T verdi = denne.verdi;
            // Flytt iteratoren til neste node i iterasjonen.
            denne = denne.neste;
            // Returner verdien til gjeldende node som ble lagret tidligere.
            return verdi;

        }

        // Oppgave 9:
        @Override
        public void remove() {
            //Sett opp en referanse til forrige node (p)
            Node<T> p = (denne == null ? hale : denne.forrige);
            // Se etter samtidig modifikasjon. Hvis listen har blitt endret utenfor iteratoren, kast et unntak.
            if(iteratorendringer != endringer) throw new ConcurrentModificationException("iteratorendringer er ikke lik endringer");
            // Sjekk om fjerning er tillatt. Hvis ikke, kast et unntak.
            if(!kanFjerne) throw new IllegalStateException("Kan ikke fjerne");
            // Indiker at fjerning ikke er tillatt før neste element er hentet.
            kanFjerne = false;
            // Håndtaksfjerning basert på posisjonen til referansen s.
            if(p == hode) {
                // Hvis p peker mot hodet.
                if (antall == 1) {
                    // Hvis det bare er ett element, tøm listen.
                    hode = hale = null;
                } else {
                    // Hvis det er mer enn ett element, flytt hodet til neste node.
                    hode = hode.neste;
                    hode.forrige = null;
                }
            }
                else if (p == hale) {
                // Hvis p peker mot halen, flytt halen til forrige node.
                    hale = hale.forrige;
                    hale.neste = null;
                } else {
                // Hvis p er i midten, koble nodene før og etter p til hverandre.
                    p.forrige.neste = p.neste;
                    p.neste.forrige = p.forrige;
                }
                // Rydd opp ved å sette verdien til den fjernede noden til null.
                p.verdi = null;

                // Oppdater antall og endringer for å gjenspeile fjerningen.
                antall--;
                endringer++;
                iteratorendringer++;

                }
            }
        public Iterator<T> iterator() {
            //Iterator som også brukes til oppgave 8?
            return new DobbeltLenketListeIterator();
        }

        public Iterator<T> iterator(int indeks) {
            indeksKontroll(indeks, false);
            return new DobbeltLenketListeIterator(indeks);
        }


        // Oppgave 10
        public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        // Sjekk om listen er tom; i så fall er det allerede sortert.
        if(liste.tom()) return;
        // Iterer gjennom hvert element i listen.
        for(int i = 0; i<liste.antall(); i++){
            // For hvert element, sammenligne det med alle andre elementer i listen.
            for(int j = 0; j < liste.antall(); j++){
                // Sammenlign elementene ved å bruke den medfølgende komparatoren.
                if((c.compare(liste.hent(i), liste.hent(j)) < 0)){
                    // Hvis sammenligningsresultatet er mindre enn 0, bytt elementene.
                    T temp = liste.hent(i);
                    liste.oppdater(i, liste.hent(j));
                    liste.oppdater(j, temp);
                    }
                }
            }
        }

    }


