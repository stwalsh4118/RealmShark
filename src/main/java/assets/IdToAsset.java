package assets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Id to asset class. Used to convert incoming realm IDs to the corresponding asset.
 */
public class IdToAsset {
    private final String l;
    private final int id;
    private final String idName;
    private final String display;
    private final String clazz;
    private final String group;
    private final String projectile;
    private Projectile[] projectiles = null;
    private final String texture;
    private Texture[] textures = null;
    private static final HashMap<Integer, IdToAsset> objectID = new HashMap<>();
    private static final HashMap<Integer, IdToAsset> tileID = new HashMap<>();

    /**
     * Constructor for the object resources.
     *
     * @param l          Base string before parsing
     * @param id         Id of the resource
     * @param idName     Name of the resource
     * @param display    Display name of the resource
     * @param clazz      Class of the resource
     * @param projectile Projectile min,max,armorPiercing,(repeated) listed
     * @param texture    Texture name and index used to find the image
     * @param group      Group of the resource
     */
    public IdToAsset(String l, int id, String idName, String display, String clazz, String projectile, String texture, String group) {
        this.l = l;
        this.id = id;
        this.idName = idName;
        this.display = display;
        this.clazz = clazz;
        this.projectile = projectile;
        this.texture = texture;
        this.group = group;
    }

    /**
     * Constructor for the tile resources.
     *
     * @param l       Base string before parsing
     * @param id      Id of the resource
     * @param idName  Name of the resource
     * @param texture Texture name and index used to find the image
     */
    public IdToAsset(String l, int id, String idName, String texture) {
        this.l = l;
        this.id = id;
        this.idName = idName;
        this.texture = texture;

        display = "";
        clazz = "";
        group = "";
        projectile = "";
    }

    /**
     * Construct the list on start of using this class.
     */
    static {
        readObjectList();
        readTileList();
    }

    /**
     * Reloads assets from files.
     */
    public static void reloadAssets() {
        objectID.clear();
        tileID.clear();
        readObjectList();
        readTileList();
    }

    /**
     * Method to grab the full list of object resource's from file and construct the hashmap.
     */
    private static void readObjectList() {
        File objectsFile = new File(AssetExtractor.ASSETS_OBJECT_FILE_DIR_PATH);
        if (!objectsFile.exists()) return;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(objectsFile)));
            String line;

            while ((line = br.readLine()) != null) {
                String[] l = line.split(";");
                int id = Integer.parseInt(l[0]);
                String display = l[1];
                String clazz = l[2];
                String group = l[3];
                String projectile = l[4];
                String texture = l[5];
                String idName = l[6];
                objectID.put(id, new IdToAsset(line, id, idName, display, clazz, projectile, texture, group));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        objectID.put(-1, new IdToAsset("", -1, "Unloaded", "Unloaded", "", "", "", "Unloaded"));
    }

    /**
     * Method to grab the full list of tile resource's from file and construct the hashmap.
     */
    private static void readTileList() {
        File tilesFile = new File(AssetExtractor.ASSETS_TILE_FILE_DIR_PATH);
        if (!tilesFile.exists()) return;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(tilesFile)));
            String line;

            while ((line = br.readLine()) != null) {
                String[] l = line.split(";");
                int id = Integer.parseInt(l[0]);
                String texture = l[1];
                String idName = l[2];
                tileID.put(id, new IdToAsset(line, id, idName, texture));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tileID.put(-1, new IdToAsset("", -1, "Unknown", ""));
    }

    /**
     * Method to grab the name of the object resource.
     * If display name is not present, use the regular name.
     *
     * @param id Id of the object.
     * @return Best descriptive name of the resource
     */
    public static String objectName(int id) throws AssetMissingException {
        IdToAsset i = objectID.get(id);
        if (i.display.equals("")) return i.idName;
        return i.display;
    }

    /**
     * Method to grab the name of the tile resource.
     * If display name is not present, use the regular name.
     *
     * @param id Id of the tile.
     * @return Best descriptive name of the resource
     */
    public static String tileName(int id) throws AssetMissingException {
        IdToAsset i = tileID.get(id);
        return i.idName;
    }

    /**
     * Common name of the object.
     *
     * @param id Id of the object.
     * @return Regular name of the object.
     */
    public static String getOjbectIdName(int id) throws AssetMissingException {
        IdToAsset i = objectID.get(id);
        return i.idName;
    }

    /**
     * Display name of the object.
     *
     * @param id Id of the object.
     * @return Display name of the object.
     */
    public static String getDisplayName(int id) throws AssetMissingException {
        IdToAsset i = objectID.get(id);
        return i.display;
    }

    /**
     * Class of the object.
     *
     * @param id Id of the object.
     * @return Class name of the object.
     */
    public static String getClazz(int id) throws AssetMissingException {
        IdToAsset i = objectID.get(id);
        return i.clazz;
    }

    /**
     * Group of the object.
     *
     * @param id Id of the object.
     * @return Group name of the object.
     */
    public static String getIdGroup(int id) throws AssetMissingException {
        IdToAsset i = objectID.get(id);
        return i.group;
    }

    /**
     * Parses the projectile string to the number of projectiles the entity can shoot.
     *
     * @param entity that should be projectile parsed
     * @return List of parsed projectiles
     */
    private static Projectile[] parseProjectile(IdToAsset entity) throws AssetMissingException {
        String[] l = entity.projectile.split(",");
        Projectile[] p = new Projectile[l.length / 3];
        int index = 0;
        for (int i = 0; i < l.length; i += 3) {
            int min = Integer.parseInt(l[i]);
            int max = Integer.parseInt(l[1 + i]);
            boolean ap = l[2 + i].equals("1");
            p[index] = new Projectile(min, max, ap);
            index++;
        }

        return p;
    }

    /**
     * Parses the texture string to the texture object.
     *
     * @param entity that should be texture parsed
     * @return List of parsed textures
     */
    private static Texture[] parseObjectTexture(IdToAsset entity) throws AssetMissingException {
        String[] l = entity.texture.split(",");
        Texture[] t = new Texture[l.length / 2];
        int index = 0;
        try {
            for (int i = 0; i < l.length; i += 2) {
                String name = l[i + 1];
                int ix = Integer.parseInt(l[i]);
                t[index] = new Texture(name, ix);
                index++;
            }
        } catch (Exception e) {
            System.out.println(entity);
        }
        return t;
    }

    /**
     * Minimum damage of weapon.
     *
     * @param id           Id of the object.
     * @param projectileId Bullet sub id
     * @return Minimum damage
     */
    public static int getIdProjectileMinDmg(int id, int projectileId) throws AssetMissingException {
        IdToAsset i = objectID.get(id);
        if (i.projectiles == null) i.projectiles = parseProjectile(i);
        return i.projectiles[projectileId].min;
    }

    /**
     * Maximum damage of weapon.
     *
     * @param id           Id of the object.
     * @param projectileId Bullet sub id
     * @return Maximum damage
     */
    public static int getIdProjectileMaxDmg(int id, int projectileId) throws AssetMissingException {
        IdToAsset i = objectID.get(id);
        if (i.projectiles == null) i.projectiles = parseProjectile(i);
        return i.projectiles[projectileId].max;
    }

    /**
     * Maximum damage of weapon.
     *
     * @param id           Id of the object.
     * @param projectileId Bullet sub id
     * @return Maximum damage
     */
    public static boolean getIdProjectileArmorPierces(int id, int projectileId) throws AssetMissingException {
        IdToAsset i = objectID.get(id);
        if (i.projectiles == null) i.projectiles = parseProjectile(i);
        return i.projectiles[projectileId].ap;
    }

    /**
     * Object texture file name.
     *
     * @param id  Id of the object.
     * @param num Sub texture number
     * @return File name of the texture
     */
    public static String getObjectTextureName(int id, int num) throws AssetMissingException {
        IdToAsset i = objectID.get(id);
        if (i != null && i.textures == null) i.textures = parseObjectTexture(i);
        try {
            return i.textures[num].name;
        } catch (Exception e) {
//            System.out.println(id + " " + i);
            return null;
        }
    }

    /**
     * Object texture file index.
     *
     * @param id  Id of the object.
     * @param num Sub texture number
     * @return File index of the texture
     */
    public static int getObjectTextureIndex(int id, int num) throws AssetMissingException {
        IdToAsset i = objectID.get(id);
        if (i.textures == null) i.textures = parseObjectTexture(i);
        try {
            return i.textures[num].index;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(id + " " + i);
            return -1;
        }
    }

    /**
     * Tile texture file name.
     *
     * @param id  Id of the object.
     * @param num Sub texture number
     * @return File name of the texture
     */
    public static String getTileTextureName(int id, int num) throws AssetMissingException {
        IdToAsset i = tileID.get(id);
        if (i.textures == null) i.textures = parseObjectTexture(i);
        return i.textures[num].name;
    }

    /**
     * Tile texture file index.
     *
     * @param id  Id of the object.
     * @param num Sub texture number
     * @return File index of the texture
     */
    public static int getTileTextureIndex(int id, int num) throws AssetMissingException {
        IdToAsset i = tileID.get(id);
        if (i.textures == null) i.textures = parseObjectTexture(i);
        return i.textures[num].index;
    }

    /**
     * Checks if the tile id exists.
     *
     * @param id Id of the tile.
     * @return True if the tile ID exists.
     */
    public static boolean tileIdExists(int id) {
        return tileID.containsKey(id);
    }

    /**
     * Simple class to store projectile info
     */
    private static class Projectile {
        int min; // min dmg
        int max; // max dmg
        boolean ap; // armor piercing

        public Projectile(int min, int max, boolean ap) {
            this.min = min;
            this.max = max;
            this.ap = ap;
        }
    }

    /**
     * Simple class to store texture info
     */
    private static class Texture {
        String name;
        int index;

        public Texture(String name, int index) {
            this.name = name;
            this.index = index;
        }
    }

    public String toString() {
        return l;
    }
}
