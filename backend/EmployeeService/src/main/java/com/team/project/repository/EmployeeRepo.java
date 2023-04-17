package com.team.project.repository;

import com.team.project.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@Repository
public class EmployeeRepo{


    final private MongoTemplate mongoTemplate;

    @Autowired
    public EmployeeRepo(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Employee> findEmployeeByUserID(Integer userId){
        Query query= new Query();
        query.addCriteria(Criteria.where("UserID").is(userId));
        return mongoTemplate.find(query, Employee.class, "Employee");
    }
    public List<Employee> findEmployeeByHouseId(Integer houseId){
        Query query= new Query();
        query.addCriteria(Criteria.where("HouseID").is(houseId));
        return mongoTemplate.find(query, Employee.class, "Employee");
    }
    public List<Employee> findAllEmployees(){
        return mongoTemplate.findAll(Employee.class, "Employee");
    }

    public void updateEmployeeVisaDocument(Integer userId, Employee employee){
        Query query= new Query();
        query.addCriteria(Criteria.where("UserID").is(userId));
        Update update = new Update();
        update.set("VisaStatus", employee.getVisaStatuses());
        update.set("PersonalDocument", employee.getPersonalDocuments());
        mongoTemplate.upsert(query, update, Employee.class, "Employee");
    }

    public Employee save(Employee employee){
        return mongoTemplate.save(employee);
    }

    public List<Employee> getAllEmployeeForSummary(){
        Query query= new Query();
        query.fields()
                .include("UserID")
                .include("firstName")
                .include("lastName")
                .include("SSN")
                .include("VisaStatus")
                .include("CellPhone")
                .include("Email");
        return mongoTemplate.find(query, Employee.class, "Employee");

    }

    public List<Employee> getAllVisaStatusManagement(){
        Query query= new Query();
        query.fields()
                .include("UserID")
                .include("firstName")
                .include("lastName")
                .include("VisaStatus");
        return mongoTemplate.find(query, Employee.class, "Employee");
    }

    public Employee updateVisaActiveFlag(Integer userId, String type, String status){
        return mongoTemplate.findAndModify(
                Query.query(Criteria.where("UserID").is(userId).andOperator(Criteria.where("VisaStatus").elemMatch(Criteria.where("VisaType").is(type)))),
                new Update().set("VisaStatus.$.ActiveFlag", status), Employee.class, "Employee");
    }

}
