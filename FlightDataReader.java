/*@author Martha Baumgarten
 * 
 * 
 * This class contains a method that calls the methods that answer questions 1-9 and formats the answers and writes them to an answer file
 */
public class FlightDataReader {

    public FlightDataReader() {

    }

    // This method answers the questions and writes the answers to a new file
    public void readData() {
	FlightDataAnalyst myFDA = new FlightDataAnalyst("flights.csv");
	FormattedOutput myFO = new FormattedOutput();
	myFO.addAnswer(1, (myFDA.findMostCancelledFlightsCarrier()));
	myFO.addAnswer(2, (myFDA.findCommonCauseCancellation()));
	myFO.addAnswer(3, (myFDA.findPlaneFlewFurthest()));
	myFO.addAnswer(4, (myFDA.findBusiestAirport()));
	myFO.addAnswer(5, (myFDA.findBiggestSourcePlanes()));
	myFO.addAnswer(6, (myFDA.findBiggestSinkPlanes()));
	myFO.addAnswer(7, (myFDA.findVeryLateAAPlanes()));
	myFO.addAnswer(8, (myFDA.findLargestMadeUpDelay()));
	myFO.addAnswer(9,
		"Question 9: Which carrier has the highest rate of delay? " + (myFDA.findMostDelayedCarrier()));
	myFO.writeAnswers();

    }

}
