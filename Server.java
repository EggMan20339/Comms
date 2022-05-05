import com.sun.source.tree.LiteralTree;

import javax.sound.sampled.*;


public class Server extends BP {


    public static void main(String[] args) {

        BP BP1 = new BP(1);
        BP BP2 = new BP(2);
        BP BP3 = new BP(3);
        BP BP4 = new BP(4);

        ListenThread BPLT1 = new ListenThread(BP1);
        ListenThread BPLT2 = new ListenThread(BP2);
        ListenThread BPLT3 = new ListenThread(BP3);
        ListenThread BPLT4 = new ListenThread(BP4);

        ShoutThread BPST1 = new ShoutThread(BP1);
        ShoutThread BPST2 = new ShoutThread(BP2);
        ShoutThread BPST3 = new ShoutThread(BP3);
        ShoutThread BPST4 = new ShoutThread(BP4);



    }
}
