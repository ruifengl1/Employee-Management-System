package com.team.project.dao;

import com.team.project.entity.Document;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentDao extends AbstractHibernateDao<Document>{
    public DocumentDao() {
        setClazz(Document.class);
    }
    public List<Document> getAllDocuments() {
        return this.getAll();
    }

    public Document getDocumentById(int id) {
        return findById(id);
    }

}
