/**
 * Created by LIKHTAROVICH on 25.10.2017.
 */

import java.io.File;
import java.io.FilenameFilter;

/*Пример параметров командной строки при запуске программы
* "C:\Program Files" "xml" "src\results.xml"*/

public class Main {


    public static void main(String[] args) {

        if (args.length >= 3) {
            findFiles(args[args.length - 2], args, args[args.length - 1]);

        } else {
            System.out.println("Не указана параметры командной строки");
            System.out.println("Пример параметров командной строки при запуске программы " +
                    "\"C:\\Program Files\" \"xml\" \"src\\results.xml\"");
            System.exit(-1);
        }


    }

    // метод поиска
    private static void findFiles(String ext, String[] args, String result) {
        for (int i = 0; i < args.length - 2; i++) {


            File file = new File(args[i]);
            if (!file.exists()) System.out.println(i + " папка не существует");


            File[] listFiles = file.listFiles(new MyFileNameFilter(ext));
            if (listFiles.length == 0) {
                System.out.println(args[i] + " не содержит файлов с расширением " + ext);
            } else {
                for (File f : listFiles) {
                    System.out.println(args[i] + File.separator + f.getName());
                    CreateXML.createXML(listFiles, args[i],result);
                }

            }
        }
    }

    // Реализация интерфейса FileNameFilter
    public static class MyFileNameFilter implements FilenameFilter {

        private String ext;

        public MyFileNameFilter(String ext) {
            this.ext = ext.toLowerCase();
        }

        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(ext);
        }


    }


}
