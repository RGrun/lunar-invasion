package r3software.org.lunarinvasion;

import java.util.Random;

import r3software.org.lunarinvasion.engine.framework.Animation;
import r3software.org.lunarinvasion.engine.framework.Font;
import r3software.org.lunarinvasion.engine.framework.Music;
import r3software.org.lunarinvasion.engine.framework.Sound;
import r3software.org.lunarinvasion.engine.framework.TextureRegion;
import r3software.org.lunarinvasion.engine.impl.GLGame;
import r3software.org.lunarinvasion.engine.impl.Texture;

@SuppressWarnings("PointlessArithmeticExpression")
public class Assets {
	
	//load up assets in this class

    //basic
    public static Texture atlas;

    //other backgrounds
    public static Texture background_2_UI;
    public static TextureRegion background_2_UI_region;

    public static Texture background_3;
    public static TextureRegion background_3_region;

    public static Texture background_4;
    public static TextureRegion background_4_region;

    public static Texture background_5;
    public static TextureRegion background_5_region;

    public static Texture title_menu;
    public static TextureRegion title_menu_region;

    public static Texture background_3_UI;
    public static TextureRegion background_3_UI_region;

    public static Texture background_4_UI;
    public static TextureRegion background_4_UI_region;

    public static Texture background_5_UI;
    public static TextureRegion background_5_UI_region;

    //in-game menu
    public static Texture gameMenuSoundOn;
    public static Texture gameMenuSoundOff;
    public static TextureRegion gameMenuSoundOnRegion;
    public static TextureRegion gameMenuSoundOffRegion;

    public static Texture earth_victory_menu;
    public static TextureRegion earth_victory_menu_region;

    public static Texture alien_victory_menu;
    public static TextureRegion alien_victory_menu_region;


    //ships
    public static TextureRegion humanShip;
    public static TextureRegion alienShip;

    //weapons
    public static TextureRegion orangeShot;
    public static TextureRegion greenShot;
    public static TextureRegion redShot;
    public static TextureRegion blueShot;
    public static TextureRegion missile;

    //drones
    public static TextureRegion drone;
    public static TextureRegion alienDrone;
    public static TextureRegion humanDrone;

    //powerups
    public static TextureRegion armorPU;
    public static TextureRegion healthPU;
    public static TextureRegion weaponPU;

    //targets
    public static TextureRegion humanTargetRegion;
    public static TextureRegion alienTargetRegion;

    //blocks
    public static TextureRegion platform_1x1_static;
    public static TextureRegion platform_1x1_breakable;

    public static TextureRegion platform_2x2_static;
    public static TextureRegion platform_2x2_breakable;

    public static TextureRegion platform_4x4_static;
    public static TextureRegion platform_4x4_breakable;

    public static TextureRegion platform_4x2_static_v;

    public static TextureRegion platform_4x2_static_h;

    public static TextureRegion platform_6x2_static_v;
    public static TextureRegion platform_6x2_static_h;

    public static TextureRegion platform_8x2_static_v;
    public static TextureRegion platform_8x2_static_h;

    public static TextureRegion platform_10x2_static_v;
    public static TextureRegion platform_10x2_static_h;

    public static TextureRegion platform_12x2_static_v;
    public static TextureRegion platform_12x2_static_h;

    public static TextureRegion platform_14x2_static_v;
    public static TextureRegion platform_14x2_static_h;

    public static TextureRegion platform_16x2_static_v;
    public static TextureRegion platform_16x2_static_h;

    public static TextureRegion platform_18x2_static_h;


    //angled blocks
    public static TextureRegion angled_6x6;
    public static TextureRegion angled_4x4;
    public static TextureRegion angled_2x2;

    //fonts
    public static Font font;
    public static Font blackFont;

    //health bars
    public static TextureRegion filledHealth;
    public static TextureRegion emptyHealth;
    public static TextureRegion shieldHealth;

    public static Animation shield;

    public static Animation shotBounce;

    public static Animation teleport;
    public static Animation reverseTeleport;

    //move buttons
    public static TextureRegion enabledButton;
    public static TextureRegion pressedButton;
    public static TextureRegion disabledButton;

    //teleport meter stuff
    public static TextureRegion teleportMeter;
    public static TextureRegion teleportBorder;

    //gear button
    public static TextureRegion gearButton;

    // explosions
    public static Animation shipExplosion;
    public static Animation blueShotExplosion;

    // weapon select buttons
    public static TextureRegion humanButton;
    public static TextureRegion alienButton;

    //weapon menus
    public static TextureRegion humanMenu;
    public static TextureRegion alienMenu;

    //pause menu stuff
    public static Texture blackOverlay;
    public static TextureRegion blackOverlayRegion;


    //pictures of the individual weapons
    //to show when a weapon is picked up
    public static TextureRegion hGreen;
    public static TextureRegion hRed;
    public static TextureRegion hBlue;
    public static TextureRegion hMissile;

    public static TextureRegion aGreen;
    public static TextureRegion aRed;
    public static TextureRegion aBlue;
    public static TextureRegion aMissile;


    //menu stuff
    public static Texture menuBackground;
    public static TextureRegion menuBackgroundRegion;
    public static Texture menuAtlas;

    public static TextureRegion game_play;
    public static TextureRegion weapons;
    public static TextureRegion power_ups;
    public static TextureRegion credits;
    public static TextureRegion help;

    public static TextureRegion goals;
    public static TextureRegion movement;
    public static TextureRegion shooting;
    public static TextureRegion story;
    public static TextureRegion game_play_small;
    public static TextureRegion weapons_small;
    public static TextureRegion power_ups_small;
    public static TextureRegion health_menu;
    public static TextureRegion shield_menu;
    public static TextureRegion weapon_menu;
    public static TextureRegion orange;
    public static TextureRegion green;
    public static TextureRegion blue;
    public static TextureRegion purple;
    public static TextureRegion red;
    public static TextureRegion credits_small;
    public static TextureRegion help_small;
    public static TextureRegion play;
    public static TextureRegion noel;
    public static TextureRegion chris;
    public static TextureRegion richard;
    public static TextureRegion orange_shot_menu;
    public static TextureRegion green_shot_menu;
    public static TextureRegion blue_shot_menu;
    public static TextureRegion purple_shot_menu;
    public static TextureRegion red_shot_menu;
    public static TextureRegion weapon_pu_menu;
    public static TextureRegion shield_pu_menu;
    public static TextureRegion health_pu_menu;
    public static TextureRegion left_arrow;
    public static TextureRegion down_arrow;
    public static TextureRegion right_arrow;
    public static TextureRegion soundOn;
    public static TextureRegion soundOff;

    //shot explosions
    //these are on the second sprite sheet
    public static Animation orangeExplosion;
    public static Animation greenExplosion;
    public static Animation redExplosion;
    public static Animation blueExplosion;
    public static Animation purpleExplosion;

    //these are the page backgrounds
    public static Texture goalsPage;
    public static TextureRegion goalsPageRegion;

    public static Texture bluePage;
    public static TextureRegion bluePageRegion;

    public static Texture greenPage;
    public static TextureRegion greenPageRegion;

    public static Texture healthPUPage;
    public static TextureRegion healthPUPageRegion;

    public static Texture movementPage;
    public static TextureRegion movementPageRegion;

    public static Texture purplePage;
    public static TextureRegion purplePageRegion;

    public static Texture organgePage;
    public static TextureRegion orangePageRegion;

    public static Texture redPage;
    public static TextureRegion redPageRegion;

    public static Texture shieldPUPage;
    public static TextureRegion shieldPUPageRegion;

    public static Texture shootingPage;
    public static TextureRegion shootingPageRegion;

    public static Texture weaponPUPage;
    public static TextureRegion weaponPUPageRegion;

    // these are the level select screens
    public static Texture select1;
    public static TextureRegion select1Region;

    public static Texture select2;
    public static TextureRegion select2Region;

    public static Texture select3;
    public static TextureRegion select3Region;

    public static Texture select4;
    public static TextureRegion select4Region;

    public static Texture select5;
    public static TextureRegion select5Region;


    // human-alien turn confirmation popups
    public static Texture humanTurnConfirm;
    public static TextureRegion humanTurnConfirmRegion;

    public static Texture alienTurnConfirm;
    public static TextureRegion alienTurnConfirmRegion;



    // game sound effects
    public static Sound beginTurn;
    public static Sound blockDestroy;
    public static Sound bump;
    public static Sound cantGoHere;
    public static Sound death;
    public static Sound error1;
    public static Sound error2;
    public static Sound menuClick;
    public static Sound menuClose;
    public static Sound menuSelect;
    public static Sound pickup;
    public static Sound powerup;
    public static Sound shotSound;
    public static Sound warp;

    public static Sound blue_shot;
    public static Sound blue_activate;
    public static Sound green_shot;
    public static Sound green_activate;
    public static Sound player_death;
    public static Sound purple_shot;
    public static Sound purple_activate;
    public static Sound red_death;
    public static Sound red_death_short;
    public static Sound satellite_destroy_no_pu;
    public static Sound satellite_destory_pu;
    public static Sound take_damage;


    // songs
    public static Music spacebeat;
    public static Music victoryShort;
    public static Music cosmic1;
    public static Music cosmic2;
    public static Music cosmic3;
    public static Music menuMusic;


    public static GLGame gameRef;


    // for controlling which song is being played currently
    public static Music currentMusic;

    public static void load(GLGame game) {

        gameRef = game;

        goalsPage = new Texture(game, "pages/Goals_Menu.jpg");
        goalsPageRegion = new TextureRegion(goalsPage, 0, 0, 512, 1024);
        bluePage = new Texture(game, "pages/Blue_Weapon.jpg");
        bluePageRegion = new TextureRegion(bluePage, 0, 0, 512, 1024);
        greenPage = new Texture(game, "pages/Green_Weapon.jpg");
        greenPageRegion = new TextureRegion(greenPage, 0, 0, 512, 1024);
        healthPUPage = new Texture(game, "pages/Health_PowerUp.jpg");
        healthPUPageRegion = new TextureRegion(healthPUPage, 0, 0, 512, 1024);
        movementPage = new Texture(game, "pages/Movement_Menu.jpg");
        movementPageRegion = new TextureRegion(movementPage, 0, 0, 512, 1024);
        organgePage = new Texture(game, "pages/Orange_Weapon.jpg");
        orangePageRegion = new TextureRegion(organgePage, 0, 0, 512, 1024);
        purplePage = new Texture(game, "pages/Purple_Weapon.jpg");
        purplePageRegion = new TextureRegion(purplePage, 0, 0, 512, 1024);
        redPage = new Texture(game, "pages/Red_Weapon.jpg");
        redPageRegion = new TextureRegion(redPage, 0, 0, 512, 1024);
        shieldPUPage = new Texture(game, "pages/Shield_PowerUp.jpg");
        shieldPUPageRegion = new TextureRegion(shieldPUPage, 0, 0, 512, 1024);
        shootingPage = new Texture(game, "pages/Shooting_Menu.jpg");
        shootingPageRegion = new TextureRegion(shootingPage, 0, 0, 512, 1024);
        weaponPUPage = new Texture(game, "pages/Weapon_PowerUp.jpg");
        weaponPUPageRegion = new TextureRegion(weaponPUPage, 0, 0, 512, 1024);

        atlas = new Texture(game, "Sprite_Atlas_D.png");
        menuAtlas = new Texture(game, "Menu_Sheet_2.png");
        menuBackground = new Texture (game, "backgrounds/Menu_BG_2_pwr2.png");
        menuBackgroundRegion = new TextureRegion(menuBackground, 0, 0, 512, 1024);

        background_2_UI  = new Texture(game, "backgrounds/Space_BG_2_UI.jpg");
        background_2_UI_region = new TextureRegion(background_2_UI, 0, 0, 512, 1024);

        background_3 = new Texture(game, "backgrounds/Space_BG_3.jpg");
        background_3_region = new TextureRegion(background_3, 0, 0, 512, 1024);

        background_4 = new Texture(game, "backgrounds/Space_BG_4.jpg");
        background_4_region = new TextureRegion(background_4, 0, 0, 512, 1024);

        background_5 = new Texture(game, "backgrounds/Space_BG_5.jpg");
        background_5_region = new TextureRegion(background_5, 0, 0, 512, 1024);

        title_menu = new Texture(game, "backgrounds/Title_Menu.jpg");
        title_menu_region = new TextureRegion(title_menu, 0, 0, 512, 1024);

        background_3_UI = new Texture(game, "backgrounds/Space_BG_3_UI.jpg");
        background_3_UI_region = new TextureRegion(background_3_UI, 0, 0, 512, 1024);

        background_4_UI = new Texture(game, "backgrounds/Space_BG_4_UI.jpg");
        background_4_UI_region = new TextureRegion(background_4_UI, 0, 0, 512, 1024);

        background_5_UI = new Texture(game, "backgrounds/Space_BG_5_UI.jpg");
        background_5_UI_region = new TextureRegion(background_5_UI, 0, 0, 512, 1024);

        gameMenuSoundOn = new Texture(game, "Pause_Menu_1.png");
        gameMenuSoundOff = new Texture(game, "Pause_Menu_2.png");
        gameMenuSoundOnRegion = new TextureRegion(gameMenuSoundOn, 0, 0, 1024, 1024);
        gameMenuSoundOffRegion = new TextureRegion(gameMenuSoundOff, 0, 0, 1024, 1024);

        earth_victory_menu = new Texture(game, "Victory_Menu_Earth.jpg");
        earth_victory_menu_region = new TextureRegion(earth_victory_menu, 0, 0, 1024, 1024);

        alien_victory_menu = new Texture(game, "Victory_Menu_Lunar.jpg");
        alien_victory_menu_region = new TextureRegion(alien_victory_menu, 0, 0, 1024, 1024);

        //black overlay for game screen
        blackOverlay = new Texture(game, "Pause_Shade.png");
        blackOverlayRegion = new TextureRegion(blackOverlay, 0, 0, 512, 1024);

        humanShip = new TextureRegion(atlas, 0, 0, 64, 64);
        alienShip = new TextureRegion(atlas, 64, 0, 64, 64);

        orangeShot = new TextureRegion(atlas, 0, 64, 32, 32);
        greenShot = new TextureRegion(atlas, 32, 64, 32, 32);
        redShot = new TextureRegion(atlas, 2 * 32, 2 * 32, 32, 32);
        blueShot = new TextureRegion(atlas, 3 * 32, 2 * 32, 32, 32);
        missile  = new TextureRegion(atlas, 4 * 32, 2 * 32, 1 * 32, 1 * 32);

        humanTargetRegion = new TextureRegion(atlas, 30 * 32, 15 * 32, 2 * 32, 2 * 32);
        alienTargetRegion = new TextureRegion(atlas, 28 * 32,  15 * 32, 2 * 32, 2 * 32);

        //platforms
        platform_1x1_breakable = new TextureRegion(atlas, 11 * 32, 0, 32, 32);
        platform_1x1_static = new TextureRegion(atlas, 9 * 32, 3 * 32, 32, 32);

        platform_2x2_breakable = new TextureRegion(atlas, 9 * 32, 0, 64, 62);
        platform_2x2_static = new TextureRegion(atlas, 10 * 32, 2 * 32, 64, 62);

        platform_4x4_breakable = new TextureRegion(atlas, 5 * 32, 9, 128, 128);
        platform_4x4_static = new TextureRegion(atlas, 12 * 32, 0, 128, 128);

        platform_4x2_static_v = new TextureRegion(atlas, 2 * 32, 18 * 32, 2 * 32, 4 * 32);
        platform_4x2_static_h = new TextureRegion(atlas, 0 * 32, 16 * 32, 4 * 32, 2 * 32);

        platform_6x2_static_v = new TextureRegion(atlas, 2 * 32, 18 * 32, 2 * 32, 6 * 32);
        platform_6x2_static_h = new TextureRegion(atlas, 0 * 32, 14 * 32, 6 * 32, 2 * 32);

        platform_8x2_static_v = new TextureRegion(atlas, 4 * 32, 16 * 32, 2 * 32, 8 * 32);
        platform_8x2_static_h = new TextureRegion(atlas, 0 * 32, 12 * 32, 8 * 32, 2 * 32);

        platform_10x2_static_v = new TextureRegion(atlas, 8 * 32, 12 * 32, 2 * 32, 10 * 32);
        platform_10x2_static_h = new TextureRegion(atlas, 0 * 32, 10 * 32, 10 * 32, 2 * 32);

        platform_12x2_static_v = new TextureRegion(atlas, 10 * 32, 10 * 32, 2 * 32, 12 * 32);
        platform_12x2_static_h = new TextureRegion(atlas, 0 * 32, 8 * 32, 12 * 32, 2 * 32);

        platform_14x2_static_v = new TextureRegion(atlas, 12 * 32, 8 * 32, 2 * 32, 14 * 32);
        platform_14x2_static_h = new TextureRegion(atlas, 0 * 32, 6 * 32, 14 * 32, 2 * 32);

        platform_16x2_static_v = new TextureRegion(atlas, 14 * 32, 10 * 32, 2 * 32, 16 * 32);
        platform_16x2_static_h = new TextureRegion(atlas, 0 * 32, 4 * 32, 16 * 32, 2 * 32);

		angled_6x6 = new TextureRegion(atlas, 0 * 32, (22 * 32) + 2,  6 * 32, 6 * 32);
        angled_4x4 = new TextureRegion(atlas, 6 * 32, (22 * 32) + 1, 4 * 32, (4 * 32));
        angled_2x2 = new TextureRegion(atlas, 10 * 32, 22 * 32, 2 * 32, 2 * 32);

        font = new Font(atlas, 0 * 32, 28 * 32, 16, 16, 20);
        blackFont = new Font(atlas, 8 * 32, 28 * 32, 16, 16, 20);

        filledHealth = new TextureRegion(atlas, 28 * 32, 18 * 32, 2 * 32, 1 * 32);
        shieldHealth = new TextureRegion(atlas, 28 * 32, 19 * 32, 2 * 32, 1 * 32);
        emptyHealth = new TextureRegion(atlas, 28 * 32, 17 * 32, 2 * 32, 1 * 32);

        shield = new Animation(0.1f,
                                new TextureRegion(atlas, (16 * 32) + 2, (0 * 32) + 2, 3 * 32, 3 * 32),
                                new TextureRegion(atlas, 19 * 32, 0 * 32, 3 * 32, 3 * 32),
                                new TextureRegion(atlas, 22 * 32, 0 * 32, 3 * 32, 3 * 32),
                                new TextureRegion(atlas, 25 * 32, 0 * 32, 3 * 32, 3 * 32),
                                new TextureRegion(atlas, 28 * 32, 0 * 32, 3 * 32, 3 * 32));

        shotBounce = new Animation(0.1f,
                                new TextureRegion(atlas, 0 * 32, 3 * 32, 32, 32),
                                new TextureRegion(atlas, 1 * 32, 3 * 32, 32, 32),
                                new TextureRegion(atlas, 2 * 32, 3 * 32, 32, 32),
                                new TextureRegion(atlas, 3 * 32, 3 * 32, 32, 32),
                                new TextureRegion(atlas, 4 * 32, 3 * 32, 32, 32));

        teleport = new Animation(0.1f,
                                new TextureRegion(atlas, 16 * 32, 3 * 32, 3 * 32, 3 * 32),
                                new TextureRegion(atlas, 19 * 32, 3 * 32, 3 * 32, 3 * 32),
                                new TextureRegion(atlas, 22 * 32, 3 * 32, 3 * 32, 3 * 32),
                                new TextureRegion(atlas, 25 * 32, 3 * 32, 3 * 32, 3 * 32),
                                new TextureRegion(atlas, 28 * 32, 3 * 32, 3 * 32, 3 * 32));

        reverseTeleport = new Animation(0.1f,
                new TextureRegion(atlas, 28 * 32, 3 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 25 * 32, 3 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 22 * 32, 3 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 19 * 32, 3 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 16 * 32, 3 * 32, 3 * 32, 3 * 32));


        enabledButton = new TextureRegion(atlas, 16 * 32, 6 * 32, 2 * 32, 3 * 32);
        pressedButton =  new TextureRegion(atlas, 18 * 32, 6 * 32, 2 * 32, 3 * 32);
        disabledButton =  new TextureRegion(atlas, 20 * 32, 6 * 32, 2 * 32, 3 * 32);

        teleportMeter = new TextureRegion(atlas, 22 * 32, 6 * 32, 2 * 32, 3 * 32);
        teleportBorder = new TextureRegion(atlas, 22 * 32, 6 * 32, 2 * 32, 3 * 32);

        gearButton = new TextureRegion(atlas, 24 * 32, 6 * 32, 2 * 32, 2 * 32);

        shipExplosion   = new Animation(0.1f,
                new TextureRegion(atlas, 16 * 32, 9 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 19 * 32, 9 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 22 * 32, 9 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 25 * 32, 9 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 28 * 32, 9 * 32, 3 * 32, 3 * 32));

        blueShotExplosion = new Animation(0.05f,
                new TextureRegion(atlas, 16 * 32, 12 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 19 * 32, 12 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 22 * 32, 12 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 25 * 32, 12 * 32, 3 * 32, 3 * 32),
                new TextureRegion(atlas, 28 * 32, 12 * 32, 3 * 32, 3 * 32));

        humanButton = new TextureRegion(atlas, 30 * 32, 20 * 32, 2 * 32, 2 * 32);
        alienButton = new TextureRegion(atlas, 28 * 32, 20 * 32, 2 * 32, 2 * 32);

        humanMenu = new TextureRegion(atlas, 16 * 32, 23 * 32, 12 * 32, 8 * 32);
        alienMenu = new TextureRegion(atlas, 16 * 32, 15 * 32, 12 * 32, 8 * 32);

        drone = new TextureRegion(atlas, 30 * 32, 17 * 32, 2 * 32, 2 * 32);
        alienDrone = new TextureRegion(atlas, 28 * 32, 22 * 32, 2 * 32, 2 * 32);
        humanDrone = new TextureRegion(atlas, 26 * 32, 6 * 32, 2 * 32, 2 * 32);

        armorPU = new TextureRegion(atlas, 31 * 32, 19 * 32, 1 * 32, 1 * 32);
        weaponPU = new TextureRegion(atlas, 31 * 32, 22 * 32, 1 * 32, 1 * 32);
        healthPU = new TextureRegion(atlas, 30 * 32, 19 * 32, 1 * 32, 1 * 32);

        aGreen = new TextureRegion(atlas, 20 * 32, 15 * 32, 4 * 32, 4 * 32);
        aRed = new TextureRegion(atlas, 16 * 32, 19 * 32, 4 * 32, 4 * 32);
        aBlue = new TextureRegion(atlas, 20 * 32, 19 * 32, 4 * 32, 4 * 32);
        aMissile = new TextureRegion(atlas, 24 * 32, 15 * 32, 4 * 32, 4 * 32);

        hMissile = new TextureRegion(atlas, 24 * 32, 23 * 32, 4 * 32, 4 * 32);
        hGreen = new TextureRegion(atlas, 20 * 32, 23 * 32, 4 * 32, 4 * 32);
        hBlue = new TextureRegion(atlas, 20 * 32, 27 * 32, 4 * 32, 4 * 32);
        hRed = new TextureRegion(atlas, 16 * 32, 27 * 32, 4 * 32, 4 * 32);

        //menus ;~;

        game_play = new TextureRegion(menuAtlas, 0, 0, 10 * 32, 2 * 32);
        weapons = new TextureRegion(menuAtlas, 0, 2 * 32, 9 * 32, 2 * 32);
        power_ups = new TextureRegion(menuAtlas, 0, 4 * 32, 11 * 32, 2 * 32);
        credits = new TextureRegion(menuAtlas, 0, 6 * 32, 8 * 32, 2 * 32);
        help = new TextureRegion(menuAtlas, 0, 8 * 32, 5 * 32, 2 * 32);

        goals = new TextureRegion(menuAtlas, 0, 10 * 32, 5 * 32, 2 * 32);
        movement = new TextureRegion(menuAtlas, 0, 12 * 32, 9 * 32, 2 * 32);
        shooting = new TextureRegion(menuAtlas, 0, 14 * 32, 8 * 32, 2 * 32);
        story = new TextureRegion(menuAtlas, 0, 16 * 32, 5 * 32, 2 * 32);
        game_play_small = new TextureRegion(menuAtlas, 0, 18 * 32, 10 * 32, 2 * 32);
        weapons_small = new TextureRegion(menuAtlas, 0, 20 * 32, 8 * 32, 2 * 32);
        power_ups_small = new TextureRegion(menuAtlas, 0, 22 * 32, 10 * 32, 2 * 32);
        health_menu = new TextureRegion(menuAtlas, 0, 24 * 32, 6 * 32, 2 * 32);
        shield_menu = new TextureRegion(menuAtlas, 0, 26 * 32, 6 * 32, 2 * 32);
        weapon_menu = new TextureRegion(menuAtlas, 0, 28 * 32, 7 * 32, 2 * 32);
        orange = new TextureRegion(menuAtlas, 11 * 32, 10 * 32, 7 * 32, 2 * 32);
        green = new TextureRegion(menuAtlas, 11 * 32, 12 * 32, 6 * 32, 2 * 32);
        blue = new TextureRegion(menuAtlas, 11 * 32, 14 * 32, 4 * 32, 2 * 32);
        purple = new TextureRegion(menuAtlas, 11 * 32, 16 * 32, 6 * 32, 2 * 32);
        red = new TextureRegion(menuAtlas, 11 * 32, 18 * 32, 4 * 32, 2 * 32);
        credits_small = new TextureRegion(menuAtlas, 11 * 32, 20 * 32, 7 * 32, 2 * 32);
        help_small = new TextureRegion(menuAtlas, 11 * 32, 22 * 32, 4 * 32, 2 * 32);
        play = new TextureRegion(menuAtlas, 11 * 32, 24 * 32, 6 * 32, 3 * 32);

        noel = new TextureRegion(menuAtlas, 19 * 32, 15 * 32, 13 * 32, 3 * 32);
        chris = new TextureRegion(menuAtlas, 20 * 32, 18 * 32, 11 * 32, 3 * 32);
        richard = new TextureRegion(menuAtlas, 19 * 32, 21 * 32, 13 * 32, 3 * 32);

        orange_shot_menu = new TextureRegion(menuAtlas, 17 * 32, 7 * 32, 3 * 32, 3 * 32);
        green_shot_menu = new TextureRegion(menuAtlas, 20 * 32, 7 * 32, 3 * 32, 3 * 32);
        blue_shot_menu = new TextureRegion(menuAtlas, 23 * 32, 7 * 32, 3 * 32, 3 * 32);
        purple_shot_menu = new TextureRegion(menuAtlas, 26 * 32, 7 * 32, 3 * 32, 3 * 32);
        red_shot_menu = new TextureRegion(menuAtlas, 29 * 32, 7 * 32, 3 * 32, 3 * 32);

        weapon_pu_menu = new TextureRegion(menuAtlas, 23 * 32, 4 * 32, 3 * 32, 3 * 32);
        shield_pu_menu = new TextureRegion(menuAtlas, 26 * 32, 4 * 32, 3 * 32, 3 * 32);
        health_pu_menu = new TextureRegion(menuAtlas, 29 * 32, 4 * 32, 3 * 32, 3 * 32);

        left_arrow = new TextureRegion(menuAtlas, 20 * 32, 0, 4 * 32, 4 * 32);
        down_arrow = new TextureRegion(menuAtlas, 24 * 32, 0, 4 * 32, 4 * 32);
        right_arrow = new TextureRegion(menuAtlas, 28 * 32, 0, 4 * 32, 4 * 32);

        soundOn = new TextureRegion(menuAtlas, 17 * 32, 4 * 32, 2 * 32, 2 * 32);
        soundOff = new TextureRegion(menuAtlas, 19 * 32, 4 * 32, 2 * 32, 2 * 32);

        //shot explosions
        orangeExplosion = new Animation(0.1f,
                new TextureRegion(atlas, 28 * 32, 24 * 32, 32, 32),
                new TextureRegion(atlas, 29 * 32, 24 * 32, 32, 32),
                new TextureRegion(atlas, 30 * 32, 24 * 32, 32, 32),
                new TextureRegion(atlas, 31 * 32, 24 * 32, 32, 32),
                new TextureRegion(atlas, 31 * 32, 10 * 32, 32, 32));

        greenExplosion = new Animation(0.1f,
                new TextureRegion(atlas, 28 * 32, 25 * 32, 32, 32),
                new TextureRegion(atlas, 29 * 32, 25 * 32, 32, 32),
                new TextureRegion(atlas, 30 * 32, 25 * 32, 32, 32),
                new TextureRegion(atlas, 31 * 32, 25 * 32, 32, 32),
                new TextureRegion(atlas, 31 * 32, 11 * 32, 32, 32));

        redExplosion = new Animation(0.1f,
                new TextureRegion(atlas, 28 * 32, 26 * 32, 32, 32),
                new TextureRegion(atlas, 29 * 32, 26 * 32, 32, 32),
                new TextureRegion(atlas, 30 * 32, 26 * 32, 32, 32),
                new TextureRegion(atlas, 31 * 32, 26 * 32, 32, 32),
                new TextureRegion(atlas, 31 * 32, 12 * 32, 32, 32));

        blueExplosion = new Animation(0.1f,
                new TextureRegion(atlas, 28 * 32, 27 * 32, 32, 32),
                new TextureRegion(atlas, 29 * 32, 27 * 32, 32, 32),
                new TextureRegion(atlas, 30 * 32, 27 * 32, 32, 32),
                new TextureRegion(atlas, 31 * 32, 27 * 32, 32, 32),
                new TextureRegion(atlas, 31 * 32, 13 * 32, 32, 32));

        purpleExplosion = new Animation(0.1f,
                new TextureRegion(atlas, 28 * 32, 28 * 32, 32, 32),
                new TextureRegion(atlas, 29 * 32, 28 * 32, 32, 32),
                new TextureRegion(atlas, 30 * 32, 28 * 32, 32, 32),
                new TextureRegion(atlas, 31 * 32, 28 * 32, 32, 32),
                new TextureRegion(atlas, 31 * 32, 14 * 32, 32, 32));


        //level select screens
        select1 = new Texture(game, "level_select/Level_Select_Menu1.jpg");
        select1Region = new TextureRegion(select1, 0, 0, 512, 1024);
        select2 = new Texture(game, "level_select/Level_Select_Menu2.jpg");
        select2Region = new TextureRegion(select2, 0, 0, 512, 1024);
        select3 = new Texture(game, "level_select/Level_Select_Menu3.jpg");
        select3Region = new TextureRegion(select3, 0, 0, 512, 1024);
        select4 = new Texture(game, "level_select/Level_Select_Menu4.jpg");
        select4Region = new TextureRegion(select4, 0, 0, 512, 1024);
        select5 = new Texture(game, "level_select/Level_Select_Menu5.jpg");
        select5Region = new TextureRegion(select5, 0, 0, 512, 1024);

        // human-alien turn confirmation popups
        humanTurnConfirm = new Texture(game, "Human_Turn_pwr2.jpg");
        humanTurnConfirmRegion = new TextureRegion(humanTurnConfirm, 0, 0, 512, 256);
        alienTurnConfirm = new Texture(game, "Alien_Turn_pwr2.jpg");
        alienTurnConfirmRegion = new TextureRegion(alienTurnConfirm, 0, 0, 512, 256);

        // music and sounds

        victoryShort = game.getAudio().newMusic("sounds/victory-final.ogg");
        victoryShort.setLooping(true);
        victoryShort.setVolume(Settings.DEFAULT_VOLUME);

        menuMusic = game.getAudio().newMusic("sounds/menumusic.ogg");
        menuMusic.setLooping(true);
        menuMusic.setVolume(Settings.DEFAULT_VOLUME);


        // menuMusic is default song to play
        currentMusic = menuMusic;

        if(Settings.soundEnabled) {
            currentMusic.play();
        }

        beginTurn = game.getAudio().newSound("sounds/begin-turn.ogg");
        blockDestroy = game.getAudio().newSound("sounds/block-destroy.ogg");
        bump = game.getAudio().newSound("sounds/bump.ogg");
        cantGoHere = game.getAudio().newSound("sounds/cant-go-here.ogg");
        death = game.getAudio().newSound("sounds/death.ogg");
        error1 = game.getAudio().newSound("sounds/error1.ogg");
        error2 = game.getAudio().newSound("sounds/error2.ogg");
        menuClick = game.getAudio().newSound("sounds/menu-click.ogg");
        menuClose = game.getAudio().newSound("sounds/menu-close.ogg");
        menuSelect = game.getAudio().newSound("sounds/menu-select.ogg");
        pickup = game.getAudio().newSound("sounds/pickup.ogg");
        powerup = game.getAudio().newSound("sounds/health-pickup-new.ogg");
        shotSound = game.getAudio().newSound("sounds/shot.ogg");
        warp = game.getAudio().newSound("sounds/warp.ogg");

        blue_shot = game.getAudio().newSound("sounds/blue.ogg");
        blue_activate = game.getAudio().newSound("sounds/blue-activate.ogg");
        green_shot = game.getAudio().newSound("sounds/green.ogg");
        green_activate = game.getAudio().newSound("sounds/green-activate.ogg");
        player_death = game.getAudio().newSound("sounds/player-death.ogg");
        purple_shot = game.getAudio().newSound("sounds/purple.ogg");
        purple_activate = game.getAudio().newSound("sounds/purple-engage-new.ogg");
        red_death = game.getAudio().newSound("sounds/red-death.ogg");
        red_death_short = game.getAudio().newSound("sounds/red-death-short.ogg");
        satellite_destroy_no_pu = game.getAudio().newSound("sounds/satellite-destroy-no-powerup.ogg");
        satellite_destory_pu = game.getAudio().newSound("sounds/satellite-destroy-powerup-drop.ogg");
        take_damage = game.getAudio().newSound("sounds/take-damage.ogg");

	}
	
	@SuppressWarnings("unused")
    public static void reload() {

        //only texture files are lost
        atlas.reload();

        //...and sounds
        if(Settings.soundEnabled) {
            currentMusic.play();
        }

	}

    public static void changeMusic(Music newMusic) {
        currentMusic.stop();

        currentMusic = newMusic;

        if(Settings.soundEnabled) {
            currentMusic.play();
        }
    }

    public static void playSound(Sound sound) {
        if(Settings.soundEnabled) {
            sound.play(1);
        }
    }

    public static void randomSong() {
        Random rand = new Random();

        float test = rand.nextFloat();

        if(test <= 0.25f) {

            if(spacebeat != null) {
                changeMusic(spacebeat);
            } else {
                spacebeat = gameRef.getAudio().newMusic("sounds/spacebeat.ogg");
                spacebeat.setLooping(true);
                spacebeat.setVolume(Settings.DEFAULT_VOLUME);

                changeMusic(spacebeat);
            }


        } else if(test > 0.25 && test <= 0.5) {

            if(cosmic1 != null) {
                changeMusic(cosmic1);
            } else {
                cosmic1 = gameRef.getAudio().newMusic("sounds/cosmic1.ogg");
                cosmic1.setLooping(true);
                cosmic1.setVolume(Settings.DEFAULT_VOLUME);

                changeMusic(cosmic1);
            }


        } else if(test > 0.5 && test <= 0.75) {

            if(cosmic2 != null) {
                changeMusic(cosmic2);
            } else {
                cosmic2 = gameRef.getAudio().newMusic("sounds/cosmic2.ogg");
                cosmic2.setLooping(true);
                cosmic2.setVolume(Settings.DEFAULT_VOLUME);

                changeMusic(cosmic2);
            }


        } else {

            if(cosmic3 != null) {
                changeMusic(cosmic3);
            } else {
                cosmic3 = gameRef.getAudio().newMusic("sounds/cosmic3.ogg");
                cosmic3.setLooping(true);
                cosmic3.setVolume(Settings.DEFAULT_VOLUME);

                changeMusic(cosmic3);
            }

        }
    }

    public static Texture randomBackground() {
        Random rand = new Random();

        float test = rand.nextFloat();

        if(test <= 0.25f) {
            return background_2_UI;
        } else if(test > 0.25 && test <= 0.5) {
            return background_3_UI;
        } else if(test > 0.5 && test <= 0.75) {
            return background_4_UI;
        } else {
            return background_5_UI;
        }
    }

    /*public static TextureRegion getRegionForBackground(Texture background) {

    }*/
	
}
