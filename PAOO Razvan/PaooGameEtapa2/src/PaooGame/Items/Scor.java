package PaooGame.Items;

public class Scor {
    private static Scor instance = null;
    private int scor;
    private Scor()
    {
        scor = 0;
    }
    public static Scor GetInstance()
    {
        if (instance == null)
        {
            instance = new Scor();
        }
        return instance;
    }
    public static void Reset()
    {
        instance = null;
    }

    public void Update_Scor(){
        scor++;
    }

    public int getScor(){
        return scor;
    }

    public void setScor(int scor){
        this.scor = scor;
    }

}
