package org.smartsupply.api.service.baseclasses;

import com.jarcommons.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.smartsupply.model.BaseData;
import org.smartsupply.model.enums.Setup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.jarcommons.StringUtil.isNotBlankOrEmpty;

// Class to hold static service operations
public class BaseServiceClass {

    private static Logger logStatic = LoggerFactory.getLogger(BaseServiceClass.class);

    private static Setup setup = getSetup();

    public static boolean isTest() {
        try {
            String fileName = "RMS_SETTINGS.properties";
            String driverClass = "hibernate.connection.driver_class";
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            Properties prop = new Properties();
            if (stream != null) {
                prop.load(stream);
            }
            String forName = prop.getProperty(driverClass);
            return forName.contains("h2");
        } catch (IOException e) {
            logStatic.error("Error", e);
            return false;
        }
    }

    public static Setup getSetup() {
        if (setup == null) {
            String setupStr = getProperty("setup");
            setup = StringUtil.isNotBlankOrEmpty(setupStr) ? Setup.valueOf(setupStr) : Setup.UTAMU;
        }
        return setup;
    }

    public static String getProperty(String propertyName) {
        try {
            String fileName = "RMS_SETTINGS.properties";
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            Properties prop = new Properties();
            if (stream != null) {
                prop.load(stream);
            }
            return prop.getProperty(propertyName);
        } catch (IOException e) {
            logStatic.error("Error", e);
        }
        return null;
    }

    private static String pictureDirectory;

    public static String getPictureDirectory() throws Exception {
        if (!setup.hasProfilePictures()) {
            throw new Exception("Setup " + setup + " has no Profile pictures");
        }

        if (pictureDirectory == null) {
            pictureDirectory = setup.hasProfilePictures() ? processPictureDirectory() : null;
        }

        if (pictureDirectory == null) {
            throw new Exception("Picture directory is not configured for " + setup);
        }
        return pictureDirectory;
    }

    private static String processPictureDirectory() {
        try {
            String fileName = "RMS_SETTINGS.properties";
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            Properties prop = new Properties();
            if (stream != null) {
                prop.load(stream);
            }
            String directory = prop.getProperty("picture_directory");
            String space = prop.getProperty("space_place_holder");
            String directory_separator_place_holder = prop.getProperty("directory_separator_place_holder");
            if (directory != null && space != null && directory_separator_place_holder != null) {
                return directory.replace(space, " ").replace(directory_separator_place_holder, File.separator);
            }
        } catch (IOException e) {
            logStatic.error("Error", e);
        }
        return null;
    }

    public static String getILikeComparator() {
        return isTest() ? " like " : " ilike ";
    }

    public static String generateValuesString(int numRecords, int numFields) {

        String fields = "";

        for (int i = 0; i < numFields; i++)
            fields += StringUtils.isBlank(fields) ? "?" : ", ?";

        fields = StringUtil.bracketed(fields);

        String x = "";
        for (int i = 0; i < numRecords; i++)
            x += StringUtils.isBlank(x) ? fields : "," + fields;

        x += ";";
        return x;
    }

    public static <T extends BaseData> T get(T obj, List<T> objects) {
        if (obj != null && obj.idIsNOTBlankOrEmpty()) {
            for (T t : objects) {
                if (t.equals(obj)) {
                    return t;
                }
            }
        }
        return null;
    }

    public static <T extends BaseData> String getIds(List<T> objects, String separator) {
        String ids = "";
        separator = isNotBlankOrEmpty(separator) ? separator : ",";
        if (objects != null && objects.size() > 0) {
            for (T t : objects) {
                ids += (isNotBlankOrEmpty(ids) ? separator : "") + t.getId();
            }
        }
        return ids;
    }

    public static <T extends BaseData> String[] getIds(List<T> objects) {
        List<String> ids = new ArrayList<>();
        if (objects != null && objects.size() > 0) {
            for (T t : objects) {
                if (!ids.contains(t.getId()))
                    ids.add(t.getId());
            }
        }
        String ids2[] = new String[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            ids2[i] = ids.get(i);
        }
        return ids2;
    }

    public static List<String> addIfMissing(List<String> strings, String t) {
        if (t != null) {
            if (strings.contains(t)) {
                strings.add(t);
            }
        }
        return strings;
    }

    public static List<String> addIfMissing(List<String> strings, String... newStrings) {
        for (String s : newStrings) {
            strings = addIfMissing(strings, s);
        }
        return strings;
    }
}
