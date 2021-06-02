package PaooGame.Items;
//package PaooGame.Maps;

public class Map_level {
    private static Map_level instance = null;
    private int level;
    private Map_level()
    {
        level = 1;
    }
    public static Map_level GetInstance()
    {
        if (instance == null)
        {
            instance = new Map_level();
        }
        return instance;
    }
    public static void Reset()
    {
        instance = null;
    }

    public void SetLevel(int level){
        this.level = level;
    }
    public void IncrementLevel(){ this.level ++;}

    public int getLevel(){
        return level;
    }

}
