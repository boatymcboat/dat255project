package model;

/**
 * Stores and manages the names of the different types of locations in the PCM 0.0.16 system and the backend VirtualBox Application.
 */
public class LocationManager {

    // Stores the names of all Anchoring Areas
    private static final String[] anchoringAreas = new String[]{
            "Rivö Fjord R (Romeo) 3",
            "Rivö Fjord R (Romeo) 4",
            "Anchoring Area \"B\" (Bravo)",
            "Anchoring Area \"A\" (Alpha)",
            "Rivö Fjord R (Romeo) 1",
            "Anchoring Area \"C\" (Charlie)",
            "Rivö Fjord R (Romeo) 2",
            "Dana Fjord D1"
    };

    // Stores the names of all Berths
    private static final String[] berths = new String[]{
            "Älvsborg Harbour 700",
            "Masthuggskajen 33",
            "Älvsborg Harbour 712",
            "Stigbergskajen 36",
            "Arendal 751",
            "Tors Harbour 801",
            "Skandia Harbour 610",
            "Masthuggskajen 29",
            "Rya Harbour 551",
            "Rya Harbour 563",
            "Skarvik Harbour 514",
            "Skandia Harbour 642",
            "Skandia Harbour 614",
            "Masthuggskajen 25",
            "Rya Harbour 555",
            "Skarvik Harbour 510",
            "Skarvik Harbour 519",
            "Skarvik Harbour 507",
            "Majnabbehamnen 49",
            "Stigbergskajen 37",
            "Masthuggskajen 22",
            "Älvsborg Harbour 713",
            "Arendal 750",
            "Masthuggskajen 30",
            "Tors Harbour 800",
            "Skandia Harbour 643",
            "Rya Harbour 572",
            "Skandia Harbour 611",
            "Skarvik Harbour 513",
            "Masthuggskajen 26",
            "Skandia Harbour Bitumen 644",
            "Skandia Harbour 615",
            "Skarvik Harbour 521",
            "Rya Harbour 556",
            "Rya Harbour 568",
            "Majnabbehamnen 46",
            "Skarvik Harbour 518",
            "Skarvik Harbour 506",
            "Älvsborg Harbour 702",
            "Masthuggskajen 31",
            "Stigbergskajen 34",
            "Älvsborg Harbour 710",
            "Skarvik Harbour 516",
            "Skandia Harbour 600",
            "Masthuggskajen 27",
            "Skarvik Harbour 512",
            "Frihamnen 107",
            "Skandia Harbour 612",
            "Masthuggskajen 23",
            "Skarvik Harbour 520",
            "Majnabbehamnen 47",
            "Skarvik Harbour 509",
            "Skarvik Harbour 517",
            "Arendal 750A",
            "Masthuggskajen 32",
            "Älvsborg Harbour 711",
            "Stigbergskajen 35",
            "Arendal 752",
            "Rya Harbour 562",
            "Skarvik Harbour 515",
            "Skandia Harbour 641",
            "Frihamnen 108",
            "Masthuggskajen 28",
            "Rya Harbour 566",
            "Skarvik Harbour 511",
            "Skandia Harbour 601",
            "Skandia Harbour 613",
            "Masthuggskajen 24",
            "Skandia Harbour 640",
            "Skarvik Harbour 508",
            "Majnabbehamnen 48"
    };

    // Stores the names of all ETug Zones
    private static final String[] eTugZones = new String[] {
            "Escort tug station 1",
            "Escort tug station VLCC",
            "Escort tug station 2"
    };

    // Stores the names of all LOCs
    private static final String[] locs = new String[] {
            "LOC"
    };

    // Stores the names of all Pilot Boarding Areas
    private static final String[] pilotBAs = new String[] {
            "Pilot Boarding Position VLCC",
            "Pilot Boarding Position 3",
            "Pilot Boarding Position 2",
            "Pilot Boarding Position Rivö fjord",
            "Pilot Boarding Position Tångudden",
            "Pilot Boarding Position 1"
    };

    // Stores the names of all Traffic Areas
    private static final String[] trafficAreas = new String[] {
            "Port of Gothenburg's traffic area"
    };

    // Stores the names of all Tug Zones
    private static final String[] tugZones = new String[]{
            "Dana fjord",
            "Rivö fjord",
            "Fästningen"
    };

    /**
     * Returns the names of all Anchoring Areas
     *
     * @return a String array with the names of all Anchoring Areas
     */
    public static String[] getAnchoringAreas() {
        return anchoringAreas.clone();
    }

    /**
     * Returns the names of all Berths
     *
     * @return a String array with the names of all Berths
     */
    public static String[] getBerths() {
        return berths.clone();
    }

    /**
     * Returns the names of all ETug Zones
     *
     * @return a String array with the names of all ETug Zones
     */
    public static String[] geteTugZones() {
        return eTugZones.clone();
    }

    /**
     * Returns the names of all LOCs
     *
     * @return a String array with the names of all LOCs
     */
    public static String[] getLocs() {
        return locs.clone();
    }

    /**
     * Returns the names of all Pilot Boarding Areas
     *
     * @return a String array with the names of all Pilot Boarding Areas
     */
    public static String[] getPilotBAs() {
        return pilotBAs.clone();
    }

    /**
     * Returns the names of all Traffic Areas
     *
     * @return a String array with the names of all Traffic Areas
     */
    public static String[] getTrafficAreas() {
        return trafficAreas.clone();
    }

    /**
     * Returns the names of all Tug Zones
     *
     * @return a String array with the names of all Tug Zones
     */
    public static String[] getTugZones() {
        return tugZones.clone();
    }

}
