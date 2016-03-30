package credit_sys;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PoliceOffice {

    public static String crimeType(String crime1, String crime2, String judge) throws IOException {

        File aFile = new File("./ini/A类.ini");
        File bFile = new File("./ini/B类.ini");
        File cFile = new File("./ini/C类.ini");

        String crimeType = "";

        ArrayList<String> arrA = new ArrayList<String>();
        ArrayList<String> arrB = new ArrayList<String>();
        ArrayList<String> arrC = new ArrayList<String>();

        Scanner scA = new Scanner(aFile);
        while (scA.hasNextLine()) {
            arrA.add(scA.next());
        }
        Scanner scB = new Scanner(bFile);
        while (scB.hasNextLine()) {
            arrB.add(scB.next());
        }
        Scanner scC = new Scanner(cFile);
        while (scC.hasNextLine()) {
            arrC.add(scC.next());
        }
        scA.close();
        scB.close();
        scC.close();

        // A类犯罪记录判断
        for (String a : arrA) {
            if (judge.contains(a)) {
                crimeType = "A";
            }
        }

        // B类犯罪记录判断
        if (crimeType != "A") {
            for (String b : arrB) {
                if (crime1.contains(b) || crime2.contains(b)) {
                    crimeType = "B";
                }
            }
        }

        // C类犯罪记录判断
        if (crimeType != "A" || crimeType != "B") {
            for (String c : arrC) {
                if (judge.contains(c)) {
                    crimeType = "C";
                }
            }
        }

        return crimeType;
    }
}
