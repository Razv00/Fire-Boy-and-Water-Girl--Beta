package PaooGame.Maps;

import PaooGame.Graphics.Assets;
import PaooGame.Items.CollisionItem;
import PaooGame.Items.Map_level;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map
{
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private BufferedImage mapBackground;
    private CollisionItem collisionTestObject;
    private CollisionItem collisionTestObject1;
    public static int level;
   // private CollisionItem star;
    private ArrayList<CollisionItem> list_star; //elemente de coliziune dinamica-stele;
    private ArrayList<CollisionItem> list_star_blue; //elemente de coliziune dinamica-stele;

    private ArrayList<CollisionItem> list_fire; //elemente de coliziune dinamica: erouFoc - foc;

    private ArrayList<CollisionItem> list_static_element ;//elemente de coliziune statica podele;

    private ArrayList<CollisionItem> list_next_level ;


    boolean contor = false;
    //private CollisionItem collisionTestObject1;

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink)
    {
            /// Retine referinta "shortcut".
        this.refLink = refLink;
        list_star = new ArrayList<CollisionItem>();
        list_star_blue = new ArrayList<CollisionItem>();

        list_fire = new ArrayList<CollisionItem>();
        list_static_element = new ArrayList<CollisionItem>();

        list_next_level = new ArrayList<CollisionItem>();

        //level = 1;
       // star = new CollisionItem(refLink , 650 , 390,104,15);



        if(Map_level.GetInstance().getLevel() == 1) {
            System.out.println("salut1");

            list_star.add(new CollisionItem(refLink, 300, 200, 50, 50));
            list_star_blue.add(new CollisionItem(refLink, 250, 50, 50, 50));

            list_fire.add(new CollisionItem(refLink, 850, 270, 60, 20));


            //list_static_element.add(new CollisionItem(refLink, 452, 433, 104, 15));
            //list_static_element.add(new CollisionItem(refLink, 650, 410, 104, 15));

            list_static_element.add(new CollisionItem(refLink, 15, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 119, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 223, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 327, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 431, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 535, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 639, 140, 104, 15));

            //list_static_element.add( new CollisionItem(refLink, 15, 250, 104, 15) );
            //list_static_element.add( new CollisionItem(refLink, 119, 250, 104, 15) );
            list_static_element.add(new CollisionItem(refLink, 223, 290, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 327, 290, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 431, 290, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 535, 290, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 639, 290, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 743, 290, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 847, 290, 104, 15));


            list_next_level.add( new CollisionItem(refLink, 120, 420, 55, 50) );
            //list_static_element.add( new CollisionItem(refLink, 0, 465, 960, 15) );



            ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
            LoadWorld();


        }
        else if(Map_level.GetInstance().getLevel() ==2){
            list_star.add(new CollisionItem(refLink, 250, 80, 50, 50));
            list_star_blue.add(new CollisionItem(refLink, 700, 160, 50, 50));

            list_fire.add(new CollisionItem(refLink, 300, 450, 60, 20));


            //list_static_element.add(new CollisionItem(refLink, 452, 433, 104, 15));
            //list_static_element.add(new CollisionItem(refLink, 650, 410, 104, 15));

            list_static_element.add(new CollisionItem(refLink, 15, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 119, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 223, 140, 104, 15));
            //list_static_element.add(new CollisionItem(refLink, 327, 140, 104, 15));


            list_static_element.add(new CollisionItem(refLink, 620, 230, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 724, 230, 104, 15));


            list_static_element.add(new CollisionItem(refLink, 223, 290, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 327, 290, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 431, 290, 104, 15));


            list_next_level.add( new CollisionItem(refLink, 120, 420, 55, 50) );


            LoadWorld();
        }


        //lista podelei//elemente de coliziune statica

    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */
    public  void Update()
    {
        /*
        else if(level == 2){
            list_star.add(new CollisionItem(refLink, 670, 360, 50, 50));
            list_star_blue.add(new CollisionItem(refLink, 250, 50, 50, 50));

            list_fire.add(new CollisionItem(refLink, 220, 120, 60, 20));


            /*list_static_element.add(new CollisionItem(refLink, 452, 433, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 650, 410, 104, 15));

            list_static_element.add(new CollisionItem(refLink, 15, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 119, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 223, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 327, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 431, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 535, 140, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 639, 140, 104, 15));

            //list_static_element.add( new CollisionItem(refLink, 15, 250, 104, 15) );
            //list_static_element.add( new CollisionItem(refLink, 119, 250, 104, 15) );
            list_static_element.add(new CollisionItem(refLink, 223, 270, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 327, 270, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 431, 270, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 535, 270, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 639, 270, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 743, 270, 104, 15));
            list_static_element.add(new CollisionItem(refLink, 847, 270, 104, 15));


            //list_static_element.add( new CollisionItem(refLink, 0, 0, 960, 15) );
            //list_static_element.add( new CollisionItem(refLink, 0, 465, 960, 15) );


            ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.

            LoadWorld();

            return;
        }*/

    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g)
    {
            ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        g.drawImage(mapBackground, 0, 0, mapBackground.getWidth(), mapBackground.getHeight(), null);

        for(CollisionItem collisionItem : list_star){
            collisionItem.Draw(g);
        }

        for(CollisionItem collisionItem : list_star_blue){
            collisionItem.Draw(g);
        }

        for(CollisionItem collisionItem : list_fire){
            collisionItem.Draw(g);
        }


        for(CollisionItem collisionItem : list_static_element){
            collisionItem.Draw(g);
        }

        for(CollisionItem collisionItem :list_next_level){
            collisionItem.Draw(g);
        }

    }


    /*public boolean CheckCollision(int x, int y) {
        if(x > collisionTestObject.GetX() && x < collisionTestObject.GetX() + collisionTestObject.GetWidth()
        && y > collisionTestObject.GetY()  && y <  collisionTestObject.GetY() + collisionTestObject.GetHeight()  ) {
            return true;
        }

        if(x > collisionTestObject1.GetX() && x < collisionTestObject1.GetX() + collisionTestObject1.GetWidth()
                && y > collisionTestObject1.GetY() && y <  collisionTestObject1.GetY() + collisionTestObject1.GetHeight() ) {
           return true;
        }
        //if(x > collisionTestObject.)



        return false;



    }*/
    /*public boolean CheckCollisionStar(int x , int y){

        for(CollisionItem b : list) {
            if(x > b.GetX() && x < b.GetX() + b.GetWidth() &&
                x > b.GetY() && y < b.GetY() + b.GetHeight()){

               return true;
            }
        }



        return false;
    }*/



    /*! \fn private void LoadWorld()
       \brief Functie de incarcare a hartii jocului.
       Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
    */
    private void LoadWorld()
    {
        mapBackground = Assets.mapBackground;
        //collisionTestObject = new CollisionItem(refLink, 452, 433, 104, 15);
        //collisionTestObject1 = new CollisionItem(refLink, 650, 410, 104, 15);

        //collisionTestObject1 = new CollisionItem(refLink, 300, 433, 104, 15);

        for(CollisionItem collisionItem : list_static_element){
            collisionItem.LoadImage(Assets.floor);
        }


        for(CollisionItem collisionItem : list_star){
            collisionItem.LoadImage(Assets.red_Diamond);
        }

        for(CollisionItem collisionItem : list_star_blue){
            collisionItem.LoadImage(Assets.blue_Diamond);
        }

        for(CollisionItem collisionItem : list_fire){
            collisionItem.LoadImage(Assets.fire);
        }


        for(CollisionItem collisionItem : list_next_level){
            collisionItem.LoadImage(Assets.door);
        }





        //collisionTestObject1.LoadImage(Assets.floor);
    }

    public ArrayList<CollisionItem> getList_star(){
        return list_star;
    }
    public void setList_star(ArrayList<CollisionItem> list){
        this.list_star = list;
    }

    public ArrayList<CollisionItem> getList_star_blue(){
        return list_star_blue;
    }
    public void setList_star_blue(ArrayList<CollisionItem> list){
        this.list_star_blue = list;
    }

    public  ArrayList<CollisionItem> getList_fire(){
        return list_fire;
    }

    public void setList_fire(ArrayList<CollisionItem> list){
        this.list_fire = list;
    }

    public ArrayList<CollisionItem> getList_static_element(){
        return list_static_element;
    }

    public void setList_static_element(ArrayList<CollisionItem> list){
        this.list_static_element = list;
    }



    public ArrayList<CollisionItem> getList_next_level(){
        return list_next_level;
    }

    public void setList_next_level(ArrayList<CollisionItem> list){
        this.list_next_level = list;
    }


    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

}