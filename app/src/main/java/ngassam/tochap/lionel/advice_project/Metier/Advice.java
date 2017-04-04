package ngassam.tochap.lionel.advice_project.Metier;

/**
 * Created by tochl on 01/04/2017.
 */

public class Advice {

    private int id;
    private String title;
    private String description;
    private int note;
    private String auteur;
    private String categorie;
    private double longitude;
    private double latitude;


    public Advice() {
        super();
    }

    public Advice(String title, String description, int note, String auteur, String categorie) {
        this.title = title;
        this.description = description;
        this.note = note;
        this.auteur = auteur;
        this.categorie = categorie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Advice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", note=" + note +
                ", auteur='" + auteur + '\'' +
                ", categorie='" + categorie + '\'' +
                '}';
    }
}
