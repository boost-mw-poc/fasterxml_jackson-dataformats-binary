// CBOR Main artifact Module descriptor
module tools.jackson.dataformat.cbor
{
    requires transitive tools.jackson.core;
    requires transitive tools.jackson.databind;

    exports tools.jackson.dataformat.cbor;

    provides tools.jackson.core.TokenStreamFactory with
        tools.jackson.dataformat.cbor.CBORFactory;
    provides tools.jackson.databind.ObjectMapper with
        tools.jackson.dataformat.cbor.CBORMapper;
}
