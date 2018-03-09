package com.library.mapper;

import com.library.domain.Readers;
import com.library.domain.dto.ReadersDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReadersMapper {
    public Readers mapToReaders(final ReadersDto readersDto) {
        return new Readers(
                readersDto.getId(),
                readersDto.getFirstName(),
                readersDto.getLastName(),
                readersDto.getCreationDate(),
                readersDto.getBooksRented());
    }

    public ReadersDto mapToReadersDto(final Readers readers) {
        return new ReadersDto(
                readers.getId(),
                readers.getFirstName(),
                readers.getLastName(),
                readers.getCreationDate(),
                readers.getBooksRented());
    }

    public List<ReadersDto> mapToReadersDtoList(final List<Readers> readersList) {
        return readersList.stream()
                .map(m -> new ReadersDto(m.getId(), m.getFirstName(), m.getLastName(), m.getCreationDate(), m.getBooksRented()))
                .collect(Collectors.toList());
    }
}
