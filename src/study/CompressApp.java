package study;

import java.io.*;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CompressApp {

    Scanner sc = new Scanner(System.in);

    public void run(){
        int operation = chooseOperation();
        switch (operation){
            case 1:
                compressOperation();
                break;
            case 2:
                decompressOperation();
                break;
        }

    }

    private int chooseOperation(){
        System.out.println("Wybierz operację:\n1.Kompresja pliku\n2. Dekompresja pliku");
        int input = 0;
        while(input != 1 & input != 2){
            input = sc.nextInt();
        }
        sc.nextLine();
        return input;
    }

    private void compressOperation(){
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

    private void decompressOperation(){
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
