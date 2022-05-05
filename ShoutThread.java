public class ShoutThread extends BP implements Runnable {

    private int belpackNumber;
    private String beltpackName;

    private BP beltPack;

    ShoutThread(BP pack){

        belpackNumber = pack.getBeltpackNum();

        beltPack = pack;

    }

    public void run(){

        while (true) {
            beltPack.send();
            System.out.println("S"+belpackNumber);
        }
    }

}
