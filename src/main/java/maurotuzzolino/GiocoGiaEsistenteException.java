package maurotuzzolino;

public class GiocoGiaEsistenteException extends RuntimeException {
    public GiocoGiaEsistenteException(String id) {
        super("Un gioco con ID '" + id + "' esiste già.");
    }
}
