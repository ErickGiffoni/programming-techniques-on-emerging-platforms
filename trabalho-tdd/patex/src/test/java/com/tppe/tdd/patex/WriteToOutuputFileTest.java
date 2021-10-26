package com.tppe.tdd.patex;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

public class WriteToOutuputFileTest extends ChooseFileTest {
    private File testFile;

    WriteToOutuputFileTest(){
        super();
        this.testFile = new File("outputTest/testFile.txt");
    }

    @Test()
    void testWriteToOutputFile_Lines() throws Exception {
        this.patexapp = new Patex("../analysisTime.out", ";");
        this.patexapp.userOutputFormatChoice = "Lines";
        this.patexapp.outputPath = this.testFile.getAbsolutePath();
        this.patexapp.persistence.readChosenFile(this.patexapp);
        assertEquals(true, this.patexapp.persistence.writeToOutputFile(this.patexapp));
    }

    @Test()
    void testWriteToOutputFile_Lines2() throws Exception {
        this.patexapp = new Patex("../analysisMemory.out", ";");
        this.patexapp.userOutputFormatChoice = "Lines";
        this.patexapp.outputPath = this.testFile.getAbsolutePath();
        this.patexapp.persistence.readChosenFile(this.patexapp);
        assertEquals(true, this.patexapp.persistence.writeToOutputFile(this.patexapp));
    }

    @Test()
    void testWriteToOutputFile_Columns() throws Exception {
        this.patexapp = new Patex("../analysisTime.out", ";");
        this.patexapp.userOutputFormatChoice = "Columns";
        this.patexapp.outputPath = this.testFile.getAbsolutePath();
        this.patexapp.persistence.readChosenFile(this.patexapp);
        assertEquals(true, this.patexapp.persistence.writeToOutputFile(this.patexapp));
    }

    @Test()
    void testWriteToOutputFile_Columns2() throws Exception {
        this.patexapp = new Patex("../analysisMemory.out", ";");
        this.patexapp.userOutputFormatChoice = "Columns";
        this.patexapp.outputPath = this.testFile.getAbsolutePath();
        this.patexapp.persistence.readChosenFile(this.patexapp);
        assertEquals(true, this.patexapp.persistence.writeToOutputFile(this.patexapp));
    }
}
