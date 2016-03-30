package credit_sys;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

    public static void importLog(short sign, int rows, String date, String time) {

        String info = "";
        switch (sign) {
            case 0:
                info = "工商银行人口办基本信息";
                break;
            case 1:
                info = "工商银行803信息";
                break;
            case 2:
                info = "交通银行人口办基本信息";
                break;
            case 3:
                info = "交通银行803信息";
                break;
        }
        
        File dictionary = new File("./log");
        if (!dictionary.exists()) dictionary.mkdirs();
        File file = new File("./log/" + date + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        FileWriter fwriter;
        try {
            fwriter = new FileWriter(file, true);
            fwriter.write("[" + time + "]\n");
            fwriter.write("导入" + info + "数据,共影响" + rows + "行...");
            fwriter.flush();
            fwriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void exportLog(String info, String date, String time, int rows) {
        File dictionary = new File("./log");
        if (!dictionary.exists()) dictionary.mkdirs();
        File file = new File("./log/" + date + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        FileWriter fwriter;
        try {
            fwriter = new FileWriter(file, true);
            fwriter.write("[" + time + "]\n");
            fwriter.write("导出" + info + "数据,共" + rows + "行...");
            fwriter.flush();
            fwriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void deleteLog() {

    }
}
