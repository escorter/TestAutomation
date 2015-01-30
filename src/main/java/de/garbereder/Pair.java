package de.garbereder;

/**
 * Simple pair class like in STL
 *
 * @author gerritgarbereder
 * @version 1.0
 */
public class Pair<A, B> {
    public A first;
    public B second;
    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
}
