package binarysnow.bitset;

public class BitSetFactory {

    public static BitSet compactByteBitSet(final int numberOfBits) {
        return new CompactByteBitSet(numberOfBits, Endianness.BigEndian, IndexType.ZeroIndex);
    }
}
