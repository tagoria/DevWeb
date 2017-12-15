package projet.dev.web.Entities;

public final class Streamer {
    private String description;
    private int id;
    private String cheminImage;
    private String nom;

    public Streamer(int id , String description, String cheminImage,String nom){
        this.description=description;
        this.id=id;
        this.cheminImage=cheminImage;
        this.nom=nom;
    }
    public Streamer(){

    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int anInt) {
        if (anInt > 0) {
            id = anInt;
        }
    }
}
