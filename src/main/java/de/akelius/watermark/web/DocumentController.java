package de.akelius.watermark.web;

import de.akelius.watermark.domain.Document;
import de.akelius.watermark.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api")
public class DocumentController {

    DocumentService documentService;

    public DocumentController(@Autowired DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * @param ticket of a document
     * @return corresponding document for the ticket
     */
    @GetMapping(path = "/document/{ticket}")
    public ResponseEntity get(@PathVariable Long ticket) {
        Document document = documentService.get(ticket);
        if (document == null) {
            return ResponseEntity.status(HttpStatus.OK).body("No document exist for such a ticket!");
        }
        return new ResponseEntity<>(document, HttpStatus.ACCEPTED);
    }

    /**
     * @param ticket of a document
     * @return corresponding document for the ticket
     */
    @PutMapping(path = "/document/{ticket}/watermark")
    public ResponseEntity watermark(@PathVariable Long ticket) {
        documentService.watermark(ticket);
        return ResponseEntity.status(HttpStatus.OK).body("Document with ticket No : " + ticket + " watermarked!");
    }


}
