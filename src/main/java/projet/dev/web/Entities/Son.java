package projet.dev.web.Entities;

import java.time.LocalDate;

public final class Son {
    private String description;
    private int id;
    private LocalDate date;
    private String cheminSon;
    private Streamer streamer;
    private boolean supprime;

    public Son(String description, int id, LocalDate date, String cheminSon, Streamer streamer,boolean supprime) {
        this.description=description;
        this.id=id;
        this.date=date;
        this.cheminSon=cheminSon;
        this.streamer=streamer;
    }
    public Streamer getStreamer(){
        return streamer;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getCheminSon() {
        return cheminSon;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public boolean isSupprime() {
        return supprime;
    }

    public void setChemin(String chemin) {
        this.cheminSon = chemin;
    }
}
