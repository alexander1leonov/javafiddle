package Entities;

/**
 * Created by Fedor on 18.11.2015.
 */
import javax.persistence.*;

@Entity
@Table

public class Hashes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    @OneToOne
    private Project project;
    private String hash;

    public Hashes(Project project, String hash) {
        this.project = project;
        this.hash = hash;
    }

    public Hashes() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "Hashes{" +
                "ID=" + ID +
                ", project=" + project.getProject_id() +
                ", hash='" + hash +
                '}';
    }
}