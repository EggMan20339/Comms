public class ListenThread extends BP implements Runnable {

    private int belpackNumber;
    private String beltpackName;

    private BP beltPack;

    ListenThread(BP pack){

        belpackNumber = pack.getBeltpackNum();

        beltPack = pack;

    }

    public void run(){

        while (true) {
            beltPack.Listen();
        }
    }

}
