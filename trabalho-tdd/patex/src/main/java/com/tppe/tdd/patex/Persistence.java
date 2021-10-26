package com.tppe.tdd.patex;

import java.nio.file.Files;

public class Persistence {


    Persistence(){

    }

   Boolean readChosenFile(Patex patex) throws Exception {
       if(patex.isFileChosen() && patex.isChosenFileReadable()){
           patex.chosenFileLines = Files.readAllLines(
               patex.chosenFile.toPath().toAbsolutePath()
           );
           return true;
       }

       throw new Exception("File was not chosen or is not readable");
   }

    Boolean writeToOutputFile(Patex patex) throws Exception {
        if(patex.wasChosenFileRead() && patex.isOutputFormatChosen() && patex.isOutputPathSet())
            return new Parser(patex).writeToOutputFIle();

        throw new Exception(
            "You must choose the path to save the output file before"
        );
    }
}
