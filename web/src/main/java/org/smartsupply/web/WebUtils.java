package org.smartsupply.web;

import com.jarcommons.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.smartsupply.model.BaseData;
import org.smartsupply.model.admin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.smartsupply.api.Constants.MAX_NUM_PAGE_RECORD;
import static org.smartsupply.web.WebConstants.CONTENT_HEADER;
import static org.smartsupply.web.WebConstants.ERROR_MESSAGE;
import static org.smartsupply.web.WebConstants.SYSTEM_MESSAGE;

public class WebUtils {

    private static Logger logStatic = LoggerFactory.getLogger(WebUtils.class);

    public static void prepareNavigation(
            String itemName, long totalNumberOfItems, int numberOfItemsOnPage, int currentPage, String url, ModelMap model) {

        double numberOfPages = totalNumberOfItems / (double) MAX_NUM_PAGE_RECORD;
        int numPages = (int) Math.rint(numberOfPages);
        if (numPages < numberOfPages) {
            numPages += 1;
        }

        model.put("max_num_per_page", MAX_NUM_PAGE_RECORD);
        model.put("navigationItem", itemName);
        model.put("navigationCurrentPage", currentPage);
        model.put("navigationTotalPages", numPages);
        model.put("navigationTotalNumberOfItems", totalNumberOfItems);
        model.put("navigationNumberOfItemsOnPage", numberOfItemsOnPage);
        model.put("navigationMaxNumRecordPerPage", MAX_NUM_PAGE_RECORD);
        model.put("navigationUrl", url);
    }

    public static int getNumPages(long totalNumberOfItems) {
        return getNumPages(totalNumberOfItems, MAX_NUM_PAGE_RECORD);
    }

    public static int getNumPages(long totalNumberOfItems, int numPerPage) {
        double numberOfPages = totalNumberOfItems / (double) numPerPage;
        int numPages = (int) Math.rint(numberOfPages);
        if (numPages < numberOfPages) {
            numPages += 1;
        }
        return numPages;
    }

    public static void prepareNavigation(long totalNumberOfItems, int numberOfItemsOnPage, int currentPage, ModelMap model) {
        int numPages = getNumPages(totalNumberOfItems, MAX_NUM_PAGE_RECORD);

        model.put("max_num_per_page", MAX_NUM_PAGE_RECORD);

        model.put("navigationCurrentPage", currentPage);
        model.put("navigationTotalPages", numPages);
        model.put("navigationTotalNumberOfItems", totalNumberOfItems);
        model.put("navigationNumberOfItemsOnPage", numberOfItemsOnPage);
    }

    public static void populateModelMap(List<ObjKeyValue> list, ModelMap modelMap) {
        for (ObjKeyValue t : list) {
            modelMap.put(t.key, t.value);
        }
    }

    public static void prepareNavigation(int totalNumberOfItems, int currentPage, ModelMap modelMap) {
        int from = currentPage * MAX_NUM_PAGE_RECORD - MAX_NUM_PAGE_RECORD + 1;
        int max = currentPage * MAX_NUM_PAGE_RECORD;
        int to = max > totalNumberOfItems ? totalNumberOfItems : max;
        populateModelMap(Arrays.asList(
                new ObjKeyValue("max_num_per_page", MAX_NUM_PAGE_RECORD),
                new ObjKeyValue("from", from),
                new ObjKeyValue("to", to),
                new ObjKeyValue("numPages", getNumPages(totalNumberOfItems, MAX_NUM_PAGE_RECORD)),
                new ObjKeyValue("totalNumberOfItems", totalNumberOfItems),
                new ObjKeyValue("previous", currentPage - 1),
                new ObjKeyValue("next", currentPage + 1)), modelMap);
    }

    // NOT UNIT TESTED Returns the base url (e.g, http://myhost:8080/myapp) suitable for using in a base tag or building reliable urls.
    public static String getBaseUrl(HttpServletRequest request) {
        String port = (request.getServerPort() == 80 || request.getServerPort() == 443) ? "" : ":" + request.getServerPort();
        return request.getScheme() + "://" + request.getServerName() + port + request.getContextPath();
    }

    public static boolean fileIsExcel2003(MultipartFile file) {
        return file.getContentType().toString().equals("application/vnd.ms-excel")
                || file.getContentType().toString().equals("application/octet-stream");
    }

    public static boolean fileIsExcel2007(MultipartFile file) {
        return file.getContentType().toString().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static String getFileExtension(MultipartFile file) throws Exception {
        String e = fileIsExcel2003(file) ? ".xls" : fileIsExcel2007(file) ? ".xlsx" : "unknown";
        if (e.equalsIgnoreCase("unknown")) {
            throw new Exception("Unknown file type");
        }
        return e;
    }

    public static int initializeSearchPageNumber(Integer pageNo) {
        return (pageNo == null || pageNo <= 0) ? 1 : pageNo;
    }

    public static void addContentHeader(ModelMap modelMap, String contentHeader) {
        modelMap.put(CONTENT_HEADER, contentHeader);
    }

    public static void addMenuActivators(String activeGroup, String activeItem, ModelMap modelMap) {
        if (StringUtil.isNotBlankOrEmpty(activeGroup))
            modelMap.put("activeGroup", activeGroup);
        if (StringUtil.isNotBlankOrEmpty(activeItem))
            modelMap.put("activeItem", activeItem);
    }

    public static void addSystemMessage(ModelMap modelMap, String systemMessage) {
        modelMap.put(SYSTEM_MESSAGE, systemMessage);
    }

    public static void addErrorMessage(ModelMap modelMap, String errorMessage) {
        modelMap.put(ERROR_MESSAGE, errorMessage);
    }

    public static void logExceptionAndAddErrorMessage(ModelMap modelMap, Exception e) {
        logStatic.error("Error", e);
        modelMap.put(ERROR_MESSAGE, e.getMessage());
    }

    public static void addLongResponseMessage(ModelMap modelMap, String str) {
        if (StringUtil.isNotBlankOrEmpty(str)) {
            modelMap.put(WebConstants.LONG_RESPONSE_TEXT, str);
        }
    }

    public static void logExceptionAndAddLongResponseMessage(ModelMap modelMap, Exception e) {
        logStatic.error("Error", e);
        addLongResponseMessage(modelMap, e.getMessage());
    }

    public static void validateThatFileTypeIsExcel(MultipartFile file) throws Exception {
        if (!fileIsExcel2003(file) && !fileIsExcel2007(file)) {
            logStatic.error("File type error - " + file.getContentType().toString());
            throw new Exception("Invalid type format. Only MS Excel expected.");
        }
    }

    public static void validateThatFileTypeIsZip(MultipartFile file) throws Exception {
        if (!file.getContentType().equalsIgnoreCase("application/zip")) {
            throw new Exception("Invalid type format. Only zipped (.zip) files expected.");
        }
    }

    public static void validateFileAndFileType(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Empty file error");
        }
        if (!fileIsExcel2003(file) && !fileIsExcel2007(file)) {
            logStatic.error("File type error - " + file.getContentType().toString());
            throw new Exception("Invalid type format. Only MS Excel expected.");
        }
    }

    public static void checkAndThrowIdNotFoundException(String objectName, BaseData object) throws Exception {
        if (object == null) {
            throw new Exception(objectName + " with given id not found in database");
        }
    }

    public static void addSystemMessageIfMissing(ModelMap modelMap, String systemMessage) {
        addConditionalMessage(modelMap, SYSTEM_MESSAGE, systemMessage, !modelMap.containsAttribute(SYSTEM_MESSAGE));
    }

    public static void addConditionalMessage(ModelMap modelMap, String key, String value, boolean condition) {
        if (condition) {
            modelMap.put(key, value);
        }
    }

    public static void throwLoggedInUserNotFoundException(User loggedInUser) throws Exception {
        if (loggedInUser == null) {
            throw new Exception("Internal Error, logged in user not found");
        }
    }

    public static boolean validFile(MultipartFile file) {
        try {
            validateFileAndFileType(file);
            return true;
        } catch (Exception e) {
            logStatic.error("Invalid file", e);
        }
        return false;
    }

    public static XSSFWorkbook getXSSFWorkbook(MultipartFile file)
            throws IOException, InvalidFormatException {
        return fileIsExcel2007(file) ? new XSSFWorkbook(OPCPackage.open(file.getInputStream())) : null;
    }

    public static HSSFWorkbook getHSSFWorkbook(MultipartFile file)
            throws IOException, InvalidFormatException {
        return fileIsExcel2003(file) ? new HSSFWorkbook(new POIFSFileSystem(file.getInputStream())) : null;
    }
}