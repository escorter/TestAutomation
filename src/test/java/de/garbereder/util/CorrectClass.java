package de.garbereder.util;

/**
 * @author gerritgarbereder
 * @version 1.0
 */
public class CorrectClass {
    protected int i;

    public CorrectClass(){};

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CorrectClass)) return false;

        CorrectClass correctClass = (CorrectClass) o;

        if (i != correctClass.i) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return i;
    }
}