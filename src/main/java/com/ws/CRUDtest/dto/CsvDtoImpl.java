package com.ws.CRUDtest.dto;

import com.ws.CRUDtest.model.Book;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class CsvDtoImpl implements CsvDto {
    public ByteArrayInputStream downloadCsvFile(List<Book> books){
        final CSVFormat format =CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CSVPrinter csvPrinter;
        try {
            csvPrinter = new CSVPrinter(new PrintWriter(out),format);
            for (Book book: books) {
                List<String> data = Arrays.asList(
                        book.getIsbn(),
                        book.getBookName(),
                        book.getAuthorName(),
                        book.getAuthorSurname(),
                        book.getDescription()
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("import failed"+e.getMessage());
        }
    }
}
