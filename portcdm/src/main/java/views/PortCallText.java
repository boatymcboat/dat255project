package views;

/**
 * Created by hanneslagerroth on 2017-05-18.
 */
public class PortCallText {

    //Arrival
    private String arrivalVessel_TrafficArea;
    private String arrivalVessel_PilotBA;
    private String arrivalVessel_TugZone;
    private String arrivalVessel_Berth;

    //Cargo Operations
    private String cargoOp_Commenced;
    private String cargoOp_Completed;

    //Departures
    private String departure_Vessel_Berth;
    private String departure_Tug_Vessel;
    private String departure_Pilot_Vessel;


    public String getArrivalVessel_TrafficArea() {
        return arrivalVessel_TrafficArea;
    }

    public void setArrivalVessel_TrafficArea(String arrivalVessel_TrafficArea) {
        this.arrivalVessel_TrafficArea = arrivalVessel_TrafficArea;
    }

    public String getArrivalVessel_PilotBA() {
        return arrivalVessel_PilotBA;
    }

    public void setArrivalVessel_PilotBA(String arrivalVessel_PilotBA) {
        this.arrivalVessel_PilotBA = arrivalVessel_PilotBA;
    }

    public String getArrivalVessel_TugZone() {
        return arrivalVessel_TugZone;
    }

    public void setArrivalVessel_TugZone(String arrivalVessel_TugZone) {
        this.arrivalVessel_TugZone = arrivalVessel_TugZone;
    }

    public String getArrivalVessel_Berth() {
        return arrivalVessel_Berth;
    }

    public void setArrivalVessel_Berth(String arrivalVessel_Berth) {
        this.arrivalVessel_Berth = arrivalVessel_Berth;
    }

    public String getCargoOp_Commenced() {
        return cargoOp_Commenced;
    }

    public void setCargoOp_Commenced(String cargoOp_Commenced) {
        this.cargoOp_Commenced = cargoOp_Commenced;
    }

    public String getCargoOp_Completed() {
        return cargoOp_Completed;
    }

    public void setCargoOp_Completed(String cargoOp_Completed) {
        this.cargoOp_Completed = cargoOp_Completed;
    }

    public String getDeparture_Vessel_Berth() {
        return departure_Vessel_Berth;
    }

    public void setDeparture_Vessel_Berth(String departure_Vessel_Berth) {
        this.departure_Vessel_Berth = departure_Vessel_Berth;
    }

    public String getDeparture_Tug_Vessel() {
        return departure_Tug_Vessel;
    }

    public void setDeparture_Tug_Vessel(String departure_Tug_Vessel) {
        this.departure_Tug_Vessel = departure_Tug_Vessel;
    }

    public String getDeparture_Pilot_Vessel() {
        return departure_Pilot_Vessel;
    }

    public void setDeparture_Pilot_Vessel(String departure_Pilot_Vessel) {
        this.departure_Pilot_Vessel = departure_Pilot_Vessel;
    }
}
