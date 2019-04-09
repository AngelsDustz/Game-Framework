package group3.griddie.network.commands;

import group3.griddie.network.NetworkMain;

public class GetBufferSize implements Command {
    private NetworkMain access;
    private int bufferSize;

    public GetBufferSize(NetworkMain access){
        this.access = access;
    }

    @Override
    public void execute() {
        this.bufferSize = access.getBufferInSize();
    }

    public int getBufferSize(){
        return this.bufferSize;
    }
}
