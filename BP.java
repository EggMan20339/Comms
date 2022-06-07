import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class BP {

    private static DataLine.Info lInfo;
    private static SourceDataLine sourceDataLine;

    private static TargetDataLine outLine;
    private static DatagramPacket DGP;

    private int beltpackNum;
    private static float  aRate = 44100.0f;
    private static int channels = 1;
    private static int sampleSize = 16;
    private static boolean isSigned = true;
    private static boolean bigEndian = false;
    private static int bufferSize = 16;
    private int timeout = 32;

    private static AudioFormat aFormat = new AudioFormat(aRate, sampleSize, channels, isSigned, bigEndian);
    private static AudioFormat.Encoding encode = AudioFormat.Encoding.PCM_SIGNED;

    private MulticastSocket inSocket;
    private MulticastSocket outSocket;
    private AudioInputStream AIS;
    private DatagramPacket recievePacket;
    private ByteArrayInputStream BAIS;
    private InetAddress serverAddress;
    private InetAddress beltpackAddress;

    private String serverIP;
    private String beltpackIP;

    private int inPort;
    private int outPort;

    private byte[] micIn = new byte[bufferSize];
    private byte[] speakerOut = new byte[bufferSize];


    private boolean newData;


    private boolean isWorking;


    public BP() {

    }

    public BP(int BPN) {


        isWorking = true;

        beltpackNum = BPN;
        serverIP = "224.0.50.50";
        beltpackIP = "224.0.50.0";

        inPort = 25550 + beltpackNum;
        outPort = 50000 + beltpackNum;

        System.out.println("Server is starting for belt-pack: " + BPN);

        System.setProperty("java.net.preferIPv4Stack", "true");

        try {

            serverAddress = InetAddress.getByName(serverIP);
            inSocket = new MulticastSocket(inPort);
            inSocket.joinGroup(serverAddress);
            inSocket.setSoTimeout(timeout);

            beltpackAddress = InetAddress.getByName(beltpackIP);
            outSocket = new MulticastSocket();


            lInfo = new DataLine.Info(SourceDataLine.class, aFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(lInfo);
            sourceDataLine.open(aFormat);
            sourceDataLine.start();

            recievePacket = new DatagramPacket(micIn, micIn.length);
            BAIS = new ByteArrayInputStream(recievePacket.getData());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Listen() {

        try {

            isWorking = true;

            inSocket.receive(recievePacket);
            newData = true;
            AIS = new AudioInputStream(BAIS, aFormat, recievePacket.getLength());

        } catch (Exception e) {
            isWorking = false;
        }
    }

    public void send() {

        try {

            //outLine.read(micIn, 0, micIn.length);
            DGP = new DatagramPacket(micIn, micIn.length, serverAddress, outPort);
            outSocket.send(DGP);
            newData = false;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Boolean isWorking(){

        return isWorking;

    }

    public int getBeltpackNum(){

        return beltpackNum;

    }

    public boolean newData(){
        return newData;
    }

}
