package shared;

public class Config {
    private static Config instance;

    private int accuracy = Settings.Geometry.ACCURACY;

    private int windowLocationX = Settings.Window.LOCATION_X;
    private int getWindowLocationY = Settings.Window.LOCATION_Y;
    private int windowWidth = Settings.Window.WIDTH;
    private int windowHeight = Settings.Window.HEIGHT;

    private int canvasWidth = Settings.Canvas.WIDTH;
    private int canvasHeight = Settings.Canvas.HEIGHT;

    private Config() {}

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = isAccuracyValid(accuracy) ? accuracy : this.accuracy;
    }

    private boolean isAccuracyValid(int n) {
        return n >= 0;
    }

    public static void setInstance(Config instance) {
        Config.instance = instance;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public int getCanvasWidth() {
        return canvasWidth;
    }

    public void setCanvasWidth(int canvasWidth) {
        this.canvasWidth = canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }

    public void setCanvasHeight(int canvasHeight) {
        this.canvasHeight = canvasHeight;
    }

    public int getWindowLocationX() {
        return windowLocationX;
    }

    public void setWindowLocationX(int windowLocationX) {
        this.windowLocationX = windowLocationX;
    }

    public int getGetWindowLocationY() {
        return getWindowLocationY;
    }

    public void setGetWindowLocationY(int getWindowLocationY) {
        this.getWindowLocationY = getWindowLocationY;
    }
}
