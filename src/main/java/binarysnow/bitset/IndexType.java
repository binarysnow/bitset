package binarysnow.bitset;

public enum IndexType {
    ZeroIndex(0), OneIndex(1);

    private final int indexOffset;

    IndexType(final int indexOffset) {
        this.indexOffset = indexOffset;
    }

    long offset(final long input) {
        return input - indexOffset;
    }
}
