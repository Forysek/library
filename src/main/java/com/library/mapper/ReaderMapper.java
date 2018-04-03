package com.library.mapper;

import com.library.domain.Reader;
import com.library.domain.dto.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {
    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getId(),
                readerDto.getFirstName(),
                readerDto.getLastName(),
                readerDto.getCreationDate(),
                readerDto.getBooksCopies());
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getCreationDate(),
                reader.getBooksCopies());
    }

    public List<ReaderDto> mapToReadersDtoList(final List<Reader> readersList) {
        return readersList.stream()
                .map(m -> new ReaderDto(m.getId(), m.getFirstName(), m.getLastName(), m.getCreationDate(), m.getBooksCopies()))
                .collect(Collectors.toList());
    }
}
