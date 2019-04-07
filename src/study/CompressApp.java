package study;

import java.util.Scanner;

public class CompressApp {

    Scanner sc = new Scanner(System.in);

    public void run(){
        int operation = chooseOperation();
        switch (operation){
            case 1:
                CompressModule cm = new CompressModule(sc);
                cm.compressOperation();
                break;
            case 2:
                DecompressModule dm = new DecompressModule(sc);
                dm.decompressOperation();
                break;
        }
        sc.close();
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
}
