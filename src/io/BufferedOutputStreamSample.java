package io;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.Random;

import org.junit.Test;

public class BufferedOutputStreamSample {

    private int[] genearateRandomIntArray(int size) {
        Random rnd = new Random(System.currentTimeMillis());
        byte[] inputBytes = new byte[size * 4];
        rnd.nextBytes(inputBytes);
        int[] input = new int[size];
        ByteBuffer.wrap(inputBytes).asIntBuffer().get(input);
        return input;
    }

    /**
     * ByteArrayOutputStreamのsize拡張
     * @throws IOException
     */
    @Test
    public void testByteArrayOutputStreamSize() throws IOException {
        final int intArraySize = 100;
        final int baosSize = 16;
        final int bosSize = 64;

        int[] obj = genearateRandomIntArray(intArraySize);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(baosSize);
        try (ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(baos, bosSize));) {
            oos.writeObject(obj);
        }
        assertThat(baos.toByteArray().length, greaterThan(intArraySize * Integer.BYTES));
    }

}
