package maurotuzzolino;

public class Videogioco extends Gioco {
    private String piattaforma;
    private double durataGioco;
    private Genere genere;

    public Videogioco(String id, String titolo, int annoPubblicazione, double prezzo, String piattaforma, double durataGioco, Genere genere) {
        super(id, titolo, annoPubblicazione, prezzo);
        this.piattaforma = piattaforma;
        this.durataGioco = durataGioco;
        this.genere = genere;
    }
}
