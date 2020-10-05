
/*@author Martha Baumgarten
 * 
 * This class contains the methods that answer questions 1-9
 * It reads in a file with flight information and stores the information in a set 
 * The information is then used to answer each question
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;

public class FlightDataAnalyst {
    private static Set<Flight> allFlights;

    // constructor
    public FlightDataAnalyst(String filename) {
	allFlights = new HashSet<Flight>();
	File flightsFile = new File(filename);
	try {
	    Scanner file_reader = new Scanner(flightsFile);
	    file_reader.nextLine();
	    while (file_reader.hasNextLine()) {
		Flight myFlight = new Flight(file_reader.nextLine());
		allFlights.add(myFlight);
	    }
	    file_reader.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    /*
     * question 1, method finds the airline with the most cancelled flights and the
     * rate of cancellation
     * 
     * @return carrier and percent of cancellations
     * 
     */
    public String findMostCancelledFlightsCarrier() {

	HashMap<String, Integer> total_flights = FlightDataAlgorithms.createTotalFlightsMapByCarrier(allFlights);

	HashMap<String, Integer> cancelled_flights = FlightDataAlgorithms
		.createCancelledFlightsMapByCarrier(allFlights);

	Set<String> carrierSet = FlightDataAlgorithms.createSetMapKeys(total_flights, cancelled_flights);

	HashMap<String, Double> carrCancellationsPerc = FlightDataAlgorithms.create_rate_mapByString(total_flights,
		cancelled_flights, carrierSet);

	String mostCancelledFlightCarrier = FlightDataAlgorithms.find_top_rate_and_key(carrCancellationsPerc);

	return mostCancelledFlightCarrier;
    }

    /*
     * question 2, finds the most common cause of cancellations
     * 
     * @return cancellation code
     */
    public String findCommonCauseCancellation() {

	ArrayList<String> cancellationCodes = new ArrayList<String>();
	for (Flight flight : allFlights) {
	    if (flight.isFlightCancelled()) {
		cancellationCodes.add(flight.getCancellationCode());
	    }
	}

	HashMap<String, Integer> cancelMap = new HashMap<String, Integer>();
	for (String cancCode : cancellationCodes) {
	    Integer codeCounter = cancelMap.get(cancCode);
	    if (codeCounter == null) {
		codeCounter = 0;
	    }
	    ++codeCounter;
	    cancelMap.put(cancCode, codeCounter);
	}

	String mostCommonCauseCancellation = FlightDataAlgorithms.find_top_int_and_key(cancelMap);
	return mostCommonCauseCancellation;
    }

    /*
     * question 3, find the plane that logged the most miles
     * 
     * @return tail number
     */
    public String findPlaneFlewFurthest() {
	HashMap<String, Integer> planeDistanceMap = new HashMap<String, Integer>();
	for (Flight flight : allFlights) {
	    if (!flight.isFlightCancelled() && !flight.isFlightDiverted()) {
		Integer total_distance = planeDistanceMap.get(flight.getTailNum());
		if (total_distance == null) {
		    total_distance = 0;
		}
		Integer distance_i = Integer.parseInt(flight.getFlightDistance());
		total_distance += distance_i;
		planeDistanceMap.put(flight.getTailNum(), total_distance);
	    }
	}

	String farthestTraveledPlane = FlightDataAlgorithms.find_top_int_and_key(planeDistanceMap);
	return farthestTraveledPlane;

    }

    /*
     * question 4, finds the airport with the most flights in and out
     * 
     * @return airport ID
     */
    public String findBusiestAirport() {
	HashMap<String, Integer> DepartingFlights = FlightDataAlgorithms.createDeparturesMapByAirport(allFlights);

	HashMap<String, Integer> IncomingFlights = FlightDataAlgorithms.createIncomingFlightsMapByAirport(allFlights);

	Set<String> total_Airports = FlightDataAlgorithms.createSetMapKeys(DepartingFlights, IncomingFlights);

	HashMap<String, Integer> total_flights = new HashMap<String, Integer>();
	for (String ap : total_Airports) {
	    Integer dep_flight_count = DepartingFlights.get(ap);
	    if (dep_flight_count == null) {
		dep_flight_count = 0;
	    }
	    Integer IF_count = IncomingFlights.get(ap);
	    if (IF_count == null) {
		IF_count = 0;
	    }
	    total_flights.put(ap, IF_count + dep_flight_count);

	}

	String busiestAirport = FlightDataAlgorithms.find_top_int_and_key(total_flights);
	return busiestAirport;

    }

    /*
     * question 5, finds the airport with the most departing flights
     * 
     * @return airport ID
     */
    public String findBiggestSourcePlanes() {
	HashMap<String, Integer> DepartingFlights = FlightDataAlgorithms.createDeparturesMapByAirport(allFlights);

	HashMap<String, Integer> IncomingFlights = FlightDataAlgorithms.createIncomingFlightsMapByAirport(allFlights);

	Set<String> total_Airports = FlightDataAlgorithms.createSetMapKeys(DepartingFlights, IncomingFlights);

	HashMap<String, Integer> source_flights = new HashMap<String, Integer>();
	for (String ap : total_Airports) {
	    Integer dep_flight_count = DepartingFlights.get(ap);
	    if (dep_flight_count == null) {
		dep_flight_count = 0;
	    }
	    Integer IF_count = IncomingFlights.get(ap);
	    if (IF_count == null) {
		IF_count = 0;
	    }
	    source_flights.put(ap, dep_flight_count - IF_count);

	}

	String biggest_source_planes = FlightDataAlgorithms.find_top_int_and_key(source_flights);
	return biggest_source_planes;

    }

    /*
     * question 6, finds the airport with the most incoming flights
     * 
     * @return airport ID
     */
    public String findBiggestSinkPlanes() {
	HashMap<String, Integer> DepartingFlights = FlightDataAlgorithms.createDeparturesMapByAirport(allFlights);

	HashMap<String, Integer> IncomingFlights = FlightDataAlgorithms.createIncomingFlightsMapByAirport(allFlights);

	Set<String> total_Airports = FlightDataAlgorithms.createSetMapKeys(DepartingFlights, IncomingFlights);

	HashMap<String, Integer> sink_flights = new HashMap<String, Integer>();
	for (String ap : total_Airports) {
	    Integer dep_flight_count = DepartingFlights.get(ap);
	    if (dep_flight_count == null) {
		dep_flight_count = 0;
	    }
	    Integer IF_count = IncomingFlights.get(ap);
	    if (IF_count == null) {
		IF_count = 0;
	    }
	    sink_flights.put(ap, IF_count - dep_flight_count);

	}

	String biggest_sink_planes = FlightDataAlgorithms.find_top_int_and_key(sink_flights);
	return biggest_sink_planes;

    }

    /*
     * question 7, finds the number of AA flights that were delayed by over an hour
     * 
     * @return number of flights
     */
    public int findVeryLateAAPlanes() {
	Set<Flight> AA_Flight_Set = new HashSet<Flight>();
	for (Flight flight : allFlights) {
	    if (flight.getUniqueCarrier().equals("AA")) {
		if (!flight.isFlightCancelled() && !flight.isFlightDiverted()) {
		    AA_Flight_Set.add(flight);

		}
	    }
	}

	int flight_counter = 0;
	for (Flight flight : AA_Flight_Set) {
	    if (!flight.isFlightCancelled() && !flight.isFlightDiverted()) {
		String d_delay = flight.getDepDelayMinutes();
		String a_delay = flight.getArrDelayMinutes();
		int dep_delay_min = Integer.parseInt(d_delay);
		int arr_delay_min = Integer.parseInt(a_delay);
		if (dep_delay_min >= 60 || arr_delay_min >= 60) {
		    flight_counter++;
		}
	    }
	}
	return flight_counter;
    }

    /*
     * question 8, finds the flight that made up the largest delay
     * 
     * @return day of month, minutes, tail number
     */
    public String findLargestMadeUpDelay() {
	HashMap<Flight, Integer> del_dep_flights = new HashMap<Flight, Integer>();
	for (Flight flight : allFlights) {
	    if (!flight.isFlightCancelled() && !flight.isMissingData() && !flight.isFlightDiverted()) {
		if (Integer.parseInt(flight.getDepDelayMinutes()) > 0) {
		    if (flight.isFlightEarly() || flight.isFlightOnTime()) {
			Integer dep_minutes = del_dep_flights.get(flight);
			if (dep_minutes == null) {
			    dep_minutes = 0;
			}
			dep_minutes = Integer.parseInt(flight.getDepDelayMinutes());
			del_dep_flights.put(flight, dep_minutes);

		    }
		}
	    }
	}

	Flight best_makeup_flight = FlightDataAlgorithms.find_top_int_and_flight(del_dep_flights);
	StringBuilder bestMadeUpFlightSB = new StringBuilder();

	bestMadeUpFlightSB = bestMadeUpFlightSB.append(best_makeup_flight.getDayOfMonth() + ","
		+ best_makeup_flight.getDepDelayMinutes() + "," + best_makeup_flight.getTailNum());

	String answer = bestMadeUpFlightSB.toString();

	return answer;
    }

    /*
     * question 9, finds the carrier with the highest rate of delay
     * 
     * @return carrier and percent of delays
     */
    public String findMostDelayedCarrier() {
	HashMap<String, Integer> total_flights = FlightDataAlgorithms.createTotalFlightsMapByCarrier(allFlights);

	HashMap<String, Integer> delayed_flights = FlightDataAlgorithms.createDelayedFlightMapByCarrier(allFlights);

	Set<String> carrierSet = FlightDataAlgorithms.createSetMapKeys(total_flights, delayed_flights);

	HashMap<String, Double> carrDelayRate = FlightDataAlgorithms.create_rate_mapByString(total_flights,
		delayed_flights, carrierSet);

	String mostDelayedCarrier = FlightDataAlgorithms.find_top_rate_and_key(carrDelayRate);

	return mostDelayedCarrier;

    }

}
