package org.smartsupply.api.service;

import org.smartsupply.model.enums.Setup;

public interface SetupService {

    public Setup setup();

    public boolean hasStudentNumbers();

    public boolean hasCampuses();

    public boolean pictureDirectoryExists() throws Exception;

    public String pictureDirectory() throws Exception;

    public boolean hasPictures();

    boolean allowsResultsBelowStudentIntake();

    boolean testimonialHasStudentOption();
}
