package com.saksham.calculator.logging.fileCreator;

import com.saksham.calculator.Operation;

import java.io.File;
import java.util.List;

public interface LogfileCreator {

    File createLogFile(List<Operation> operations);
}
