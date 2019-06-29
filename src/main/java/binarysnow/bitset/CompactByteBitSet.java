package binarysnow.bitset;

class CompactByteBitSet implements BitSet {

    private final static long MAX_NUMBER_OF_BITS = Integer.MAX_VALUE * 8L;
    private final static byte ONE = 1;
    private final static byte[] SET_MASK = new byte[] {-128, 64, 32, 16, 8, 4, 2, 1};
    private final static byte[] UNSET_MASK = new byte[] {127, -65, -33, -17, -9, -5, -3, -2};

    private final byte[] data;
    private final long numberOfBits;
    private final Endianness endianness;
    private final IndexType indexType;

    CompactByteBitSet(final long numberOfBits, final Endianness endianness, final IndexType indexType) {
        if (numberOfBits > MAX_NUMBER_OF_BITS) {
            throw new IllegalArgumentException("Maximum number of bits exceeded. " + numberOfBits + " vs maximum of "
                                               + MAX_NUMBER_OF_BITS);
        }

        if (numberOfBits < 1) {
            throw new IllegalArgumentException("Invalid number of bits, too low. " + numberOfBits);
        }

        final int numOfBytes = (int) (1 + numberOfBits / 8);
        data = new byte[numOfBytes];
        this.endianness = endianness;
        this.numberOfBits = numberOfBits;
        this.indexType = indexType;
    }

    @Override
    public void set(long position, boolean value) {
        final long actualPosition = indexType.offset(position);

        if (actualPosition >= numberOfBits) {
            throw new IllegalArgumentException("Position exceeds number of bits. " + position + " vs " + numberOfBits
            + " for index type " + indexType.name());
        }

        if (actualPosition < 0) {
            throw new IllegalArgumentException("Position too low. " + position + " for index type " + indexType.name());
        }

        final int positionOfByte = (int) (actualPosition / 8);
        final byte positionOfBit = (byte) (actualPosition % 8);

        final byte dataByte = data[positionOfByte];
        final byte modifiedDataByte;

        if (value) {
            modifiedDataByte = (byte) (dataByte | SET_MASK[positionOfBit]);
        } else {
            modifiedDataByte = (byte) (dataByte & UNSET_MASK[positionOfBit]);
        }
        data[positionOfByte] = modifiedDataByte;
    }

    @Override
    public boolean get(long position) {
        final long actualPosition = indexType.offset(position);

        if (actualPosition >= numberOfBits) {
            throw new IllegalArgumentException("Position exceeds number of bits. " + position + " vs " + numberOfBits
                    + " for index type " + indexType.name());
        }

        if (actualPosition < 0) {
            throw new IllegalArgumentException("Position too low. " + position + " for index type " + indexType.name());
        }

        final int positionOfByte = (int) (actualPosition / 8);
        final byte positionOfBit = (byte) (actualPosition % 8);

        final byte dataByte = data[positionOfByte];
        final byte extractedBit = (byte) (dataByte & SET_MASK[positionOfBit]);

        return extractedBit != 0;
    }
}
