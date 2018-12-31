package arif_ayon.checkmark.Model;


public class Contact{

    String username, password, semester;
    String event, room, stime, etime;


    public Contact(String username, String password, String semester){
        this.username = username;
        this.password = password;
        this.semester = semester;
    }

    public Contact(String event, String room, String stime, String etime){
        this.event = event;
        this.room = room;
        this.stime = stime;
        this.etime = etime;
    }

    public Contact(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUserName() { return this.username; }
    public String getPassword() { return this.password; }
    public String getSemester() { return this.semester; }

    public String getEvent() { return this.event; }
    public String getRoom() { return this.room; }
    public String getStime() { return this.stime; }
    public String getEtime() { return this.etime; }

}
