package maurotuzzolino;

public class GiocoNonTrovatoException extends RuntimeException {
    public GiocoNonTrovatoException(String id) {
        super("Nessun gioco trovato con ID '" + id + "'.");
    }
}
