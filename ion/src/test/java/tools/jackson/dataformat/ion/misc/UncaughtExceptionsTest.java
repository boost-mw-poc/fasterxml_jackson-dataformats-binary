package tools.jackson.dataformat.ion.misc;

import java.io.InputStream;

import org.junit.jupiter.api.Test;

import tools.jackson.core.exc.StreamReadException;
import tools.jackson.dataformat.ion.IonObjectMapper;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * A set of unit tests for reported issues where implementation does
 * not catch exceptions like {@link NullPointerException} where it should.
 */
public class UncaughtExceptionsTest
{
    private final IonObjectMapper MAPPER = IonObjectMapper.builder().build();

    // [dataformats-binary#302]
    @Test
    public void testUncaughtException302() throws Exception
    {
        try (InputStream in = getClass().getResourceAsStream("/data/issue-302.ion")) {
            MAPPER.readTree(in);
            fail("Should not pass with invalid content");
        } catch (StreamReadException e) {
            verifyException(e, "Invalid embedded TIMESTAMP");
        }
    }

    // [dataformats-binary#303]
    @Test
    public void testUncaughtException303() throws Exception
    {
        try (InputStream in = getClass().getResourceAsStream("/data/issue-303.ion")) {
            MAPPER.readTree(in);
            fail("Should not pass with invalid content");
        } catch (StreamReadException e) {
            // 19-Dec-2023, tatu: Looks like message depends on ion-java version,
            //     cannot easily verify
            // verifyException(e, "Value exceeds the length of its parent container");
        }
    }
    
    void verifyException(Throwable e, String match)
    {
        String msg = e.getMessage();
        String lmsg = (msg == null) ? "" : msg.toLowerCase();
        if (!lmsg.contains(match.toLowerCase())) {
            fail("Expected an exception with a substrings ("+match+"): got one with message \""+msg+"\"");
        }
    }
}
