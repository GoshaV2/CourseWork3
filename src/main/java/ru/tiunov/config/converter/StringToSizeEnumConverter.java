package ru.tiunov.config.converter;

import org.springframework.core.convert.converter.Converter;
import ru.tiunov.model.Size;

public class StringToSizeEnumConverter implements Converter<String, Size> {

    @Override
    public Size convert(String source) {
        return Size.forValues(source);
    }
}
