import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWether(String message,Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+message+"&units=metric&appid=d4f3c809c1f1a5693ebbf1767b126c3e");

        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";
        while (scanner.hasNext()){
            result += scanner.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray array = object.getJSONArray("weather");
        for (int i = 0;i <array.length();i++){
            JSONObject obj = array.getJSONObject(i);
            model.setIcon(obj.getString("icon"));
            model.setMain(obj.getString("main"));
        }
        String end = "City " + model.getName() +"\n" + "Temperature " + model.getTemp() +" C" + "\n" + "Humidity " + model.getHumidity() + " %" + " \n" +
                "Main "+ model.getMain()+ "\n" +  "https://openweathermap.org/img/w/"+ model.getIcon() + ".png";


        return end;

    }

}
