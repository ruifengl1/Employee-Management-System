package com.team.project.service;

import com.netflix.discovery.converters.Auto;
import com.team.project.dao.DocumentDao;
import com.team.project.entity.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DocumentService {
    private DocumentDao documentDao;
    @Autowired
    public DocumentService(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    @Transactional
    public List<Document> getAllDocuments() {
        return documentDao.getAllDocuments();
    }

    @Transactional
    public Document getDocumentById(int id) {
        return documentDao.getDocumentById(id);
    }

}
