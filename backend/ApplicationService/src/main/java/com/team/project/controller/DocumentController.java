package com.team.project.controller;

import com.team.project.entity.Document;
import com.team.project.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("document")
public class DocumentController {

    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }
    @GetMapping
    public List<Document> getAllDocuments(){
        return documentService.getAllDocuments();
    }

    @GetMapping("{id}")
    public Document getDocumentById(@PathVariable int id){
        return documentService.getDocumentById(id);
    }
}
