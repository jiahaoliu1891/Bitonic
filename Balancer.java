public class Balancer implements BalancerInterface {

    /**
     * Add the necessary fields and implement all the methods in this file to
     * implement the Balancer class. Be sure to look at the definition for the
     * interface that this class implements.
     **/
    boolean toggle = true;
    Balancer[] next;

    public synchronized int traverse() {
        try {
            if (toggle) {
                return 0;
            } else {
                return 1;
            }
        } finally {
            toggle = !toggle;
        }
    }

    public synchronized int antiTraverse() {
        toggle = !toggle;
        if (toggle) {
            return 0;
        } else {
            return 1;
        }
        
    }
}
