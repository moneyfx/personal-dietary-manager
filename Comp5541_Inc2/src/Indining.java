public class Indining {
    private String name;
    private String time;
    private String serving;
    private String type;

    public Indining (String name, String time, String serving, String type) {
        this.name = name;
        this.time = time;
        this.serving = serving;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getServing() {
        return serving;
    }

    public void setServing(String serving) {
        this.serving = serving;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
