package com.team.project.utils;

import com.team.project.domain.response.EmployeeSummaryViewResponse;

import java.util.Comparator;

public class LastNameSorter implements Comparator<EmployeeSummaryViewResponse> {
    @Override
    public int compare(EmployeeSummaryViewResponse e1, EmployeeSummaryViewResponse e2) {
        if (e1.getLastName() == null || e2.getLastName() == null) {
            throw new IllegalArgumentException("Unnamed Employee found in the system");
        }
        return e1.getLastName().compareToIgnoreCase(e2.getLastName());
    }
}