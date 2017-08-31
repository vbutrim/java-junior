package com.acme.edu.iteration04;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Java_16 on 30.08.2017.
 */
public class LoggerSystemTest implements SysoutCaptureAndAssertionAbility{
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @Test
    public void shouldLogToConsole() {
        Logger.log(10);
        Logger.flush();

        assertSysoutContains("primitive: 10");
    }
}
