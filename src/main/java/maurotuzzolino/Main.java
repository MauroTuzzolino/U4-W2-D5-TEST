package maurotuzzolino;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Collezione collezione = new Collezione();

        try {
            collezione.aggiungiGioco(new Videogioco("V1", "The Witcher 3", 2015, 19.99, "PC", 70.0, Genere.RPG));
            collezione.aggiungiGioco(new Videogioco("V2", "FIFA 23", 2022, 49.99, "PS5", 30.0, Genere.SPORT));
            collezione.aggiungiGioco(new Videogioco("V3", "Resident Evil Village", 2021, 39.99, "Xbox", 15.0, Genere.HORROR));

            collezione.aggiungiGioco(new GiocoDaTavolo("T1", "Catan", 1995, 34.99, 4, 90));
            collezione.aggiungiGioco(new GiocoDaTavolo("T2", "Ticket to Ride", 2004, 29.99, 5, 60));
            collezione.aggiungiGioco(new GiocoDaTavolo("T3", "7 Wonders", 2010, 24.99, 7, 40));

            System.out.println("Giochi statici caricati con successo.");
        } catch (GiocoGiaEsistenteException e) {
            System.out.println("Errore durante il caricamento iniziale: " + e.getMessage());
        }

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

            int scelta;
            try {
                scelta = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                throw new FormatoInputNonValidoException();
            }

            switch (scelta) {
                case 1 -> {
                    System.out.println("Videogioco (V) o Gioco da tavolo(T)?");
                    String tipo = scanner.nextLine().toUpperCase();

                    System.out.println("ID: ");
                    String id = scanner.nextLine();

                    System.out.println("Titolo: ");
                    String titolo = scanner.nextLine();

                    int annoPubblicazione;
                    double prezzo;
                    try {
                        System.out.println("Anno pubblicazione: ");
                        annoPubblicazione = Integer.parseInt(scanner.nextLine());

                        System.out.println("Prezzo: ");
                        prezzo = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        throw new FormatoInputNonValidoException();
                    }

                    if (tipo.equals("V")) {
                        System.out.println("Piattaforma: ");
                        String piattaforma = scanner.nextLine();

                        double durata;
                        try {
                            System.out.println("Durata di gioco (in ore): ");
                            durata = Double.parseDouble(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            throw new FormatoInputNonValidoException();
                        }

                        System.out.println("Genere (AZIONE, AVVENTURA, STRATEGIA, SPORT, SIMULAZIONE, HORROR, RPG): ");
                        String inputGenere = scanner.nextLine().toUpperCase();
                        Genere genere;
                        try {
                            genere = Genere.valueOf(inputGenere);
                        } catch (IllegalArgumentException e) {
                            throw new GenereNonValidoException();
                        }

                        try {
                            collezione.aggiungiGioco(new Videogioco(id, titolo, annoPubblicazione, prezzo, piattaforma, durata, genere));
                            System.out.println("Gioco aggiunto con successo");
                        } catch (GiocoGiaEsistenteException e) {
                            System.out.println(e.getMessage());
                        }

                    } else if (tipo.equals("T")) {
                        int numeroGiocatori, durataMedia;
                        try {
                            System.out.println("Numero di giocatori (2-10): ");
                            numeroGiocatori = Integer.parseInt(scanner.nextLine());

                            System.out.println("Durata media partita (in minuti): ");
                            durataMedia = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            throw new FormatoInputNonValidoException();
                        }

                        try {
                            collezione.aggiungiGioco(new GiocoDaTavolo(id, titolo, annoPubblicazione, prezzo, numeroGiocatori, durataMedia));
                            System.out.println("Gioco aggiunto con successo");
                        } catch (GiocoGiaEsistenteException e) {
                            System.out.println(e.getMessage());
                        }

                    } else {
                        throw new InputNonValidoException();
                    }
                }

                case 2 -> {
                    System.out.println("Inserisci ID da cercare: ");
                    String id = scanner.nextLine();

                    try {
                        Gioco g = collezione.cercaPerId(id);
                        System.out.println(g.getDettagli());
                    } catch (GiocoNonTrovatoException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {
                    System.out.println("Inserisci prezzo massimo: ");
                    try {
                        double prezzoMax = Double.parseDouble(scanner.nextLine());
                        collezione.cercaPerPrezzo(prezzoMax).forEach(gioco -> System.out.println(gioco.getDettagli()));
                    } catch (NumberFormatException e) {
                        throw new FormatoInputNonValidoException();
                    }
                }

                case 4 -> {
                    System.out.println("Inserisci numero di giocatori: ");
                    try {
                        int numero = Integer.parseInt(scanner.nextLine());
                        collezione.cercaPerNumeroDiGiocatori(numero).forEach(g -> System.out.println(g.getDettagli()));
                    } catch (NumberFormatException e) {
                        throw new FormatoInputNonValidoException();
                    }
                }

                case 5 -> {
                    System.out.println("Inserisci ID da rimuovere: ");
                    String id = scanner.nextLine();
                    try {
                        collezione.rimuoviGioco(id);
                        System.out.println("Gioco rimosso.");
                    } catch (GiocoNonTrovatoException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 6 -> {
                    System.out.println("Inserisci ID del gioco da aggiornare: ");
                    String id = scanner.nextLine();

                    Gioco vecchio;
                    try {
                        vecchio = collezione.cercaPerId(id);
                    } catch (GiocoNonTrovatoException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    System.out.println("Inserisci i nuovi dati: ");
                    System.out.println("Titolo: ");
                    String titolo = scanner.nextLine();
                    int annoPubblicazione;
                    double prezzo;
                    try {
                        System.out.println("Anno pubblicazione: ");
                        annoPubblicazione = Integer.parseInt(scanner.nextLine());
                        System.out.println("Prezzo: ");
                        prezzo = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        throw new FormatoInputNonValidoException();
                    }

                    if (vecchio instanceof Videogioco) {
                        System.out.print("Piattaforma: ");
                        String piattaforma = scanner.nextLine();
                        double durata;
                        try {
                            System.out.print("Durata di gioco: ");
                            durata = Double.parseDouble(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            throw new FormatoInputNonValidoException();
                        }

                        System.out.print("Genere: ");
                        String inputGenere = scanner.nextLine().toUpperCase();
                        Genere genere;
                        try {
                            genere = Genere.valueOf(inputGenere);
                        } catch (IllegalArgumentException e) {
                            throw new GenereNonValidoException();
                        }

                        try {
                            collezione.aggiornaGioco(id, new Videogioco(id, titolo, annoPubblicazione, prezzo, piattaforma, durata, genere));
                            System.out.println("Gioco aggiornato.");
                        } catch (GiocoNonTrovatoException e) {
                            System.out.println(e.getMessage());
                        }

                    } else if (vecchio instanceof GiocoDaTavolo) {
                        try {
                            System.out.print("Numero giocatori: ");
                            int giocatori = Integer.parseInt(scanner.nextLine());
                            System.out.print("Durata media (minuti): ");
                            int durata = Integer.parseInt(scanner.nextLine());

                            collezione.aggiornaGioco(id, new GiocoDaTavolo(id, titolo, annoPubblicazione, prezzo, giocatori, durata));
                            System.out.println("Gioco aggiornato.");
                        } catch (NumberFormatException e) {
                            throw new FormatoInputNonValidoException();
                        } catch (GiocoNonTrovatoException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }

                case 7 -> collezione.stampaStatistiche();

                case 0 -> {
                    System.out.println("Chiusura programma...");
                    running = false;
                }

                default -> throw new InputNonValidoException();
            }
        }

        scanner.close();
    }
}
