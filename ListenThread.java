public class ListenThread extends Thread {

    private int belpackNumber;
    private String beltpackName;

    private BP beltPack;

    ListenThread(BP pack) {

        belpackNumber = pack.getBeltpackNum();

        beltPack = pack;
        beltpackName = "BP" + belpackNumber;

        System.out.println(beltPack.getBeltpackNum() + " constructed");

    }

    public void run() {


        try {


            while (true) {
                beltPack.Listen();
                System.out.println("L " + belpackNumber);
            }
        } catch (Exception e) {
            System.out.println(beltpackName + " Threw an exception while listening");
        }
    }
//
//    public void start(){
//
//        Thread t = new Thread(this, beltpackName);
//        t.start();
//
//    }

}
