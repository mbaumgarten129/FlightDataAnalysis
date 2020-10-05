/*@author Martha Baumgarten
 * 
 * This class yields a "Flight" object created from a row in a flight file
 * 
 */

public class Flight {
    private String[] flightInfo;
    private String DayOfMonth;
    private String DayOfWeek;
    private String FlightDate;
    private String UniqueCarrier;
    private String TailNum;
    private String OriginAirportId;
    private String OriginAirport;
    private String OriginStateName;
    private String DestAirportId;
    private String DestinationAirport;
    private String DestStateName;
    private String DepartTime;
    private String DepDelayMinutes;
    private String WheelsOffTime;
    private String WheelsOnTime;
    private String ArrivalTime;
    private String ArrDelayMinutes;
    private String CancellationCode;
    private String Cancellation;
    private String Diversion;
    private String AirTimeMinutes;
    private String FlightDistance;

    // constructor
    public Flight(String row) {
	flightInfo = row.split(",");
	this.DayOfMonth = flightInfo[0];
	this.DayOfWeek = flightInfo[1];
	this.FlightDate = flightInfo[2];
	this.UniqueCarrier = flightInfo[3];
	this.TailNum = flightInfo[4];
	this.OriginAirportId = flightInfo[5];
	this.OriginAirport = flightInfo[6];
	this.OriginStateName = flightInfo[7];
	this.DestAirportId = flightInfo[8];
	this.DestinationAirport = flightInfo[9];
	this.DestStateName = flightInfo[10];
	this.DepartTime = flightInfo[11];
	this.DepDelayMinutes = flightInfo[12];
	this.WheelsOffTime = flightInfo[13];
	this.WheelsOnTime = flightInfo[14];
	this.ArrivalTime = flightInfo[15];
	this.ArrDelayMinutes = flightInfo[16];
	this.Cancellation = flightInfo[17];
	this.CancellationCode = flightInfo[18];
	this.Diversion = flightInfo[19];
	this.AirTimeMinutes = flightInfo[20];
	this.FlightDistance = flightInfo[21];

    }

    /*
     * get day of the month
     * 
     * @return day of month of that flight
     */
    public String getDayOfMonth() {
	return DayOfMonth;
    }

    /*
     * get day of the week
     * 
     * @return day of the week of that flight
     */
    public String getDayOfWeek() {
	return DayOfWeek;
    }

    /*
     * get flight date
     * 
     * @return date of that flight
     */
    public String getFlightDate() {
	return FlightDate;
    }

    /*
     * get airline
     * 
     * @return carrier of that flight
     */
    public String getUniqueCarrier() {
	return UniqueCarrier;
    }

    /*
     * get plane's tail number
     * 
     * @return tail number of that flight
     */
    public String getTailNum() {
	return TailNum;
    }

    /*
     * get airport ID of flight's origin
     * 
     * @return airport ID of flight's origin
     */
    public String getOriginAirportId() {
	return OriginAirportId;
    }

    /*
     * get name of airport of flight's origin
     * 
     * @return airport name of flight's origin
     */
    public String getOriginAirport() {
	return OriginAirport;
    }

    /*
     * get the state of flight's origin
     * 
     * @return state of flight's origin
     */
    public String getOriginStateName() {
	return OriginStateName;
    }

    /*
     * get airport ID of flight's destination
     * 
     * @return airport ID of flight's destination
     */
    public String getDestAirportId() {
	return DestAirportId;
    }

    /*
     * get flight destination's airport name
     * 
     * @return name of destination airport
     */
    public String getDestinationAirport() {
	return DestinationAirport;
    }

    /*
     * get the state of flight's destination
     * 
     * @return state of flight's destination
     */
    public String getDestStateName() {
	return DestStateName;
    }

    /*
     * get departure time
     * 
     * @return departure time of flight
     */
    public String getDepartTime() {
	return DepartTime;
    }

    /*
     * get departure delay minutes
     * 
     * @return number of minutes delayed in departure
     */
    public String getDepDelayMinutes() {
	return DepDelayMinutes;
    }

    /*
     * get time that plane got off the ground
     * 
     * @return time the plane got off the ground
     */
    public String getWheelsOffTime() {
	return WheelsOffTime;
    }

    /*
     * get time that plane touched down
     * 
     * @return time plane touched down
     */
    public String getWheelsOnTime() {
	return WheelsOnTime;
    }

    /*
     * get flight's arrival time
     * 
     * @return flight's arrival time
     */
    public String getArrivalTime() {
	return ArrivalTime;
    }

    /*
     * get arrival delay minutes
     * 
     * @return number of minutes delayed in arrival
     */
    public String getArrDelayMinutes() {
	return ArrDelayMinutes;
    }

    // checks if flight is cancelled
    public boolean isFlightCancelled() {
	return Integer.parseInt(Cancellation) == 1;
    }

    // checks if there is any missing information about the flight
    public boolean isMissingData() {
	return (DayOfMonth.isEmpty() | DayOfWeek.isEmpty() | FlightDate.isEmpty() | UniqueCarrier.isEmpty()
		| TailNum.isEmpty() | OriginAirportId.isEmpty() | OriginAirport.isEmpty() | OriginStateName.isEmpty()
		| DestAirportId.isEmpty() | DestinationAirport.isEmpty() | DestStateName.isEmpty()
		| DepartTime.isEmpty() | DepDelayMinutes.isEmpty() | WheelsOffTime.isEmpty() | WheelsOnTime.isEmpty()
		| ArrivalTime.isEmpty() | ArrDelayMinutes.isEmpty() | Diversion.isEmpty() | AirTimeMinutes.isEmpty()
		| FlightDistance.isEmpty());

    }

    // checks if flight is delayed
    public boolean isFlightDelayed() {
	return (!isFlightCancelled() && Integer.parseInt(getArrDelayMinutes()) > 0);
    }

    // checks if flight is early
    public boolean isFlightEarly() {
	return (!isFlightCancelled() && Integer.parseInt(getArrDelayMinutes()) < 0);
    }

    // checks if flight is on time
    public boolean isFlightOnTime() {
	return (!isFlightCancelled() && Integer.parseInt(getArrDelayMinutes()) == 0);
    }

    /*
     * get flight cancellation code
     * 
     * @return cancellation code
     */
    public String getCancellationCode() {
	return CancellationCode;
    }

    // checks if flight was diverted
    public boolean isFlightDiverted() {
	return Integer.parseInt(Diversion) == 1;
    }

    /*
     * get time in the air
     * 
     * @return air time
     */
    public String getAirTimeMinutes() {
	return AirTimeMinutes;
    }

    /*
     * get flight distance
     * 
     * @return flight distance
     */
    public String getFlightDistance() {
	return FlightDistance;
    }

}
