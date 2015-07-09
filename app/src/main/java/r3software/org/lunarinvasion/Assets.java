package r3software.org.lunarinvasion;

import r3software.org.lunarinvasion.engine.framework.Animation;
import r3software.org.lunarinvasion.engine.framework.Font;
import r3software.org.lunarinvasion.engine.framework.TextureRegion;
import r3software.org.lunarinvasion.engine.impl.GLGame;
import r3software.org.lunarinvasion.engine.impl.Texture;

@SuppressWarnings("PointlessArithmeticExpression")
public class Assets {
	
	//load up assets in this class

    //basic
    public static Texture background;
    public static Texture atlas;
    public static TextureRegion backgroundRegion;


    //ships
    public static TextureRegion humanShip;
    public static TextureRegion alienShip;

    //weapons
    public static TextureRegion orangeShot;
    public static TextureRegion greenShot;
    public static TextureRegion redShot;
    public static TextureRegion blueShot;
    public static TextureRegion missile;

    public static TextureRegion drone;
    public static TextureRegion alienDrone;

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
    public static TextureRegion platform_4x2_breakable_v;

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

    public static void load(GLGame game) {

        //background = new Texture(game, "Space_BG_2.png");
        background = new Texture(game, "Space_BG_2_pwr2.png");
        atlas = new Texture(game, "Sprite_Atlas_11.png");
        menuAtlas = new Texture(game, "Menu_Sheet_2.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 512, 1024);
        menuBackground = new Texture (game, "Menu_BG_2_pwr2.png");
        menuBackgroundRegion = new TextureRegion(menuBackground, 0, 0, 512, 1024);

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

        platform_4x2_breakable_v = new TextureRegion(atlas, 0 * 32, 18 * 32, 2 * 32, 4 * 32);
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
        //TODO: Programming is spelled wrong on the atlas
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

	}
	
	public static void reload() {

        //only texture files are lost
        background.reload();
        atlas.reload();

	}
	

	
}
