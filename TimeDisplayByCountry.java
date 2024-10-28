import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TimeDisplayByCountry {

    private static Map<String, String> countryToTimeZoneMap = new HashMap<>();

    static {
        countryToTimeZoneMap.put("USA", "America/New_York");
        countryToTimeZoneMap.put("UK", "Europe/London");
        countryToTimeZoneMap.put("Japan", "Asia/Tokyo");
        countryToTimeZoneMap.put("Australia", "Australia/Sydney");
        countryToTimeZoneMap.put("India", "Asia/Kolkata");
        countryToTimeZoneMap.put("China", "Asia/Shanghai");
        countryToTimeZoneMap.put("Brazil", "America/Sao_Paulo");
        countryToTimeZoneMap.put("South Africa", "Africa/Johannesburg");
        countryToTimeZoneMap.put("France", "Europe/Paris");
        countryToTimeZoneMap.put("Germany", "Europe/Berlin");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Here are some example countries you can use:");
            for (String country : countryToTimeZoneMap.keySet()) {
                System.out.println(country);
            }

            System.out.println("Enter the country name you are currently in (e.g., USA, UK, India): ");
            String inputCountry = scanner.nextLine();
            
            String inputTimeZone = countryToTimeZoneMap.get(inputCountry);
            if (inputTimeZone == null) {
                System.out.println("Invalid country entered for input. Please try again.");
                return;
            }

            //  (HH:mm)
            System.out.println("Enter the time in " + inputCountry + " (HH:mm): ");
            String userInputTime = scanner.nextLine();
            LocalTime localTime = LocalTime.parse(userInputTime);

            // target country name
            System.out.println("Enter the country name you want to convert the time to (e.g., USA, UK, India): ");
            String outputCountry = scanner.nextLine();

            // output country
            String outputTimeZone = countryToTimeZoneMap.get(outputCountry);
            if (outputTimeZone == null) {
                System.out.println("Invalid country entered for output. Please try again.");
                return;
            }

            // Convert the time from the input country's time zone to the output country's time zone
            ZoneId inputZoneId = ZoneId.of(inputTimeZone);
            ZonedDateTime inputZonedDateTime = ZonedDateTime.now(inputZoneId).with(localTime);

            ZoneId outputZoneId = ZoneId.of(outputTimeZone);
            ZonedDateTime outputZonedDateTime = inputZonedDateTime.withZoneSameInstant(outputZoneId);

            // Format and display the output time in the target time zone
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm z");
            System.out.println("Time in " + outputCountry + " (" + outputTimeZone + ") is: " + outputZonedDateTime.format(formatter));

        } catch (Exception e) {
            System.out.println("Invalid input or country. Please try again. Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
