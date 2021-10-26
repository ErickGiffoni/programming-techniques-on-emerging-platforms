package com.tppe.tdd.patex;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private Patex patex;
    private FileWriter outputFile;
 
    public Parser(Patex patex) {
        this.patex = patex;
        this.outputFile = null;
    }

    public boolean writeToOutputFIle()throws Exception {
        this.outputFile = new FileWriter(patex.outputPath, false);
            switch (patex.userOutputFormatChoice.toString()) {
                case "Lines":
                    this.parseLines(outputFile);
                    break;

                case "Columns":
                    this.parseColumns(outputFile);
                    break;

                default:
                    break;
            }
            return true;
    }

    private void parseLines(FileWriter outputFile) throws IOException {
        boolean wordBeforeWasNumber = false;
        outputFile.write("Evolution number" + patex.delimiter + "value" + "\n");
        for (String word : patex.chosenFileLines) {
            if(isEvolution(word)){
                String[] splitted = splitSpaces(word);
                String evolution = getEvolutionNumber(splitted);
                outputFile.write("\n" + evolution + patex.delimiter);
                wordBeforeWasNumber = false;
            }
            else if(isAnalysisValue(word)){
                String sequence = wordBeforeWasNumber ? (patex.delimiter + word) : word;
                outputFile.write(sequence);
                wordBeforeWasNumber = true;
            }
        }
        outputFile.close();
        return;
    }

    private void parseColumns(FileWriter outputFile) throws IOException {
        /*Funcao que le o arquvivo e preenche a matrizValues, sendo o primerio array as evolucoes
          e o restante os registros respeitando a posicao das evolucoes referidas no primeiro array*/
        Integer column = 0;
        Integer line = 0;
        //Linha representando as evolucoes
        patex.matrizValues.add( new ArrayList<String>() );

        for (String word : patex.chosenFileLines) {
            if(isEvolution(word)){
                String[] splitted = splitSpaces(word);
                column = Integer.parseInt(getEvolutionNumber(splitted));
                line = 0;
                patex.matrizValues.get(0).add(Integer.toString(column));
            }
            else if(isAnalysisValue(word)){
                ++line;
                /* Caso a linha seja null , significa que todas as evolucoes anteriores sao menores que a atual e devem
                 ser null nessa linha */
                if(line == patex.matrizValues.size()){
                    patex.matrizValues.add(new ArrayList<String>());
                    /*Preenchendo os valores dessa linha com null,
                    em que somente o valor na posicao "columns" tenha o valor "word" */
                    for(int i = 0; i < column; i++){
                        patex.matrizValues.get(line).add(null);
                    }
                }
                patex.matrizValues.get(line).add(word);
            }
        }
        parseColumnsWriteFile(outputFile);
        return;
    }

    private void parseColumnsWriteFile(FileWriter outputFile)throws IOException{
        outputFile.write("Evolution "+patex.delimiter+"Evolution "+patex.delimiter+"Evolution "+"\n");
        outputFile.write("value " +patex.delimiter+ "value " + patex.delimiter + "value " + "\n");
        //Percorrendo as linhas
        for(int i =0;i<patex.matrizValues.size(); i++){
            //Percorrendo o array list na posicao i
            for(int j=0; j<patex.matrizValues.get(i).size(); j++){
                outputFile.write(patex.matrizValues.get(i).get(j) + patex.delimiter);
            }
            outputFile.write("\n");
        }
        outputFile.close();
    }

    private boolean isEvolution(String word) {
        return word.matches("(-)*[ Eevolucçãaotion]+(.)*");
    }

    private String[] splitSpaces(String word) {
        return word.split(" ");
    }

    private String getEvolutionNumber(String[] splitted) {
        return splitted[splitted.length - 2];
    }

    private boolean isAnalysisValue(String word) {
        return word.matches("[0-9]+(\\.[0-9]+)?");
    }
}
