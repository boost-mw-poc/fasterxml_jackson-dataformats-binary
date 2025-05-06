// Smile Main artifact Module descriptor
module tools.jackson.dataformat.smile
{
    requires transitive tools.jackson.core;
    requires transitive tools.jackson.databind;

    exports tools.jackson.dataformat.smile;
    exports tools.jackson.dataformat.smile.async;

    provides tools.jackson.core.TokenStreamFactory with
        tools.jackson.dataformat.smile.SmileFactory;
    provides tools.jackson.databind.ObjectMapper with
        tools.jackson.dataformat.smile.SmileMapper;
}
