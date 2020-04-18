public class Outdining {

	private String retailer;
    private String time;
    private String date;
    private String unit;
    private String meal;
    private String group;
    private String calories;
    private String fat;
    private String carbs;
    private String protein;

    public Outdining (String retailer, String time, String date, String unit, String meal, 
    		String group, String calories, String fat, String carbs, String protein) {
        this.retailer = retailer;
        this.time = time;
        this.meal = meal;
        this.group = group;
        this.date = date;
        this.unit = unit;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCalories() {
		return calories;
	}

	public void setCalories(String calories) {
		this.calories = calories;
	}

	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}

	public String getCarbs() {
		return carbs;
	}

	public void setCarbs(String carbs) {
		this.carbs = carbs;
	}

	public String getProtein() {
		return protein;
	}

	public void setProtein(String protein) {
		this.protein = protein;
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
