package com.tthttl.visitservice;

import com.tthttl.visitservice.model.Visit;

import java.time.LocalDate;

public class VisitServiceTestHelper {

    public static final Long ID = 1L;
    public static final int DATA_SQL_VISITS_SIZE = 6;

    public static Visit createVisit() {
        Visit visit = new Visit();
        visit.setDate(LocalDate.of(2019, 1, 1));
        visit.setDescription("Description");
        visit.setPetId(1L);
        return visit;
    }

}
