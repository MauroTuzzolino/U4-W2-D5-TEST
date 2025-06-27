package maurotuzzolino;

import java.util.*;
import java.util.stream.Collectors;

public class Collezione {
    private Map<String, Gioco> giochi = new HashMap<>();

    //metodo di aggiunta
    public void aggiungiGioco(Gioco gioco) {
        if (giochi.containsKey(gioco.getId())) {
            System.out.println("Gioco con ID già esistente");
            return;
        }
        giochi.put(gioco.getId(), gioco);
        System.out.println("Gioco aggiunto con successo");
    }

    //metodo per la ricerca tramite ID
    public Gioco cercaPerId(String id) {
        return giochi.get(id);
    }

    //metodo per la ricerca tramite prezzo
    public List<Gioco> cercaPerPrezzo(double prezzoMax) {
        return giochi.values()
                .stream()
                .filter(g -> g.getPrezzo() < prezzoMax)
                .collect(Collectors.toList());
    }

    //metodo per la ricerca tramite numero di giocatori
    public List<GiocoDaTavolo> cercaPerNumeroDiGiocatori(int numero) {
        return giochi.values()
                .stream()
                .filter(g -> g instanceof GiocoDaTavolo)
                .map(g -> (GiocoDaTavolo) g)
                .filter(gdt -> gdt.getNumeroGiocatori() == numero)
                .collect(Collectors.toList());
    }

    //metodo di rimozione
    public boolean rimuoviGioco(String id) {
        return giochi.remove(id) != null;
    }

    //metodo di aggiornamento
    public void aggiornaGioco(String id, Gioco nuovoGioco) {
        if (!giochi.containsKey(id)) {
            System.out.println("Errore: nessun gioco con ID " + id + " trovato.");
            return;
        }
        giochi.put(id, nuovoGioco);
        System.out.println("Gioco con ID " + id + " aggiornato con successo.");
    }

    //metodo per la stampa delle statistiche
    public void stampaStatistiche() {
        long totale = giochi.size();
        long nVIdeogiochi = giochi.values()
                .stream()
                .filter(g -> g instanceof Videogioco)
                .count();
        long nTavolo = totale - nVIdeogiochi;

        Optional<Gioco> maxPrezzo = giochi.values()
                .stream()
                .max(Comparator.comparingDouble(Gioco::getPrezzo));

        OptionalDouble mediaPrezzi = giochi.values()
                .stream()
                .mapToDouble(Gioco::getPrezzo)
                .average();

        System.out.println("Totale giochi: " + totale);
        System.out.println("Videogiochi: " + nVIdeogiochi);
        System.out.println("Giochi da tavolo: " + nTavolo);
        System.out.println("Gioco più costoso: " + maxPrezzo);
        System.out.println("Prezzo medio: " + mediaPrezzi);
    }
}
