package group3.griddie.networkOLD;

public class NetworkState {
    private static boolean state;

    public NetworkState(boolean state){
        this.state = state;
    }

    public static void setState(boolean state) {
        NetworkState.state = state;
    }

    public static boolean getState() {
        return state;
    }
}
