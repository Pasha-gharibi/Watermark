package de.akelius.watermark.service.impl;

import de.akelius.watermark.domain.Book;
import de.akelius.watermark.domain.Document;
import de.akelius.watermark.domain.Journal;
import de.akelius.watermark.enm.Topic;
import de.akelius.watermark.repository.DocumentRepository;
import de.akelius.watermark.service.DocumentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class DocumentServiceImplTest {

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    DocumentService documentService;

    @Test
    public final void whenBookEntityIsCreated_thenFound() {
        Book book = new Book();
        book.setTopic(Topic.Science);
        book.setTitle("The Dark Code");
        book.setAuthor("Bruce Wayne");
        Book savedBook = documentRepository.save(book);
        Assertions.assertNotNull(savedBook);
    }

    @Test
    public final void whenJournalEntityIsCreated_thenFound() {
        Journal journal = new Journal();
        journal.setTitle("Journal of human flight routes");
        journal.setAuthor("Clark Kent");
        documentRepository.save(journal);
        Journal savedJournal = documentRepository.save(journal);
        Assertions.assertNotNull(savedJournal);
    }

    @Test
    void watermarkABook_thenIsisWatermarked() {
        Book book = new Book();
        book.setTopic(Topic.Science);
        book.setTitle("The Dark Code");
        book.setAuthor("Bruce Wayne");
        Book savedBook = documentRepository.save(book);
        documentService.watermark(savedBook.getId());
        Optional<Document> watermarkedBook = documentRepository.findById(book.getId());
        Assertions.assertTrue(watermarkedBook.isPresent());
        Assertions.assertNotNull(watermarkedBook.get().getWatermark());
        Assertions.assertEquals(watermarkedBook.get().getWatermark(),book.jsonify());
    }

    @Test
    public final void getADocumentDAfterWatermark_thenWatermarked() {
        Book book = new Book();
        book.setTopic(Topic.Science);
        book.setTitle("The Dark Code");
        book.setAuthor("Bruce Wayne");
        Book savedBook = documentRepository.save(book);
        Assertions.assertNotNull(savedBook);
        Assertions.assertNull( documentService.get(savedBook.getId()).getWatermark());
        documentService.watermark(savedBook.getId());
        Assertions.assertNotNull( documentService.get(savedBook.getId()).getWatermark());

    }

}