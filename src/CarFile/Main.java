package CarFile;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Main {
    public static void main(String [ ] args){
        VehicleInfo vehicleInfo = new VehicleInfo();

        System.out.println("Enter the VIN");
        Scanner scan = new Scanner(System.in);
        String VIN = scan.next();

        System.out.println("Enter the Miles");
        double mileage = scan.nextDouble();

        System.out.println("Enter the gas consumption");
        double gasConsumption = scan.nextDouble();

        System.out.println("Enter how many miles before your next oil change");
        double milesUntilOilChange = scan.nextDouble();

        System.out.println("Enter the liter amount on your engine (Example 4, 4.5, 6, 8");
        double engineSize = scan.nextDouble();


        vehicleInfo.setEngineSize(engineSize);
        vehicleInfo.setGasConsumption(gasConsumption);
        vehicleInfo.setMileage(mileage);
        vehicleInfo.setMilesUntilOilChange(milesUntilOilChange);
        vehicleInfo.setVIN(VIN);

        ArrayList<VehicleInfo> list = new ArrayList<>();

      try{
          TelematicsService.report(vehicleInfo);
          System.out.println(vehicleInfo);
      } catch (IOException e) {
          e.printStackTrace();
      }
      try{
          list  = TelematicsService.converter();
      } catch (IOException e) {
          e.printStackTrace();
      }
      try {
          TelematicsService.updater(list);
      } catch (IOException e) {
          e.printStackTrace();
      }

    }
}
