package com.gildedgames.aether.core;

import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import org.apache.commons.lang3.tuple.Pair;

public class AetherConfig
{
    public static class Common {
        public final ConfigValue<Boolean> start_with_portal;
        public final ConfigValue<Boolean> enable_startup_loot;
        public final ConfigValue<Boolean> edible_ambrosium;
        public final ConfigValue<Integer> maximum_life_shards;
        public final ConfigValue<Boolean> repeat_sun_spirit_dialogue;

        public final ConfigValue<Boolean> spawn_golden_feather;
        public final ConfigValue<Boolean> spawn_valkyrie_cape;

        public final ConfigValue<Boolean> generate_tall_grass;
        public final ConfigValue<Boolean> generate_pink_aerclouds;
        public final ConfigValue<Boolean> generate_holiday_tree_always;
        public final ConfigValue<Boolean> generate_holiday_tree_seasonally;

        public final ConfigValue<Boolean> admin_sun_altar;

        public final ConfigValue<Boolean> disable_aether_portal;
        public final ConfigValue<Boolean> activate_with_skyroot_bucket;
        public final ConfigValue<Boolean> disable_eternal_day;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("Gameplay");
            start_with_portal = builder
                    .comment("On world creation, the player is given an Aether Portal Frame item to automatically go to the Aether with")
                    .define("Gives player Aether Portal Frame item", false);
            enable_startup_loot = builder
                    .comment("When the player enters the Aether, they are given a Book of Lore and Golden Parachutes as starting loot")
                    .define("Gives starting loot on entry", true);
            edible_ambrosium = builder
                    .comment("Ambrosium Shards can be eaten to restore a half heart of health")
                    .define("Ambrosium Shards are edible", false);
            maximum_life_shards = builder
                    .comment("Determines the limit of the amount of Life Shards a player can consume to increase their health")
                    .define("Maximum consumable Life Shards", 10);
            repeat_sun_spirit_dialogue = builder
                    .comment("Determines whether the Sun Spirit's dialogue when meeting him should play through every time you meet him")
                    .define("Repeat Sun Spirit's battle dialogue", true);
            builder.pop();

            builder.push("Loot");
            spawn_golden_feather = builder
                    .comment("Allows the Golden Feather to spawn in the Silver Dungeon loot table")
                    .define("Golden Feather in loot", true);
            spawn_valkyrie_cape = builder
                    .comment("Allows the Valkyrie Cape to spawn in the Silver Dungeon loot table")
                    .define("Valkyrie Cape in loot", false);
            builder.pop();

            builder.push("World Generation");
            generate_tall_grass = builder
                    .comment("Determines whether the Aether should generate Tall Grass blocks on terrain or not")
                    .define("Generate Tall Grass in the Aether", false);
            generate_pink_aerclouds = builder
                    .comment("Determines whether Pink Aerclouds should generate in the skies of the Aether along with other Aerclouds")
                    .define("Generate Pink Aerclouds", false);
            generate_holiday_tree_always = builder
                    .comment("Determines whether Holiday Trees should always be able to generate when exploring new chunks in the Aether",
                            "If true, this overrides 'generate_holiday_tree_seasonally'")
                    .define("Generate Holiday Trees always", false);
            generate_holiday_tree_seasonally = builder
                    .comment("Determines whether Holiday Trees should be able to generate during the time frame of December and January when exploring new chunks in the Aether",
                            "Only works if 'generate_holiday_tree_always' is set to false")
                    .define("Generate Holiday Trees seasonally", true);
            builder.pop();

            builder.push("Multiplayer");
            admin_sun_altar = builder
                    .comment("Makes it so that only the server admin or anyone with permission level 4 can use the Sun Altar on a server")
                    .define("Only admins use Sun Altars", true);
            builder.pop();

            builder.push("Modpack");
            disable_aether_portal = builder
                    .comment("Prevents the Aether Portal from being created normally in the mod")
                    .define("Disables Aether Portal creation", false);
            activate_with_skyroot_bucket = builder
                    .comment("The Aether Portal can only be activated with an item tagged as a Skyroot Bucket")
                    .define("Aether Portals activate with Skyroot Buckets", false);
            disable_eternal_day = builder
                    .comment("Removes eternal day so that the Aether has a normal daylight cycle even before defeating the Sun Spirit")
                    .define("Disables eternal day", false);
            builder.pop();
        }
    }

    public static class Client {
        public final ConfigValue<Boolean> legacy_models;

        public final ConfigValue<Boolean> enable_aether_menu;
        public final ConfigValue<Boolean> enable_aether_menu_button;
        public final ConfigValue<Boolean> enable_trivia;

        public final ConfigValue<Boolean> install_resource_packs;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.push("Rendering");
            legacy_models = builder
                    .comment("Changes Zephyr and Aerwhale rendering to use their old models from the b1.7.3 version of the mod")
                    .define("Switches to legacy mob models", false);
            builder.pop();

            builder.push("Gui");
            enable_aether_menu = builder
                    .comment("Changes the vanilla Minecraft menu into the Aether menu")
                    .define("Enables Aether menu", false);
            enable_aether_menu_button = builder
                    .comment("Adds a button to the top right of the main menu screen to toggle between the Aether and vanilla menu")
                    .define("Enables Aether menu button", true);
            enable_trivia = builder
                    .comment("Adds random trivia and tips to the bottom of loading screens")
                    .define("Enables random trivia", true);
            builder.pop();

            builder.push("Resource Pack");
            install_resource_packs = builder
                    .comment("Allows the built-in programmer art resource packs for b1.7.3 and 1.2.5 textures to be automatically downloaded")
                    .define("Installs resource packs", true);
            builder.pop();
        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();

        final Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}
