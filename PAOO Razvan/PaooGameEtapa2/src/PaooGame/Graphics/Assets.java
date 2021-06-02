package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage hero;
    public static BufferedImage heroLeft;
    public static BufferedImage heroRight;
    public static BufferedImage water_hero;
    public static BufferedImage water_hero_left;
    public static BufferedImage water_hero_right;



    public static BufferedImage grass;
    public static BufferedImage mapBackground;
    public static BufferedImage floor;
    public static BufferedImage red_Diamond;
    public static BufferedImage blue_Diamond;
    public static BufferedImage gameOverScreen;
    public static BufferedImage gameMenu;
    public static BufferedImage fire;
    public static BufferedImage nextLevel;
    public static BufferedImage door;

    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/PaooGameSpriteSheet.png"));

            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass = sheet.crop(0, 0);
        hero = sheet.crop(0, 2);
        heroRight = sheet.crop(1, 2);
        heroLeft = sheet.crop(2, 2);


        water_hero = sheet.crop(3,2);
        water_hero_right = sheet.crop(0,3);
        water_hero_left = sheet.crop(1,3);

        red_Diamond = sheet.crop(2,3);
        blue_Diamond = sheet.crop(3,3);

        mapBackground = ImageLoader.LoadImage("/textures/map_background.png");
        floor = ImageLoader.LoadImage("/textures/floor.png");


        gameOverScreen = ImageLoader.LoadImage("/textures/gameOverScreen.jpg");
        gameMenu = ImageLoader.LoadImage("/textures/GameMenu.png");

        fire = ImageLoader.LoadImage("/textures/fire.png");

        nextLevel = ImageLoader.LoadImage("/textures/nextlevel.png");
        door = ImageLoader.LoadImage("/textures/door.png");
    }
}
