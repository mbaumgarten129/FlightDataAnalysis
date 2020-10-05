
/*@author Martha Baumgarten
 * This class contains helper methods for answering questions 1-9
 * The methods create maps, sets, and sort to find the highest value
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class FlightDataAlgorithms {

    public FlightDataAlgorithms() {

    }

    /*
     * creates a map of with a key of airport codes and a value equal to the number
     * of departing flights per airport
     * 
     * @param flight set
     * 
     * @return map of airport codes and number of departing flights in that airport
     */
    public static HashMap<String, Integer> createDeparturesMapByAirport(Set<Flight> allFlights) {
	HashMap<String, Integer> departingFlightsMap = new HashMap<String, Integer>();
	for (Flight flight : allFlights) {
	    if (!flight.isFlightCancelled() && !flight.isFlightDiverted()) {
		Integer dep_flight_count = departingFlightsMap.get(flight.getOriginAirportId());
		if (dep_flight_count == null) {
		    dep_flight_count = 0;
		}
		++dep_flight_count;
		departingFlightsMap.put(flight.getOriginAirportId(), dep_flight_count);
	    }
	}
	return departingFlightsMap;
    }

    /*
     * creates a map of with a key of airport codes and a value equal to the number
     * of incoming flights per airport
     * 
     * @param flight set
     * 
     * @return map of airport codes and number of incoming flights in that airport
     */
    public static HashMap<String, Integer> createIncomingFlightsMapByAirport(Set<Flight> allFlights) {
	HashMap<String, Integer> incomingFlightsMap = new HashMap<String, Integer>();
	for (Flight flight : allFlights) {
	    if (!flight.isFlightCancelled() && !flight.isFlightDiverted()) {
		Integer IF_count = incomingFlightsMap.get(flight.getDestAirportId());
		if (IF_count == null) {
		    IF_count = 0;
		}
		++IF_count;
		incomingFlightsMap.put(flight.getDestAirportId(), IF_count);
	    }
	}
	return incomingFlightsMap;
    }

    /*
     * creates a map with a key of carriers and a value equal to the number of
     * delayed flights per carrier
     * 
     * @param flight set
     * 
     * @return map of carriers and number of delayed flights per carrier
     */
    public static HashMap<String, Integer> createDelayedFlightMapByCarrier(Set<Flight> allFlights) {
	HashMap<String, Integer> DelayedFlights = new HashMap<String, Integer>();
	for (Flight flight : allFlights) {
	    if (!flight.isFlightCancelled() && !flight.isFlightDiverted() && !flight.isMissingData()) {
		if (flight.isFlightDelayed()) {
		    Integer delFlightCount = DelayedFlights.get(flight.getUniqueCarrier());
		    if (delFlightCount == null) {
			delFlightCount = 0;
		    }
		    ++delFlightCount;
		    DelayedFlights.put(flight.getUniqueCarrier(), delFlightCount);
		}
	    }
	}
	return DelayedFlights;
    }

    /*
     * creates a map with a key of carriers and a value equal to the total number of
     * flights per carrier
     * 
     * @param fligh set
     * 
     * @return map of carriers and flights per carrier
     */
    public static HashMap<String, Integer> createTotalFlightsMapByCarrier(Set<Flight> allFlights) {
	HashMap<String, Integer> carrTotalFlightsMap = new HashMap<String, Integer>();
	for (Flight flight : allFlights) {
	    if (!flight.isFlightDiverted()) {
		Integer TotalFlightCount = carrTotalFlightsMap.get(flight.getUniqueCarrier());
		if (TotalFlightCount == null) {
		    TotalFlightCount = 0;
		}
		++TotalFlightCount;
		carrTotalFlightsMap.put(flight.getUniqueCarrier(), TotalFlightCount);
	    }
	}
	return carrTotalFlightsMap;
    }

    /*
     * creates a map with a key of carriers and a value equal to the number of
     * cancelled flights per carrier
     * 
     * @param flight set
     * 
     * @return map of carriers and number of cancelled flights per carrier
     */
    public static HashMap<String, Integer> createCancelledFlightsMapByCarrier(Set<Flight> allFlights) {
	HashMap<String, Integer> carrCancFlights = new HashMap<String, Integer>();
	for (Flight cancFlight : allFlights) {
	    if (cancFlight.isFlightCancelled()) {
		Integer cancelFlightCount = carrCancFlights.get(cancFlight.getUniqueCarrier());
		if (cancelFlightCount == null) {
		    cancelFlightCount = 0;
		}
		++cancelFlightCount;
		carrCancFlights.put(cancFlight.getUniqueCarrier(), cancelFlightCount);
	    }
	}
	return carrCancFlights;
    }

    /*
     * creates a set of map keys from two maps
     * 
     * @param two maps
     * 
     * @return set of keys from two maps
     */
    public static Set<String> createSetMapKeys(HashMap<String, Integer> map1, HashMap<String, Integer> map2) {
	Set<String> keys_set = new HashSet<String>();
	keys_set.addAll(map1.keySet());
	keys_set.addAll(map2.keySet());
	return keys_set;
    }

    /*
     * creates a map with a key of strings and value equal to rate of map2's values
     * over map1's values
     * 
     * @param map 1
     * 
     * @param map 1
     * 
     * @param set 1
     * 
     * @return map of string keys and rate of count of map2/map1
     */
    public static HashMap<String, Double> create_rate_mapByString(HashMap<String, Integer> map1,
	    HashMap<String, Integer> map2, Set<String> set1) {
	HashMap<String, Double> rate_map = new HashMap<String, Double>();
	for (String string : set1) {
	    Integer map1_Count = map1.get(string);
	    if (map1_Count == null) {
		map1_Count = 0;
	    }
	    Integer map2_Count = map2.get(string);
	    if (map2_Count == null) {
		map2_Count = 0;
	    }
	    rate_map.put(string, map2_Count / map1_Count.doubleValue());

	}
	return rate_map;

    }

    /*
     * returns the string key that is associated with the top rate value in a map
     * 
     * @param map
     * 
     * @return string key that holds the highest rate
     */
    public static String find_top_rate_and_key(HashMap<String, Double> map) {

	ArrayList<Double> rate_list = new ArrayList<Double>();
	for (String key : map.keySet()) {
	    Double rates = map.get(key);
	    rate_list.add(rates);
	}

	String most_key_string = "";
	Double highestRate = Collections.max(rate_list);
	for (String key : map.keySet()) {
	    if (highestRate.equals(map.get(key))) {
		most_key_string = key;

	    }
	}

	StringBuilder key_rateSB = new StringBuilder();
	key_rateSB = key_rateSB.append(most_key_string + "," + (highestRate * 100) + "%");
	String most_key_rate = key_rateSB.toString();
	return most_key_rate;
    }

    /*
     * returns a string key that is associated with the top integer value in a map
     * 
     * @param map
     * 
     * @return string key that holds the highest integer value
     */
    public static String find_top_int_and_key(HashMap<String, Integer> map) {

	ArrayList<Integer> int_List = new ArrayList<Integer>();
	for (String key : map.keySet()) {
	    Integer ints = map.get(key);
	    int_List.add(ints);
	}

	Integer highest_int = 0;
	String most_key_string = "";
	highest_int = Collections.max(int_List);
	for (String key : map.keySet()) {
	    if (highest_int.equals(map.get(key))) {
		most_key_string = key;

	    }
	}
	return most_key_string;

    }

    /*
     * returns a flight key that is associated with the top integer value in a map
     * 
     * @param map
     * 
     * @return flight key that holds the highest integer value
     */
    public static Flight find_top_int_and_flight(HashMap<Flight, Integer> map) {

	ArrayList<Integer> ints_List = new ArrayList<Integer>();
	for (Flight key : map.keySet()) {
	    Integer ints = map.get(key);
	    ints_List.add(ints);
	}

	Flight bestFlight = null;
	Integer largest_num = Collections.max(ints_List);
	for (Flight flight : map.keySet()) {

	    if (largest_num.equals(map.get(flight))) {
		bestFlight = flight;
	    }

	}
	return bestFlight;

    }

}
