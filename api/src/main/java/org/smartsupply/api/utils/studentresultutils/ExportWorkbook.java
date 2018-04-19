package org.smartsupply.api.utils.studentresultutils;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExportWorkbook {

    private HSSFWorkbook wb = new HSSFWorkbook();

    Short markColumnWidth = 1600;
    Short gpAndUC_ColumnWidth = 1600;
    Short borderThickness = HSSFCellStyle.BORDER_MEDIUM;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public ExportWorkbook() {
        this.setFontsAndStyles();
    }

    public HSSFWorkbook getWb() {
        return wb;
    }

    HSSFFont fontRed = wb.createFont();
    HSSFFont fontBlue = wb.createFont();
    HSSFFont fontRedBold = wb.createFont();
    HSSFFont fontBold = wb.createFont();

    HSSFCellStyle cStyleRetake = wb.createCellStyle();
    HSSFCellStyle leftBlack = wb.createCellStyle();
    HSSFCellStyle centerBlack = wb.createCellStyle();
    HSSFCellStyle centerBlackBold = wb.createCellStyle();
    HSSFCellStyle centerBlack2DPlaces = wb.createCellStyle();

    HSSFCellStyle leftBlack_BorderRight = wb.createCellStyle();

    HSSFCellStyle centerBlack_BorderBottom = wb.createCellStyle();
    HSSFCellStyle centerBlack_BorderTop = wb.createCellStyle();
    HSSFCellStyle centerBlack_BorderLeftRight = wb.createCellStyle();
    HSSFCellStyle centerBlack_BorderLeft = wb.createCellStyle();
    HSSFCellStyle centerBlack_BorderRight = wb.createCellStyle();

    HSSFCellStyle centerBlackBold_BorderTop = wb.createCellStyle();
    HSSFCellStyle centerBlackBold_BorderTopBottom = wb.createCellStyle();

    HSSFCellStyle centerBlack2DPlaces_BoldLeftRight = wb.createCellStyle();

    DataFormat format = wb.createDataFormat();

    HSSFCellStyle centerBlack1DPlace = wb.createCellStyle();
    HSSFCellStyle centerBlack0DPlaces = wb.createCellStyle();
    HSSFCellStyle centerBlackRetakeScore = wb.createCellStyle();
    HSSFCellStyle centerBlack2DPlacesRetakeScore = wb.createCellStyle();
    HSSFCellStyle centerBlack1DPlacesRetakeScore = wb.createCellStyle();
    HSSFCellStyle centerBlack0DPlacesRetakeScore = wb.createCellStyle();

    HSSFCellStyle centerBlack1DPlace_BorderRight = wb.createCellStyle();

    // first time retakes (ctr)
    HSSFCellStyle centerRed1DPlaceRetake = wb.createCellStyle();

    HSSFCellStyle centerRed0DPlaceRetake = wb.createCellStyle();

    // retake again
    HSSFCellStyle centerRed1DPlaceRetakeRetakeScore = wb.createCellStyle();

    HSSFCellStyle centerRed2DPlaceRetakeRetakeScore = wb.createCellStyle();
    HSSFCellStyle centerRed0DPlaceRetakeRetakeScore = wb.createCellStyle();

    // retaker blue (now passed)
    HSSFCellStyle leftBlue_Retaker = wb.createCellStyle();
    HSSFCellStyle centerBlue_Retaker = wb.createCellStyle();
    HSSFCellStyle centerBlue0DPlace_Retaker = wb.createCellStyle();
    HSSFCellStyle centerBlue1DPlace_Retaker = wb.createCellStyle();
    HSSFCellStyle centerBlue2DPlace_Retaker = wb.createCellStyle();

    // styles with bold borders
    HSSFCellStyle centerBlue2DPlace_Retaker_BoldLeftRight = wb.createCellStyle();
    HSSFCellStyle centerBlue1DPlace_BoldRight = wb.createCellStyle();

    public void setFontsAndStyles() {
        // fonts
        fontRed.setColor(HSSFColor.RED.index);
        fontBlue.setColor(HSSFColor.BLUE.index);
        fontRedBold.setColor(HSSFColor.RED.index);
        fontRedBold.setBoldweight(Font.BOLDWEIGHT_BOLD);
        fontBold.setBoldweight(Font.BOLDWEIGHT_BOLD);
        cStyleRetake.setFont(fontBold);

        // normal marks
        centerBlack.setAlignment(CellStyle.ALIGN_CENTER);
        leftBlack.setAlignment(CellStyle.ALIGN_LEFT);

        centerBlackBold.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlackBold.setFont(fontBold);

        centerBlack2DPlaces.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlack2DPlaces.setDataFormat(format.getFormat("#0.00"));

        centerBlack1DPlace.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlack1DPlace.setDataFormat(format.getFormat("#0.0"));

        centerBlack0DPlaces.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlack0DPlaces.setDataFormat(format.getFormat("#0"));

        // RetakeScores that appear in Black

        centerBlackRetakeScore.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlackRetakeScore.setFont(fontBold);

        centerBlack2DPlacesRetakeScore.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlack2DPlacesRetakeScore.setDataFormat(format.getFormat("#0.00"));
        centerBlack2DPlacesRetakeScore.setFont(fontBold);

        centerBlack1DPlacesRetakeScore.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlack1DPlacesRetakeScore.setDataFormat(format.getFormat("#0.0"));
        centerBlack1DPlacesRetakeScore.setFont(fontBold);

        centerBlack0DPlacesRetakeScore.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlack0DPlacesRetakeScore.setDataFormat(format.getFormat("#0"));
        centerBlack0DPlacesRetakeScore.setFont(fontBold);

        centerRed1DPlaceRetake.setAlignment(CellStyle.ALIGN_CENTER);
        centerRed1DPlaceRetake.setDataFormat(format.getFormat("#0.0"));
        centerRed1DPlaceRetake.setFont(fontRed);

        centerRed0DPlaceRetake.setAlignment(CellStyle.ALIGN_CENTER);
        centerRed0DPlaceRetake.setDataFormat(format.getFormat("##0"));
        centerRed0DPlaceRetake.setFont(fontRed);

        centerRed1DPlaceRetakeRetakeScore.setAlignment(CellStyle.ALIGN_CENTER);
        centerRed1DPlaceRetakeRetakeScore.setDataFormat(format.getFormat("#0.0"));
        centerRed1DPlaceRetakeRetakeScore.setFont(fontRedBold);

        centerRed2DPlaceRetakeRetakeScore.setAlignment(CellStyle.ALIGN_CENTER);
        centerRed2DPlaceRetakeRetakeScore.setDataFormat(format.getFormat("#0.00"));
        centerRed2DPlaceRetakeRetakeScore.setFont(fontRedBold);

        centerRed0DPlaceRetakeRetakeScore.setAlignment(CellStyle.ALIGN_CENTER);
        centerRed0DPlaceRetakeRetakeScore.setDataFormat(format.getFormat("##0"));
        centerRed0DPlaceRetakeRetakeScore.setFont(fontRedBold);

        leftBlue_Retaker.setFont(fontBlue);
        leftBlue_Retaker.setAlignment(CellStyle.ALIGN_LEFT);

        centerBlue_Retaker.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlue_Retaker.setFont(fontBlue);

        centerBlue0DPlace_Retaker.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlue0DPlace_Retaker.setDataFormat(format.getFormat("##0"));
        centerBlue0DPlace_Retaker.setFont(fontBlue);

        centerBlue1DPlace_Retaker.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlue1DPlace_Retaker.setDataFormat(format.getFormat("#0.0"));
        centerBlue1DPlace_Retaker.setFont(fontBlue);

        centerBlue2DPlace_Retaker.setAlignment(CellStyle.ALIGN_CENTER);
        centerBlue2DPlace_Retaker.setDataFormat(format.getFormat("#0.00"));
        centerBlue2DPlace_Retaker.setFont(fontBlue);

        // styles with borders

        leftBlack_BorderRight.cloneStyleFrom(leftBlack);
        leftBlack_BorderRight.setBorderRight(borderThickness);

        centerBlack_BorderBottom.cloneStyleFrom(centerBlack);
        centerBlack_BorderBottom.setBorderBottom(borderThickness);

        centerBlack_BorderTop.cloneStyleFrom(centerBlack);
        centerBlack_BorderTop.setBorderTop(borderThickness);

        centerBlack_BorderLeftRight.cloneStyleFrom(centerBlack);
        centerBlack_BorderLeftRight.setBorderLeft(borderThickness);
        centerBlack_BorderLeftRight.setBorderRight(borderThickness);

        centerBlack_BorderLeft.cloneStyleFrom(centerBlack);
        centerBlack_BorderLeft.setBorderLeft(borderThickness);

        centerBlack_BorderRight.cloneStyleFrom(centerBlack);
        centerBlack_BorderRight.setBorderRight(borderThickness);

        centerBlackBold_BorderTop.cloneStyleFrom(centerBlackBold);
        centerBlackBold_BorderTop.setBorderTop(borderThickness);

        centerBlackBold_BorderTopBottom.cloneStyleFrom(centerBlackBold);
        centerBlackBold_BorderTopBottom.setBorderTop(borderThickness);
        centerBlackBold_BorderTopBottom.setBorderBottom(borderThickness);
        centerBlackBold_BorderTopBottom.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);

        centerBlack1DPlace_BorderRight.cloneStyleFrom(centerBlack1DPlace);
        centerBlack1DPlace_BorderRight.setBorderRight(borderThickness);

        // ==========

        centerBlue2DPlace_Retaker_BoldLeftRight.cloneStyleFrom(centerBlue2DPlace_Retaker);
        centerBlue2DPlace_Retaker_BoldLeftRight.setBorderLeft(borderThickness);
        centerBlue2DPlace_Retaker_BoldLeftRight.setBorderRight(borderThickness);

        centerBlack2DPlaces_BoldLeftRight.cloneStyleFrom(centerBlack2DPlaces);
        centerBlack2DPlaces_BoldLeftRight.setBorderLeft(borderThickness);
        centerBlack2DPlaces_BoldLeftRight.setBorderRight(borderThickness);

        centerBlue1DPlace_BoldRight.cloneStyleFrom(centerBlue1DPlace_Retaker);
        centerBlue1DPlace_BoldRight.setBorderRight(borderThickness);

        log.info(String.format("created %s styles in workbook!!!!", wb.getNumCellStyles()));
    }

    public HSSFCellStyle getCellStyle(boolean isRetakeScore, boolean isRetake, boolean isRetaker,
                                      Integer numDecimalPlaces) {
        numDecimalPlaces = numDecimalPlaces == null ? 2 : numDecimalPlaces;

        if (isRetakeScore && isRetake) {
            return numDecimalPlaces.equals(0) ? centerRed0DPlaceRetakeRetakeScore :
                    numDecimalPlaces.equals(1) ? centerRed1DPlaceRetakeRetakeScore : centerRed2DPlaceRetakeRetakeScore;
        } else if (isRetakeScore) {
            if (numDecimalPlaces.equals(1))
                return centerRed1DPlaceRetake;
            else if (numDecimalPlaces.equals(0))
                return centerBlack0DPlacesRetakeScore;
            else
                return centerBlack2DPlacesRetakeScore;
        } else if (isRetake) {
            if (numDecimalPlaces.equals(1))
                return centerRed1DPlaceRetake;
            else
                return centerRed0DPlaceRetake;
        } else if (numDecimalPlaces.equals(1)) {
            if (isRetaker)
                return centerBlue1DPlace_Retaker;
            else
                return centerBlack1DPlace;
        } else if (numDecimalPlaces.equals(0)) {
            if (isRetaker)
                return centerBlue0DPlace_Retaker;
            else
                return centerBlack0DPlaces;
        }

        return null;
    }

    public HSSFCellStyle getGPA_CGPA_CellStyle() {
        return centerBlack2DPlaces_BoldLeftRight;
    }

    public HSSFCellStyle getCU_CellStyle(Boolean isRetaker) {
        return isRetaker ? centerBlue2DPlace_Retaker_BoldLeftRight : centerBlack2DPlaces_BoldLeftRight;
    }

    public HSSFCellStyle getNameStyle(boolean isRetaker) {
        return isRetaker ? leftBlue_Retaker : leftBlack;
    }
}
