package dev.henrybarreto.hardcoreplus.state;

public class ServerState {
    protected static int deaths = 0;

    public ServerState() {
    }

    public static int getDeaths() {
        return deaths;
    }
    public static void setDeaths(int deathsNumber) {
        deaths = deathsNumber;
   }
}
