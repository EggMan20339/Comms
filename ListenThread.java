public class ListenThread extends Thread {

    private int belpackNumber;
    private String beltpackName;

    private BP beltPack;

    ListenThread(BP pack){

        belpackNumber = pack.getBeltpackNum();

        beltPack = pack;
        beltpackName = "BP"+belpackNumber;

    }

    public void run(){

        try {


        while (true) {
            beltPack.Listen();
            System.out.println("L"+belpackNumber);
        }}catch (Exception e){
            System.out.println(beltpackName +" Threw an exception while listening");
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
