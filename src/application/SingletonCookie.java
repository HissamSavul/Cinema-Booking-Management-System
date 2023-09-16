package application;

public final class SingletonCookie {

    private static SingletonCookie instance;

    private int userID;
    private String userName;
    private String imagePath;


    private SingletonCookie(int id, String user,String imagepath ) {
    	userID=id;
        userName = user;
        setImagePath(imagepath);
    }

    public static SingletonCookie getInstace(String userName, int user,String imagepath ) {
        if(instance == null) {
            instance = new SingletonCookie(user,userName,imagepath);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }
    
    public int getUserID() {
        return userID;
    }
    
    public void setUserName(String a) {
        userName=a;
    }
    
    public void setUserID(int a) {
        userID=a;	
    }

    public void cleanUserSession() {
    	userID = -1;
        userName = "";
        setImagePath("");
    }

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}