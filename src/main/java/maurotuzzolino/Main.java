package maurotuzzolino;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Collezione collezione = new Collezione();
        boolean running = true;

        while (running) {
            System.out.println("\n--- GESTIONE COLLEZIONE GIOCHI ---");
            System.out.println("1. Aggiungi gioco");
            System.out.println("2. Cerca gioco per ID");
            System.out.println("3. Cerca giochi per prezzo massimo");
            System.out.println("4. Cerca giochi da tavolo per numero di giocatori");
            System.out.println("5. Rimuovi gioco per ID");
            System.out.println("6. Aggiorna gioco per ID");
            System.out.println("7. Visualizza statistiche");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            int scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 1 -> {
                    System.out.println("Videogioco (V) o Gioco da tavolo(T)?");
                    String tipo = scanner.nextLine().toUpperCase();

                    System.out.println("ID: ");
                    String id = scanner.nextLine();

                    System.out.println("Titolo: ");
                    String titolo = scanner.nextLine();

                    System.out.println("Anno pubblicazione: ");
                    int annoPubblicazione = Integer.parseInt(scanner.nextLine());

                    System.out.println("Prezzo: ");
                    double prezzo = Double.parseDouble(scanner.nextLine());

                    if (tipo.equals("V")) {
                        System.out.println("Piattaforma: ");
                        String piattaforma = scanner.nextLine();

                        System.out.println("Durata di gioco (in ore): ");
                        double durata = Double.parseDouble(scanner.nextLine());

                        System.out.println("Genere (AZIONE, AVVENTURA, STRATEGIA, SPORT, SIMULAZIONE, HORROR, RPG): ");
                        Genere genere = Genere.valueOf(scanner.nextLine().toUpperCase());

                        collezione.aggiungiGioco(new Videogioco(id, titolo, annoPubblicazione, prezzo, piattaforma, durata, genere));
                    } else if (tipo.equals("T")) {
                        System.out.println("Numero di giocatori (2-10): ");
                        int numeroGiocatori = Integer.parseInt(scanner.nextLine());

                        System.out.println("Durata media partita (in minuti): ");
                        int durataMedia = Integer.parseInt(scanner.nextLine());

                        collezione.aggiungiGioco(new GiocoDaTavolo(id, titolo, annoPubblicazione, prezzo, numeroGiocatori, durataMedia));
                    } else {
                        System.out.println("Tipo non riconosciuto");
                    }
                }

                case 2 -> {
                    System.out.println("Inserisci ID da cercare: ");
                    String id = scanner.nextLine();
                    Gioco g = collezione.cercaPerId(id);
                    if (g != null) {
                        System.out.println(g.getDettagli());
                    } else {
                        System.out.println("Nessun gioco trovato con questo ID.");
                    }
                }

                case 3 -> {
                    System.out.println("Inserisci prezzo massimo: ");
                    double prezzo = Double.parseDouble(scanner.nextLine());
                    collezione.cercaPerPrezzo(prezzo).forEach(gioco -> System.out.println(gioco.getDettagli()));
                }

                case 4 -> {
                    System.out.println("Inserisci numero di giocatori: ");
                    int numero = Integer.parseInt(scanner.nextLine());
                    collezione.cercaPerNumeroDiGiocatori(numero).forEach(g -> System.out.println(g.getDettagli()));
                }

                case 5 -> {
                    System.out.println("Inserisci ID da rimuovere: ");
                    String id = scanner.nextLine();
                    if (collezione.rimuoviGioco(id)) {
                        System.out.println("Gioco rimosso");
                    } else {
                        System.out.println("ID non trovato.");
                    }
                }

                case 6 -> {
                    System.out.println("Inserisci ID del gioco da aggiornare: ");
                    String id = scanner.nextLine();

                    if (collezione.cercaPerId(id) == null) {
                        System.out.println("Gioco non trovato.");
                        break;
                    }

                    System.out.println("Inserisci i nuovi dati: ");
                    System.out.println("Titolo: ");
                    String titolo = scanner.nextLine();
                    System.out.println("Anno pubblicazione: ");
                    int annoPubblicazione = Integer.parseInt(scanner.nextLine());
                    System.out.println("Prezzo: ");
                    double prezzo = Double.parseDouble(scanner.nextLine());

                    Gioco vecchio = collezione.cercaPerId(id);
                    if (vecchio instanceof Videogioco) {
                        System.out.print("Piattaforma: ");
                        String piattaforma = scanner.nextLine();
                        System.out.print("Durata di gioco: ");
                        double durata = Double.parseDouble(scanner.nextLine());
                        System.out.print("Genere: ");
                        Genere genere = Genere.valueOf(scanner.nextLine().toUpperCase());

                        collezione.aggiornaGioco(id, new Videogioco(id, titolo, annoPubblicazione, prezzo, piattaforma, durata, genere));
                    } else if (vecchio instanceof GiocoDaTavolo) {
                        System.out.print("Numero giocatori: ");
                        int giocatori = Integer.parseInt(scanner.nextLine());
                        System.out.print("Durata media (minuti): ");
                        int durata = Integer.parseInt(scanner.nextLine());

                        collezione.aggiornaGioco(id, new GiocoDaTavolo(id, titolo, annoPubblicazione, prezzo, giocatori, durata));
                    }
                }

                case 7 -> collezione.stampaStatistiche();

                case 0 -> {
                    System.out.println("Chiusura programma...");
                    running = false;
                }

                default -> System.out.println("Scelta non valida");
            }
        }

        scanner.close();
    }
}
