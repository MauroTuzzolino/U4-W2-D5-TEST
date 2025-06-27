package maurotuzzolino;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
}
