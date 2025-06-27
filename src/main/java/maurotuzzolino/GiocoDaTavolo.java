package maurotuzzolino;

public class GiocoDaTavolo extends Gioco {
    int numeroGiocatori;
    int durataMedia;

    public GiocoDaTavolo(String id, String titolo, int annoPubblicazione, double prezzo, int numeroGiocatori, int durataMedia) {
        super(id, titolo, annoPubblicazione, prezzo);
        this.numeroGiocatori = numeroGiocatori;
        this.durataMedia = durataMedia;
    }

    public int getNumeroGiocatori() {
        return numeroGiocatori;
    }

    public void setNumeroGiocatori(int numeroGiocatori) {
        this.numeroGiocatori = numeroGiocatori;
    }

    public int getDurataMedia() {
        return durataMedia;
    }

    public void setDurataMedia(int durataMedia) {
        this.durataMedia = durataMedia;
    }

    @Override
    public String getDettagli() {
        return "Gioco da Tavolo: " + titolo + ", Giocatori: " + numeroGiocatori + ", Durata media: " + durataMedia + " min";
    }
}
