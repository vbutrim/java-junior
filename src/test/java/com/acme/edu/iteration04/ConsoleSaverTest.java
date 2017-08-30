package com.acme.edu.iteration04;

import com.db.ConsoleSaver;
import com.db.Saver;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

public class ConsoleSaverTest implements SysoutCaptureAndAssertionAbility{
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @Test
    public void shouldPrintInSystemOutWhenCalled() {
        Saver sut = new ConsoleSaver();

        sut.log("test");

        assertSysoutContains("test");
    }
}
