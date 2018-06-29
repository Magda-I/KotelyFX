package model;

public class Kotel {

    private String name;
    private String URL;
    private int votes;

    public Kotel() {
    }

    public Kotel(String name, String URL, int votes) {
        this.name = name;
        this.URL = URL;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
