package com.fasterxml.jackson.dataformat.cbor.tofix;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.dataformat.cbor.CBORGenerator;
import com.fasterxml.jackson.dataformat.cbor.CBORTestBase;
import com.fasterxml.jackson.dataformat.cbor.testutil.failure.JacksonTestFailureExpected;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringRef599Test extends CBORTestBase
{
    static class AB599 {
        public String a;
        public String b;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class B599 {
        public String b;
    }
    
    private final ObjectMapper MAPPER = cborMapper(cborFactoryBuilder()
            .enable(CBORGenerator.Feature.STRINGREF)
            .build());

    // [dataformats-binary#599]
    @JacksonTestFailureExpected
    @Test
    public void testStringRef() throws Exception
    {
        AB599 ab = new AB599();
        ab.a = "foo";
        // important: has to be same String value to use StringRef
        ab.b = ab.a;
        byte[] cbor = MAPPER.writeValueAsBytes(ab);
        B599 b = MAPPER.readValue(cbor, B599.class);
        assertEquals(ab.b, b.b);
    }    
}
