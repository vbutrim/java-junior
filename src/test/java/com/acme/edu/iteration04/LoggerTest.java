package com.acme.edu.iteration04;

import static java.lang.System.lineSeparator;
import static org.junit.Assert.*;
import com.db.OldMessage;
import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;

public class LoggerTest {
    @Test
    public void shouldReturnDecoratedStringWhenPackCharMessageIsCalled() {
        OldMessage sut = new OldMessage();

        assertThat(sut.packCharacterMessage('t')).isEqualTo("char: t" + lineSeparator());
    }
    @Test
    public void shouldReturnDecoratedStringWhenPackStringMessageIsCalled() {
        OldMessage sut = new OldMessage();
        sut.packStringMessage("test1");

        assertThat(sut.flush()).isEqualTo("string: test1" + lineSeparator());
    }

    @Test
    public void shouldReturnDoubledStringForSeveralStrings() {
        OldMessage sut = new OldMessage();
        sut.packStringMessage("test");
        sut.packStringMessage("test");

        assertThat(sut.flush()).isEqualTo("string: test (x2)" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedStringWhenPackBoolMessageIsCalled() {
        OldMessage sut = new OldMessage();

        assertThat(sut.packBooleanMessage(true)).isEqualTo("primitive: true" + lineSeparator());
    }
    @Test
    public void shouldReturnDecoratedStringWhenPackByteMessageIsCalled() {
        OldMessage sut = new OldMessage();

        assertThat(sut.packByteMessage((byte)10)).isEqualTo("primitive: 10" + lineSeparator());
    }
//    @Test
//    public void shouldReturnDecoratedStringWhenPackStringMessageIsCalled() {
//        OldMessage sut = new OldMessage();
//
//        assertThat(sut.packCharacterMessage("test")).isEqualTo("string: test" + lineSeparator());
//    }

}