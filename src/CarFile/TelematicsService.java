package CarFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;



public class TelematicsService {
    public static void report(VehicleInfo vehicleInfo) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        String VINnumber = vehicleInfo.getVIN();
        objectMapper.writeValue(new File(VINnumber + ".json"), vehicleInfo);
        String json = objectMapper.writeValueAsString(vehicleInfo);
        System.out.println(VINnumber);
        System.out.println(json);
        System.out.println(vehicleInfo);

    }

    public static ArrayList<VehicleInfo> converter() throws IOException {
        File file = new File(".");
        ArrayList<VehicleInfo> a = new ArrayList<>();
        for (File V : file.listFiles()) {
            if (V.getName().endsWith(".json")) {
                ObjectMapper objectMapper = new ObjectMapper();
                VehicleInfo b = objectMapper.readValue(V, VehicleInfo.class);
                a.add(b);

            }
        }
        return a;
    }

    public static void Updater(ArrayList<VehicleInfo> vehicles) throws IOException {


        double avgMileage = 0;
        double avgGasConsumption = 0;
        double avgMilesUntilOilChange = 0;
        double avgEngineSize = 0;
        String vehicleHTML = "";

        String fileName = "dashboard.html";
        File file = new File(fileName);


        for(VehicleInfo vInfo : vehicles){
            avgMileage += vInfo.getMileage();
            avgEngineSize += vInfo.getEngineSize();
            avgGasConsumption += vInfo.getGasConsumption();
            avgMilesUntilOilChange += vInfo.getMilesUntilOilChange();
            vehicleHTML += "<tr>\n" +
                    "<th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
                    "</tr>\n" +
                    "<tr>\n" +
                    "<td align=\"center\">"+ vInfo.getVIN() +
                    "</td><td align=\"center\">"+ vInfo.getMileage() +
                    "</td><td align=\"center\">"+vInfo.getGasConsumption()+
                    "</td align=\"center\"><td align=\"center\">"+vInfo.getMilesUntilOilChange()+
                    "</td>\n" +
                    "</tr>";
        }
        avgMileage /= vehicles.size();
        avgGasConsumption /= vehicles.size();
        avgEngineSize /= vehicles.size();
        avgMilesUntilOilChange /= vehicles.size();

        String primaryHTML = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>Vehicle Telematics Dashboard</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1 align=\"center\">Averages for # vehicles</h1>\n" +
                "<table align=\"center\">\n" +
                "<tr>\n" +
                "<th>Odometer (miles) |</th>" +
                "<th>Consumption (gallons) |</th>" +
                "<th>Last Oil Change |</th>" +
                "<th>Engine Size (liters)</th>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "<td> align=\"center\">"+avgMileage+"</td>" +
                "<td> align=\"center\">"+avgGasConsumption+"</td>" +
                "<td> align=\"center\">"+avgMilesUntilOilChange+"</td>" +
                "<td> align=\"center\">"+avgEngineSize+"</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<br>" +
                "<h1 align=\"center\">History</h1>\n";


        primaryHTML += vehicleHTML;

        try {
            FileOutputStream stream = new FileOutputStream(file, false);
            byte[] myBytes = primaryHTML.getBytes();
            stream.write(myBytes);
            stream.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
