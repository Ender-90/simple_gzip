package study;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class DecompressModule {

    Scanner sc;

    public DecompressModule(Scanner sc){
        this.sc = sc;
    }

    public void decompressOperation(){
        try{
            GZIPInputStream sourceFile = chooseSourceForDecompression();
            FileOutputStream outputFile = chooseOutputForDecompression();
            decompress(sourceFile, outputFile);

            sourceFile.close();
            outputFile.close();

            System.out.println("Dekompresja zakończona sukcesem.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private GZIPInputStream chooseSourceForDecompression(){

        System.out.println("Podaj nazwę pliku źródłowego: ");
        boolean isExist;
        FileInputStream inputFile = null;

        do {
            String fileName = sc.nextLine();
            try {
                inputFile = new FileInputStream(fileName);
                isExist = true;
            } catch (FileNotFoundException e) {
                System.out.println("Plik nie istnieje!");
                isExist = false;
            }
        }while(!isExist);

        GZIPInputStream gzipInput = null;

        try {
            gzipInput = new GZIPInputStream(inputFile);
        }catch(IOException e){
            e.printStackTrace();
        }

        return gzipInput;
    }

    private FileOutputStream chooseOutputForDecompression(){

        System.out.println("Podaj nazwę pliku wyjściowego: ");
        boolean isSuccess;
        FileOutputStream outputFile = null;

        do {
            String fileName = sc.nextLine();
            try {
                outputFile = new FileOutputStream(fileName);
                isSuccess = true;
            } catch (FileNotFoundException e) {
                System.out.println("Nie można utworzyć pliku!");
                isSuccess = false;
            }
        }while(!isSuccess);

        return outputFile;
    }

    private void decompress(GZIPInputStream sourceFile, FileOutputStream outputFile){

        byte[] buffer = new byte[1024];

        try{

            System.out.println("Rozpoczęcie operacji.");

            FileOutputStream out = outputFile;
            GZIPInputStream gis = sourceFile;
            int len;

            while ((len = gis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            gis.close();
            out.close();

            System.out.println("Koniec operacji.");

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
