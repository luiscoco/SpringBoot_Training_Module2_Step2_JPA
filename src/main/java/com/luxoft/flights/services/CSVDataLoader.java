package com.luxoft.flights.services;

import com.luxoft.flights.model.Airport;
import com.luxoft.flights.model.Carrier;
import com.luxoft.flights.model.Flight;
import com.luxoft.flights.model.State;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
@Primary
@Qualifier("csv")
@Profile("csv")
public class CSVDataLoader implements DataLoader {

    @Value("${csv.file.path}")
    private String csvFilename;

    private final DataService dataService;

    public CSVDataLoader(DataService dataService) {
        this.dataService = dataService;
    }

    public void loadData() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(csvFilename));
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");

        // Skip the first line
        lines = lines.subList(1, lines.size());

        for (String line : lines) {
            // skip empty lines
            if (line.isEmpty()) continue;

            String[] fields = line.split(",");

            State stateOrigin = new State(fields[7]);
            stateOrigin = dataService.saveState(stateOrigin);

            Airport origin = new Airport(Integer.parseInt(fields[5]), fields[6], stateOrigin);
            origin = dataService.saveAirport(origin);

            State stateDestination = new State(fields[10]);
            stateDestination = dataService.saveState(stateDestination);

            Airport destination = new Airport(Integer.parseInt(fields[8]), fields[9], stateDestination);
            destination = dataService.saveAirport(destination);

            Carrier carrier = new Carrier(fields[3]); // Map the code to the name
            carrier = dataService.saveCarrier(carrier);

            int depDelay = 0;
            if (!fields[12].isEmpty()) {
                depDelay = Integer.parseInt(fields[12]);
            }
            int arrDelay = 0;
            if (!fields[16].isEmpty()) {
                arrDelay = Integer.parseInt(fields[16]);
            }
            Flight flight = new Flight(
                    fields[4],
                    dateFormat.parse(fields[2]),
                    origin,
                    destination,
                    carrier,
                    depDelay,
                    arrDelay,
                    Integer.parseInt(fields[17]) // cancelled status
            );
            dataService.saveFlight(flight);
        }
    }
}