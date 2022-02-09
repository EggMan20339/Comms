import javax.sound.sampled.*;


public class Server extends BP {


    public static void main(String[] args) {

        BP BP1 = new BP(1);
        BP BP2 = new BP(2);
        BP BP3 = new BP(3);
        BP BP4 = new BP(4);



        while (true){


            BP1.send();
            BP2.send();
            BP3.send();
            BP4.send();


            BP1.Listen();
            BP2.Listen();
            BP3.Listen();
            BP4.Listen();


            if (!BP1.isWorking()){

                System.out.print(BP1.getBeltpackNum() + ", ");

            }
           if (!BP2.isWorking()){

                System.out.print(BP2.getBeltpackNum() + ", ");

            }
            if (!BP3.isWorking()){

                System.out.print(BP3.getBeltpackNum() + ", ");

            }
            if (!BP4.isWorking()){

                System.out.print(BP4.getBeltpackNum() + ", ");

            }
            System.out.println();
        }



    }
}
