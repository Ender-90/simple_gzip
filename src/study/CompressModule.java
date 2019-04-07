package study;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;

public class CompressModule {

    Scanner sc;

    public CompressModule(Scanner sc){
        this.sc = sc;
    }

    public void compressOperation(){
        try {
            FileInputStream sourceFile = chooseSourceForCompression();
            GZIPOutputStream outputFile = chooseOutputForCompression();
            compress(sourceFile, outputFile);

            sourceFile.close();
            outputFile.finish();
            outputFile.close();

            System.out.println("Kompresja zakończona sukcesem.");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private FileInputStream chooseSourceForCompression(){

        System.out.println("Podaj nazwę pliku źródłowego: ");
        boolean isExist;
        FileInputStream sourceFile = null;

        do {
            String fileName = sc.nextLine();
            try {
                sourceFile = new FileInputStream(fileName);
                isExist = true;
            } catch (FileNotFoundException e) {
                System.out.println("Plik nie istnieje!");
                isExist = false;
            }
        }while(!isExist);

        return sourceFile;
    }

    private GZIPOutputStream chooseOutputForCompression(){

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

        GZIPOutputStream gzipOutput = null;

        try {
            gzipOutput = new GZIPOutputStream(outputFile);
        }catch(IOException e){
            e.printStackTrace();
        }

        return gzipOutput;
    }

    private void compress(FileInputStream sourceFile, GZIPOutputStream outputFile){
        byte[] buffer = new byte[1024];

        try{

            System.out.println("Rozpoczęcie operacji.");

            GZIPOutputStream gzos = outputFile;
            FileInputStream in = sourceFile;
            int len;

            while ((len = in.read(buffer)) > 0) {
                gzos.write(buffer, 0, len);
            }

            in.close();
            gzos.finish();
            gzos.close();

            System.out.println("Koniec operacji.");

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
