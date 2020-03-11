public class Outdining {

    private String retailer;
    private String time;
    private String meal;
    private String group;

    public Outdining (String retailer, String time, String meal, String group) {
        this.retailer = retailer;
        this.time = time;
        this.meal = meal;
        this.group = group;
    }

    public String getRetailer() {
        return retailer;
    }

    public String getTime() {
        return time;
    }

    public String getMeal() {
        return meal;
    }

    public String getGroup() {
        return group;
    }


    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
