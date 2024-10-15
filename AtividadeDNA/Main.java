package AtividadeDNA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {    
    public static void main(String[] args) {
        int contaLinha = 0;
        int fitasValidas = 0;
        int fitasInvalidas = 0;
        List<Integer> linhasInvalidas = new ArrayList<>();

        String inputFileName = "src/AtividadeDNA/fita1.txt"; 
        File inputFile = new File(inputFileName);
        String outputFileName = "src/AtividadeDNA/saida_fita1.txt"; 
        File outputFile = new File(outputFileName);

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter fileOutput = new PrintWriter(outputFile)
        ) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                contaLinha++;

                if (!line.trim().matches("[CAGT]*")) {
                    fitasInvalidas++;
                    linhasInvalidas.add(contaLinha);
                    fileOutput.println("***FITA INVALIDA - " + line);
                } else {
                    if (!line.trim().isEmpty()) {
                        fitasValidas++;
                    }
                    fileOutput.println(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("O total de fitas é " + contaLinha);
        System.out.println("O total de fitas válidas é " + fitasValidas);
        System.out.println("O total de fitas inválidas é " + fitasInvalidas);

        if (!linhasInvalidas.isEmpty()) {
            System.out.println("As linhas inválidas são " + linhasInvalidas);
        }
    }
}