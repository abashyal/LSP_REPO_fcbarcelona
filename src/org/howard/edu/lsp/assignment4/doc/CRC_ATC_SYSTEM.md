# CRC Cards — ATC System Design
Course: CSCI 363 & 540
Assignment: 4 — OO Design via CRC
Due: March 6, 2026

## AI/Internet Usage Note
No AI was used in completing this assignment.

---

## Assumptions
- The airport handles one runway; simultaneous arrivals are queued.
- Aircraft transponders broadcast continuously; the ground station polls at a fixed interval.
- "Dangerous situation" includes collision risk (proximity), unauthorized airspace entry, and low-altitude violations.
- The graphics display refreshes every 10 seconds as stated in the requirements.
- The controller interacts via a query terminal (keyboard/mouse); results are shown on the same display.

---

## CRC Cards

---

### CRC Card

Class: `Transponder`

Responsibilities:
- Encode aircraft type and flight data into a high-density packet.
- Broadcast the encoded packet at regular intervals.
- Maintain current flight data (position, altitude, speed, heading).

Collaborators (if any):
- `GroundStation`

Assumptions (if any):
- Each aircraft has exactly one transponder.
- Broadcast frequency is fixed and hardware-defined.

---

### CRC Card

Class: `GroundStation`

Responsibilities:
- Receive raw data packets broadcast by transponders.
- Delegate packet decoding to `PacketDecoder`.
- Forward decoded flight records to `AircraftDatabase` for storage.

Collaborators (if any):
- `Transponder`
- `PacketDecoder`
- `AircraftDatabase`

Assumptions (if any):
- Ground station handles one incoming data stream; multi-channel support is out of scope.

---

### CRC Card

Class: `PacketDecoder`

Responsibilities:
- Unpack a raw high-density data packet into structured flight data fields.
- Validate packet integrity (checksum, format).
- Return a decoded `FlightRecord` to the caller.

Collaborators (if any):
- `GroundStation`
- `FlightRecord`

Assumptions (if any):
- Packet format is standardized and versioned; decoder handles the current version only.

---

### CRC Card

Class: `FlightRecord`

Responsibilities:
- Store a single aircraft's flight data (aircraft ID, type, position, altitude, speed, heading).
- Provide read access to individual data fields.
- Report its own timestamp (when data was last updated).

Collaborators (if any):
- `PacketDecoder`
- `AircraftDatabase`

Assumptions (if any):
- `FlightRecord` is a value-like object; it does not update itself.

---

### CRC Card

Class: `AircraftDatabase`

Responsibilities:
- Store and index `FlightRecord` objects by aircraft ID.
- Update an existing record when new data arrives for a known aircraft.
- Retrieve a specific `FlightRecord` by aircraft ID on request.
- Provide the full set of current records for display and analysis.

Collaborators (if any):
- `FlightRecord`
- `GroundStation`
- `DisplayController`
- `SafetyAnalyzer`
- `ControllerQueryHandler`

Assumptions (if any):
- Database is in-memory; persistence across system restarts is out of scope.

---

### CRC Card

Class: `DisplayController`

Responsibilities:
- Request the current set of `FlightRecord` objects from `AircraftDatabase` every 10 seconds.
- Translate flight records into graphical positions and labels via `RadarDisplay`.
- Trigger a display refresh cycle on schedule.
- Highlight aircraft flagged by `SafetyAnalyzer` as dangerous.

Collaborators (if any):
- `AircraftDatabase`
- `RadarDisplay`
- `SafetyAnalyzer`

Assumptions (if any):
- The 10-second refresh timer is managed internally by this class.

---

### CRC Card

Class: `RadarDisplay`

Responsibilities:
- Render aircraft positions as blips on a radar screen.
- Draw labels (flight ID, altitude, speed) next to each blip.
- Visually distinguish aircraft flagged as dangerous (e.g., color change).
- Render query result details in a side panel when requested.

Collaborators (if any):
- `DisplayController`
- `ControllerQueryHandler`

Assumptions (if any):
- Rendering is 2D top-down view; no 3D visualization.

---

### CRC Card

Class: `SafetyAnalyzer`

Responsibilities:
- Retrieve all current `FlightRecord` objects from `AircraftDatabase`.
- Calculate separation distances between pairs of aircraft.
- Detect proximity violations (aircraft too close horizontally or vertically).
- Detect altitude-limit violations and unauthorized airspace entry.
- Produce a list of `DangerAlert` objects describing each detected situation.
- Notify `AlertNotifier` when new dangers are detected.

Collaborators (if any):
- `AircraftDatabase`
- `FlightRecord`
- `DangerAlert`
- `AlertNotifier`

Assumptions (if any):
- Analysis runs on the same 10-second cycle as the display refresh.
- Separation thresholds are configurable constants, not hardcoded.

---

### CRC Card

Class: `DangerAlert`

Responsibilities:
- Store details of a detected dangerous situation (type, involved aircraft IDs, severity).
- Provide read access to alert fields for display and logging.

Collaborators (if any):
- `SafetyAnalyzer`
- `AlertNotifier`
- `DisplayController`

Assumptions (if any):
- A single alert involves one or two aircraft (e.g., proximity alert involves two; altitude violation involves one).

---

### CRC Card

Class: `AlertNotifier`

Responsibilities:
- Receive `DangerAlert` objects from `SafetyAnalyzer`.
- Trigger an audible and/or visual alarm for the controller.
- Log each alert with a timestamp for the record.

Collaborators (if any):
- `SafetyAnalyzer`
- `DangerAlert`
- `RadarDisplay`

Assumptions (if any):
- Logging is to a local file; external systems are out of scope.

---

### CRC Card

Class: `ControllerQueryHandler`

Responsibilities:
- Accept a query from the controller (aircraft ID input).
- Retrieve the matching `FlightRecord` from `AircraftDatabase`.
- Format and return the flight details for display.

Collaborators (if any):
- `AircraftDatabase`
- `FlightRecord`
- `RadarDisplay`

Assumptions (if any):
- The controller inputs a single aircraft ID per query; batch queries are out of scope.

---

## Class Summary

| Class                   | Role                                         |
|-------------------------|----------------------------------------------|
| `Transponder`           | Encodes and broadcasts aircraft data         |
| `GroundStation`         | Receives packets and routes to decoder/DB    |
| `PacketDecoder`         | Unpacks raw packets into structured records  |
| `FlightRecord`          | Holds one aircraft's current flight data     |
| `AircraftDatabase`      | Stores and retrieves all flight records      |
| `DisplayController`     | Drives the 10-second display refresh cycle   |
| `RadarDisplay`          | Renders the graphical radar screen           |
| `SafetyAnalyzer`        | Detects dangerous situations between aircraft|
| `DangerAlert`           | Represents a single detected danger          |
| `AlertNotifier`         | Notifies the controller of active dangers    |
| `ControllerQueryHandler`| Handles controller queries for aircraft info |
