import org.json.simple.JSONObject;

import java.util.Scanner;

import fileReader.FileReader;
import fileWriter.FileWriter;

public class Main {

    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        JSONObject object = new JSONObject();
        System.out.println("Kas kasutada konsooli või faili (KONSOOL || FAIL): ");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        System.out.println("Te valisite: " + option);
        switch (option) {
            case "FAIL":
                // valiti fail, tee mis vaja
                System.out.println(fileReader.readInputDataFromFile("inputFile.txt"));
                object.put("cityName", "Tallinn");
                object.put("countryCode", "EE");
                object.put("metricUnits", "metric");
                break;
            case "KONSOOL":
                // valiti konsool, küsi uuesti midagi ja tee mis vaja
                System.out.println("Kirjutage linna nimi või mis sinna vaja vms: ");
                String city = scanner.nextLine();
                System.out.println("Te valisite: " + city);
                break;
            default:
                System.out.println("TE KIRJUTASITE MIDAGI VALESTI");
                break;
        }

        FileWriter.writeJsonDataToFile(object, "outputFile.txt");
    }
}
