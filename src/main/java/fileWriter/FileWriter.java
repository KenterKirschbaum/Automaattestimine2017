package fileWriter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileWriter {

    Writer writer = null;

    public void writeData(String cityName, Double latitude, Double longitude, Double minTemp, Double maxTemp, Double currentWeather) {
            try {
                writer = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(cityName + ".txt"), "utf-8"));
                writer.write("City: " + cityName);
                ((BufferedWriter) writer).newLine();
                writer.write("Coordinates: Latitude: " + latitude + ", Longitude: " + longitude);
                ((BufferedWriter) writer).newLine();
                writer.write("Minimum temp.: " + minTemp + ", " + "Maximum temp. : " + maxTemp);
                ((BufferedWriter) writer).newLine();
                writer.write("Current weather: " + currentWeather);
            } catch (IOException exception) {
                System.out.println("Error ooccured");
            } finally {
                try {writer.close();} catch (Exception exception) {/*ignore*/}
            }
    }
}