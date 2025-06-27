package maurotuzzolino;

public class GenereNonValidoException extends RuntimeException {
    public GenereNonValidoException() {
        System.out.println("Genere non valido (AZIONE, AVVENTURA, STRATEGIA, SPORT, SIMULAZIONE, HORROR, RPG)");
    }
}
