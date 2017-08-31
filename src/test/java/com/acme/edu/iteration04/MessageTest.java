package com.acme.edu.iteration04;

import static java.lang.System.lineSeparator;

import com.db.FormattingSavingHandler;
import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;

public class MessageTest {
    @Test
    public void shouldReturnDecoratedStringWhenPackCharMessageIsCalled() {
        FormattingSavingHandler sut = new FormattingSavingHandler();

        assertThat(sut.packMessage('t')).isEqualTo("char: t" + lineSeparator());
    }
    @Test
    public void shouldReturnDecoratedStringWhenPackStringMessageIsCalled() {
        FormattingSavingHandler sut = new FormattingSavingHandler();
        sut.packMessage("test1");

        assertThat(sut.flush()).isEqualTo("string: test1" + lineSeparator());
    }

    @Test
    public void shouldReturnDoubledStringForSeveralStrings() {
        FormattingSavingHandler sut = new FormattingSavingHandler();
        sut.packMessage("test");
        sut.packMessage("test");

        assertThat(sut.flush()).isEqualTo("string: test (x2)" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedStringWhenPackBoolMessageIsCalled() {
        FormattingSavingHandler sut = new FormattingSavingHandler();

        assertThat(sut.packMessage(true)).isEqualTo("primitive: true" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedStringWhenPackByteMessageIsCalled() {
        FormattingSavingHandler sut = new FormattingSavingHandler();

        assertThat(sut.packMessage((byte)10)).isEqualTo("primitive: 10" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedWhenPackOneIntMessage() {
        FormattingSavingHandler sut = new FormattingSavingHandler();
        sut.packMessage(10);

        assertThat(sut.flush()).isEqualTo("primitive: 10" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedWhenPackSomeIntMessage() {
        FormattingSavingHandler sut = new FormattingSavingHandler();
        sut.packMessage(10);
        sut.packMessage(20);
        sut.packMessage(40);

        assertThat(sut.flush()).isEqualTo("primitive: 70" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedWhenPackIntArrayMessage() {
        FormattingSavingHandler sut = new FormattingSavingHandler();

        assertThat(sut.packMessage(new int[]{1,2,3})).isEqualTo("primitives array: {1, 2, 3}" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedWhenPackObjectMessage() {
        FormattingSavingHandler sut = new FormattingSavingHandler();

        assertThat(sut.packMessage(new Object())).contains("reference: ");
    }

    @Test
    public void shouldParseStringWhenGivenExplicitly() {
        FormattingSavingHandler sut = new FormattingSavingHandler();
        sut.packMessage("str 4");
        sut.packMessage("str 4");
        
        assertThat(sut.flush()).isEqualTo("string: 4 (x2)" + lineSeparator());
    }

    @Test
    public void shouldReturnDecoratedWhenPackBigmasMessage() {
        FormattingSavingHandler sut = new FormattingSavingHandler();
        sut.packMessage(Integer.MAX_VALUE);
        assertThat(sut.packMessage(10)).isEqualTo("primitive: " + Integer.MAX_VALUE + lineSeparator());
        assertThat(sut.flush()).isEqualTo("primitive: 10" + lineSeparator());
    }
}