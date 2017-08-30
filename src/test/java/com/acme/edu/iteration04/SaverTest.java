package com.acme.edu.iteration04;

import static java.lang.System.lineSeparator;

import com.db.Message;
import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;

public class SaverTest {
    @Test
    public void shouldReturnDecoratedStringWhenPackCharMessageIsCalled() {
        Message sut = new Message();

        assertThat(sut.packMessage('t')).isEqualTo("char: t" + lineSeparator());
    }
    @Test
    public void shouldReturnDecoratedStringWhenPackStringMessageIsCalled() {
        Message sut = new Message();
        sut.packMessage("test1");

        assertThat(sut.flush()).isEqualTo("string: test1" + lineSeparator());
    }

    @Test
    public void shouldReturnDoubledStringForSeveralStrings() {
        Message sut = new Message();
        sut.packMessage("test");
        sut.packMessage("test");

        assertThat(sut.flush()).isEqualTo("string: test (x2)" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedStringWhenPackBoolMessageIsCalled() {
        Message sut = new Message();

        assertThat(sut.packMessage(true)).isEqualTo("primitive: true" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedStringWhenPackByteMessageIsCalled() {
        Message sut = new Message();

        assertThat(sut.packMessage((byte)10)).isEqualTo("primitive: 10" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedWhenPackOneIntMessage() {
        Message sut = new Message();
        sut.packMessage(10);

        assertThat(sut.flush()).isEqualTo("primitive: 10" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedWhenPackSomeIntMessage() {
        Message sut = new Message();
        sut.packMessage(10);
        sut.packMessage(20);
        sut.packMessage(40);

        assertThat(sut.flush()).isEqualTo("primitive: 70" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedWhenPackIntArrayMessage() {
        Message sut = new Message();

        assertThat(sut.packMessage(new int[]{1,2,3})).isEqualTo("primitives array: {1, 2, 3}" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedWhenPackObjectMessage() {
        Message sut = new Message();

        assertThat(sut.packMessage(new Object())).contains("reference: ");
    }

    @Test
    public void shouldParseStringWhenGivenExplicitly() {
        Message sut = new Message();
        sut.packMessage("str 4");
        sut.packMessage("str 4");

        assertThat(sut.flush()).isEqualTo("string: 4 (x2)" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedWhenPackBigmasMessage() {
        Message sut = new Message();
        sut.packMessage(Integer.MAX_VALUE);
        sut.packMessage(10);

        assertThat(sut.flush()).isEqualTo("primitive: -2147483639" + lineSeparator());
    }

    @Test
    public void ConsoleLogger() {

    }
}