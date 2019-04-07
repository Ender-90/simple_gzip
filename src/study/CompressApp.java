package study;

import java.util.Scanner;

public class CompressApp {

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
        Scanner sc = new Scanner(System.in);
        int input = 0;
        while(input != 1 & input != 2){
            input = sc.nextInt();
        }
        return input;
    }

    private void compressOperation(){

    }

    private void decompressOperation(){

    }

}
