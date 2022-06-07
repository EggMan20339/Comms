public class ShoutThread extends Thread {

    private int belpackNumber;
    private String beltpackName;

    private BP beltPack;

    ShoutThread(BP pack) {

        belpackNumber = pack.getBeltpackNum();

        beltPack = pack;
        beltpackName = "BP" + belpackNumber;

        System.out.println(beltPack.getBeltpackNum() + " constructed");

    }

    public void run() {


        try {


            while (true) {
                if (beltPack.newData())
                beltPack.send();
                System.out.println("S " + belpackNumber);

            }
        } catch (Exception e) {
            System.out.println(beltpackName + " Threw an exception while listening");
        }
    }

}
