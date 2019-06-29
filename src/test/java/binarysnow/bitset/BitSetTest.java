package binarysnow.bitset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BitSetTest {
    @Test
    void setBit() {
        for (int numberOfBits = 1; numberOfBits <= 300; numberOfBits++) {
            for (int bitToSet = 0; bitToSet < numberOfBits; bitToSet++) {
                final BitSet bitset = BitSetFactory.compactByteBitSet(numberOfBits);
                bitset.set(bitToSet, true);

                for (int bitToCheck = 0 ; bitToCheck < numberOfBits; bitToCheck++) {
                    if (bitToCheck == bitToSet) assertThat(bitset.get(bitToCheck)).as("" + numberOfBits + "," +
                            bitToSet + "," + bitToCheck).isTrue();
                    else assertThat(bitset.get(bitToCheck)).as("" + numberOfBits + "," + bitToSet + "," +
                            bitToCheck).isFalse();
                }
            }
        }
    }
}
