package com.saksham.calculator.logging.fileCreator;

import com.saksham.calculator.FileFormat;

public class LogfileCreatorFactory {

    public static LogfileCreator create(FileFormat fileFormat) {
        switch (fileFormat) {
            case csv:
                return new CsvLogfileCreator();
            case txt:
                return new TxtLogfileCreator();
            case docx:
                return new TxtLogfileCreator();
            default:
                throw new RuntimeException("File format not supported");
        }
    }
}
