package org.smartsupply.api.service.impl;

import com.jarcommons.enums.Condition;
import org.smartsupply.api.service.SetupService;
import org.smartsupply.api.service.baseclasses.BaseServiceClass;
import org.smartsupply.model.enums.Setup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

@Service("setupService")
public class SetupServiceImpl implements SetupService {

    private Logger log = LoggerFactory.getLogger(SetupServiceImpl.class);

    @Override
    public Setup setup() {
        return BaseServiceClass.getSetup();
    }

    @Override
    public boolean hasStudentNumbers() {
        return setup().hasStudentNumbers();
    }

    @Override
    public boolean hasCampuses() {
        return setup().hasCampuses();
    }

    @Override
    public boolean pictureDirectoryExists() throws Exception {
        String pictureDirName = pictureDirectory();
        return new File(pictureDirName).exists();
    }

    @Override
    public String pictureDirectory() throws Exception {
        return BaseServiceClass.getPictureDirectory();
    }

    @Override
    public boolean hasPictures() {
        try {
            Setup setup = setup();
            if (setup == null) {
                throw new Exception("Setup is null");
            }
            return setup().hasProfilePictures();
        } catch (Exception e) {
            log.error("Error", e);
        }
        return false;
    }

    @Override
    public boolean allowsResultsBelowStudentIntake() {
        Setup setup = setup();
        return setup == null ? false : setup.allowsResultsBeforeStudentIntake();
    }

    @Override
    public boolean testimonialHasStudentOption() {
        Setup setup = setup();
        return setup == null ? true : setup.testimonialHasStudentOption();
    }


}
