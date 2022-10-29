package task23;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

public class app {
    public static void main(String[] args) throws IOException, CsvValidationException {
        float income = 0;
        int i = 0;
        HashMap<String, Float> table = new HashMap<>();
        float expenses = 0;
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("D:\\Studying\\untitled2\\src\\main\\java\\task23\\movementList.csv"), "utf-8"));
        String[] nextline;
        Vector<String> v = new Vector<>();
        while((nextline = reader.readNext()) != null){
            if(nextline != null){
                if(i != 0) {
                    String[] arr;
                    if((nextline[5].split("     ")[0].split("   ").length) >= 2) {
                        arr = nextline[5].split("     ")[0].split("   ")[1].split(" ");
                        if(!table.containsKey(arr[arr.length - 1])){
                            table.put(arr[arr.length - 1], Float.parseFloat(nextline[nextline.length - 1].replace(",",".")));
                            v.add(arr[arr.length - 1]);
                        }
                        else if(table.containsKey(arr[arr.length - 1])){
                            table.put(arr[arr.length - 1], table.get(arr[arr.length - 1]) + Float.parseFloat(nextline[nextline.length - 1].replace(",",".")));
                        }
                    }

                    expenses += Float.parseFloat(nextline[nextline.length - 1].replace(",","."));
                    income += Float.parseFloat(nextline[nextline.length - 2].replace(",","."));
                }
                i++;
            }
        }
        System.out.println("Суммы расходов по организациям:");
        for(String k: v) {
            System.out.println(k + " " + table.get(k));
        }
        System.out.println("\nСумма расходов: " + expenses);
        System.out.println("Сумма доходов: " + income);
    }
}
