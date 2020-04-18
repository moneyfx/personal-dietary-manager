public class PersonalDietaryManager  {
    public static void main(String[] args)
    {
    	View mvcView = new View();
        Model mvcModel = new Model();
        mvcModel.addObserver(mvcView);
        new Controller(mvcView,mvcModel);
        mvcView.setVisible(true);
    }
}