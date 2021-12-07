public class Merger implements MergerInterface { 
    
    /**
     *  Add the necessary fields and implement the methods in this file to 
     *  implement the Merger class. Be sure to look at the definition for the
     *  interface that this class implements.
     **/
    Merger[] half;
    Balancer[] layer;
    final int width;

	public Merger(int myWidth) {
        width = myWidth;
        layer = new Balancer[width / 2];
        for (int i = 0; i < width / 2; i++) {
            layer[i] = new Balancer();
        }
        if (width > 2) {
            half = new Merger[]{new Merger(width / 2), new Merger(width / 2)};
        }
	}

    // input is the wire on which the token enters.
	public int traverse(int input) {
        int output = 0;
        if (width > 2) {
            if (input < width / 2) {    // If the input wire is one of the ﬁrst width/2,
                // then the token is sent to half[0] if i is even 
                // and to half[1] if i is odd.
                output = half[input % 2].traverse(input / 2);
    
            } else { // Otherwise, 
                // it is sent to half[0] if i is odd and to half[1] if i is even.
                output = half[1 - (input % 2)].traverse(input / 2);
            }
        }
        //No matter which half-width merger network it traverses, 
        // a token that emerges on wire i is fed to the balancer at layer[i]
        return (2 * output) + layer[output].traverse();
	}

	public int antiTraverse(int input) {
        int output = 0;
        if (width > 2) {
            if (input < width / 2) {    // If the input wire is one of the ﬁrst width/2,
                // then the token is sent to half[0] if i is even 
                // and to half[1] if i is odd.
                output = half[input % 2].antiTraverse(input / 2);
    
            } else { // Otherwise, 
                // it is sent to half[0] if i is odd and to half[1] if i is even.
                output = half[1 - (input % 2)].antiTraverse(input / 2);
            }
        }
        return (2 * output) + layer[output].antiTraverse();
	}
}       

