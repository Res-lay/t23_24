package task24;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class Data{
    public List<Map<String, String>> lines;
    public Map<Integer, List<String>> station_names;

    public Data(List<Map<String, String>> lines, Map<Integer, List<String>> stations)
    {
        this.lines = lines;
        this.station_names = stations;
    }

    public List<Map<String, String>> getLines() {
        return lines;
    }

    public Map<Integer, List<String>> getStation_names() {
        return station_names;
    }
}

public class App {
    public static void main(String[] args) {
        List<Map<String, String>> lines = new ArrayList<>();
        Map<Integer, List<String>> stations = new HashMap<>();
        Map<String, String> line;
        List<String> names;
        String path = "D:\\Studying\\untitled2\\src\\main\\java\\task24\\test.json";
        try {
            org.jsoup.nodes.Document document = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
            Integer k = 1;
            org.jsoup.select.Elements lineElement = document.getElementsByAttributeValueContaining("class", "js-metro-line");
            for (org.jsoup.nodes.Element element : lineElement) {
                line = new HashMap<>();
                line.put("number", Integer.toString(k));
                line.put("name", element.text());
                lines.add(line);
                k++;
            }
            Integer counter = 1;
            org.jsoup.select.Elements stationElement = document.getElementsByAttributeValueContaining("class", "js-metro-stations");
            for (org.jsoup.nodes.Element element : stationElement) {
                names = new ArrayList<>();
                org.jsoup.select.Elements name = element.getElementsByAttributeValueContaining("class", "name");
                for (org.jsoup.nodes.Element elem : name) {
                    names.add(elem.text());
                }
                stations.put(counter, names);
                counter++;
            }
            Data data = new Data(lines, stations);
            Gson gson = null;
            try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
                gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonString = gson.toJson(data);
                out.write(jsonString);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Data read_data = gson.fromJson(new FileReader(path), Data.class);
            stations = read_data.getStation_names();
            for(int i = 1; i < stations.size() + 1; i++)
            {
                System.out.println(stations.get(i).size());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

