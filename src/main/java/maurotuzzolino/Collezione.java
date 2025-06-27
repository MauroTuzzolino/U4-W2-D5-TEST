package maurotuzzolino;

import java.util.HashMap;
import java.util.Map;

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
}
