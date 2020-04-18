public class Indining {
    private String name;
    private String time;
    private String date;
    private String unit;
    private String serving;
    private String group;
    private String calories;
    private String fat;
    private String carbs;
    private String protein;

    public Indining (String name, String time, String date, String unit, String serving, 
    		String group, String calories, String fat, String carbs, String protein) {
        this.name = name;
        this.time = time;
        this.serving = serving;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
