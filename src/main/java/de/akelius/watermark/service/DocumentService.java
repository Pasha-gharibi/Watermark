package de.akelius.watermark.service;

import de.akelius.watermark.domain.Document;

public interface DocumentService {
    Document get(Long ticket);
    void watermark(Long ticket);
}
