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

    public String getPiattaforma() {
        return piattaforma;
    }

    public void setPiattaforma(String piattaforma) {
        this.piattaforma = piattaforma;
    }

    public double getDurataGioco() {
        return durataGioco;
    }

    public void setDurataGioco(double durataGioco) {
        this.durataGioco = durataGioco;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    @Override
    public String getDettagli() {
        return "Videogioco: " + titolo + " (" + piattaforma + "), Genere: " + genere + ", Durata: " + durataGioco + "h";
    }
}
