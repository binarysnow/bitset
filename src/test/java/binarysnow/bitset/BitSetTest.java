package binarysnow.bitset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BitSetTest {
    private BitSet bitset;

    @BeforeEach
    void before() {
        bitset = BitSetFactory.emptyBitSet(100);
    }

    @Test
    void setBit() {
        bitset.set(0, true);
        assertThat(bitset.get(0)).isTrue();
    }
}
