import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class Beltpack {

    static int BPN = 1;
    //4096
    static private int bufferSize = 16;
    static private int timeout = 3000000;

    static DataLine.Info outLineInfo;
    static TargetDataLine micLine;

    static int inPort = 50000 + BPN;
    static int outPort = 25550 + BPN;

    static String serverIP ="224.0.50.50";
    static String beltpackIP = "224.0.50.0";

    static float rate = 44100.0f;


    public static void main(String[] args) {
        try {

            System.setProperty("java.net.preferIPv4Stack", "true");

            InetAddress serverAddress = InetAddress.getByName(serverIP);
            MulticastSocket outSocket = new MulticastSocket();

            InetAddress beltpackAddress = InetAddress.getByName(beltpackIP);
            MulticastSocket inSocket = new MulticastSocket(inPort);
            inSocket.joinGroup(serverAddress);
            inSocket.setSoTimeout(timeout);


            byte[] revieveData = new byte[bufferSize];

            AudioFormat aFormat = new AudioFormat(rate, 16, 1, true, false);

            DataLine.Info inLineInfo = new DataLine.Info(SourceDataLine.class, aFormat);
            SourceDataLine inLineSource = (SourceDataLine) AudioSystem.getLine(inLineInfo);
            inLineSource.open(aFormat);
            inLineSource.start();

            DatagramPacket recievePacket = new DatagramPacket(revieveData, revieveData.length);
            ByteArrayInputStream BAIS = new ByteArrayInputStream(recievePacket.getData());

            byte[] sendData = new byte[bufferSize];

            outLineInfo = new DataLine.Info(TargetDataLine.class, aFormat);

            if (!AudioSystem.isLineSupported(outLineInfo)){
                System.out.println("Line not supported!");
            }

            micLine = (TargetDataLine) AudioSystem.getLine(outLineInfo);
            micLine.open(aFormat);
            micLine.start();



            while (true){

                try{


                    inSocket.receive(recievePacket);
                    System.out.println("packet received");
//                    AudioInputStream AIS = new AudioInputStream(BAIS, aFormat, recievePacket.getLength());
                    inLineSource.write(recievePacket.getData(), 0, recievePacket.getData().length);
                }catch (Exception e){
                    System.out.println("Not Connected");
                }
                micLine.read(sendData, 0, sendData.length);
                DatagramPacket DGP = new DatagramPacket(sendData, sendData.length, serverAddress, outPort);
                outSocket.send(DGP);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

