package model;

public class EventModel {
    private int no;
    private String nama;
    private String organizer;
    private String event;
    private String statusBayar;

    // Constructor Kosong
    public EventModel() {}

    // Constructor Berparameter
    public EventModel(int no, String nama, String organizer, String event, String statusBayar) {
        this.no = no;
        this.nama = nama;
        this.organizer = organizer;
        this.event = event;
        this.statusBayar = statusBayar;
    }

    // Getter dan Setter
    public int getNo() { return no; }
    public void setNo(int no) { this.no = no; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public String getOrganizer() { return organizer; }
    public void setOrganizer(String organizer) { this.organizer = organizer; }
    
    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }
    
    public String getStatusBayar() { return statusBayar; }
    public void setStatusBayar(String statusBayar) { this.statusBayar = statusBayar; }
}