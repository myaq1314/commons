package org.czh.commons.utils.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.SAXHelper;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@Slf4j
public final class Excel07FileParser extends AbstractExcelFileParser implements XSSFSheetXMLHandler.SheetContentsHandler {

    private OPCPackage xlsxPackage;

    @Override
    protected void beforeParse(File file) throws IOException {
        super.beforeParse(file);

        try {
            xlsxPackage = OPCPackage.open(file, PackageAccess.READ);
        } catch (InvalidFormatException e) {
            throw new IOException("can not open file:" + file.getCanonicalPath(), e);
        }
    }

    @Override
    protected void parse() throws IOException {
        try {
            ReadOnlySharedStringsTable sst = new ReadOnlySharedStringsTable(xlsxPackage);
            XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
            StylesTable styles = xssfReader.getStylesTable();

            XMLReader xmlReader = SAXHelper.newXMLReader(new XmlOptions());
            XSSFSheetXMLHandler xmlHandler = new XSSFSheetXMLHandler(styles, sst, this, false);
            xmlReader.setContentHandler(xmlHandler);

            Iterator<InputStream> sheets = xssfReader.getSheetsData();
            while (sheets.hasNext()) {
                InputStream sheet = sheets.next();

                this.sheetName = ((XSSFReader.SheetIterator) sheets).getSheetName();
                this.headerMap = new HashMap<>();
                this.dataList = new ArrayList<>();

                InputSource sheetSource = new InputSource(sheet);

                xmlReader.parse(sheetSource);

                if (this.fileHandler != null) {
                    this.fileHandler.handle(this.dataList);
                    this.dataList = null;
                }

                sheet.close();
            }

        } catch (Exception e) {
            throw new IOException("file excel fail", e);
        }
    }

    @Override
    protected void afterParse(File file) {
        super.afterParse(file);

        if (xlsxPackage != null) {
            try {
                xlsxPackage.close();
            } catch (IOException e) {
                log.warn("file stream close fail", e);
            }
        }
    }

    @Override
    public void startRow(int rowNum) {
        currentRow = rowNum;

        if (isHeader()) {
            return;
        }

        super.startRow(rowNum);
    }

    @Override
    public void endRow(int rowNum) {
        if (isHeader()) {
            return;
        }

        super.endRow(rowNum);
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        currentCol = (new CellReference(cellReference)).getCol();
        currentRow = (new CellReference(cellReference)).getRow();

        processContent(formattedValue);
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {

    }
}
