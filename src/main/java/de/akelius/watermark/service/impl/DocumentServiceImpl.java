package de.akelius.watermark.service.impl;

import de.akelius.watermark.domain.Document;
import de.akelius.watermark.repository.DocumentRepository;
import de.akelius.watermark.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
/*
  For H2, as with most other database systems, the default isolation level is 'read committed'. To make it more explicit it is set there.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS, readOnly = false)
public class DocumentServiceImpl implements DocumentService {
    DocumentRepository documentRepository;

    public DocumentServiceImpl(@Autowired DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document get(Long ticket) {
        Optional<Document> document = documentRepository.findById(ticket);
        return document.orElse(null);
    }

    @Override
    public void watermark(Long ticket) {
        Document document = get(ticket);
        if (document != null && document.getWatermark() == null) {
            document.setWatermark(document.jsonify());
            documentRepository.save(document);
        }
    }

}
