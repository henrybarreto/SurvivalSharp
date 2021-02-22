package dev.henrybarreto.hardcoreplus.state;

public class ServerState {
    protected static int deaths = 0;

    public ServerState() {
    }

    public int getDeaths() {
        return deaths;
    }
    public void setDeaths(int deathsNumber) {
        deaths = deathsNumber;
   }
}
