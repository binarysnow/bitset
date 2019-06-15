package binarysnow.bitset;

public interface BitSet {
    void set(long position, boolean value);

    boolean get(long position);
}
