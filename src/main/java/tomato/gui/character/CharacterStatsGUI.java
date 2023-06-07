package tomato.gui.character;

import assets.AssetMissingException;
import assets.IdToAsset;
import assets.ImageBuffer;
import tomato.realmshark.RealmCharacter;
import tomato.backend.data.TomatoData;
import tomato.gui.maingui.TomatoGUI;
import tomato.realmshark.enums.CharacterStatistics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Comparator;

/**
 * Dungeon completion display GUI class showing all dungeon completions of the users account.
 */
public class CharacterStatsGUI extends JPanel {
    private static CharacterStatsGUI INSTANCE;
    private final JPanel left, right;
    private final int dungeonCount;
    private boolean sortOrder;
    private final TomatoData data;
    private int charCount;
    private JLabel[][] labels;

    public CharacterStatsGUI(TomatoData data) {
        INSTANCE = this;
        this.data = data;

        JPanel top = new JPanel();
        left = new JPanel();
        right = new JPanel();
        JPanel topLeft = new JPanel();

        TomatoGUI.panelsToChangeColor.add(top);
        TomatoGUI.panelsToChangeColor.add(left);
        TomatoGUI.panelsToChangeColor.add(right);
        TomatoGUI.panelsToChangeColor.add(topLeft);


        JScrollPane spTop = new JScrollPane(top);
        JScrollPane spLeft = new JScrollPane(left);
        JScrollPane spRight = new JScrollPane(right);

        spRight.getHorizontalScrollBar().setModel(spTop.getHorizontalScrollBar().getModel());
        spRight.getVerticalScrollBar().setModel(spLeft.getVerticalScrollBar().getModel());
        spRight.getVerticalScrollBar().setUnitIncrement(9);

        spLeft.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        spRight.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        spRight.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        spTop.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        setLayout(new BorderLayout());
        JPanel leftBar = new JPanel();
        JPanel rightBar = new JPanel();
        TomatoGUI.panelsToChangeColor.add(leftBar);
        TomatoGUI.panelsToChangeColor.add(rightBar);


        leftBar.setLayout(new BorderLayout());
        rightBar.setLayout(new BorderLayout());
        add(leftBar, BorderLayout.WEST);
        add(rightBar, BorderLayout.CENTER);
        topLeft.setPreferredSize(new Dimension(0, 37));

        leftBar.add(topLeft, BorderLayout.NORTH);
        leftBar.add(spLeft, BorderLayout.CENTER);
        rightBar.add(spTop, BorderLayout.NORTH);
        rightBar.add(spRight, BorderLayout.CENTER);

        ToolTipManager.sharedInstance().setInitialDelay(200);
        ToolTipManager.sharedInstance().setDismissDelay(1000000000);

        dungeonCount = CharacterStatistics.DUNGEONS.size();

        topDungeonList(top);
    }

    /**
     * Creates top panel dungeon list;
     *
     * @param top Panel to add dungeon icons to.
     */
    private void topDungeonList(JPanel top) {
        top.setLayout(new GridLayout(1, dungeonCount));

        for (int j = 0; j < dungeonCount; j++) {
            BufferedImage img;
            String name;
            int id = CharacterStatistics.DUNGEONS.get(j);
            try {
                name = IdToAsset.getDisplayName(id);
                img = ImageBuffer.getImage(id);
            } catch (IOException | AssetMissingException e) {
                e.printStackTrace();
                return;
            }
            ImageIcon icon = new ImageIcon(img.getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel dungeonIcon = new JLabel(icon, JLabel.CENTER);
            dungeonIcon.setToolTipText(name);

            int finalJ = j;
            dungeonIcon.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (data.chars == null) return;
                    sortOrder = !sortOrder;
                    if (sortOrder) {
                        data.chars.sort(Comparator.comparingLong(o -> -o.charStats.dungeonStats[finalJ]));
                    } else {
                        data.chars.sort(Comparator.comparingLong(o -> o.charStats.dungeonStats[finalJ]));
                    }
                    update();
                }
            });

            JPanel p = new JPanel();
            TomatoGUI.panelsToChangeColor.add(p);
            p.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
            p.add(dungeonIcon);
            p.setPreferredSize(new Dimension(35, 37));
            top.add(p);
        }
    }

    /**
     * Method for receiving realm character list info.
     */
    public static void updateRealmChars() {
        INSTANCE.update();
    }

    /**
     * Update method clearing all the display and re-display it with the updated info.
     */
    private void update() {
        if (data.chars == null) return;

        int charCount = data.chars.size();

        if (charCount != this.charCount) {
            updatePanelWithPlayerListChanged();
            updatePlayerList();
            validate();
        } else {
            updateDungeonLabels();
            updatePlayerList();
            revalidate();
        }
    }

    /**
     * Updates dungeon labels with dungeon completes.
     */
    private void updateDungeonLabels() {
        for (int i = 0; i < charCount; i++) {
            RealmCharacter c = data.chars.get(i);

            for (int j = 0; j < dungeonCount; j++) {
                int v = c.charStats.dungeonStats[j];
                labels[i][j].setText("" + v);
            }
        }
    }

    /**
     * Updates player list on the left panel.
     */
    private void updatePlayerList() {
        left.removeAll();

        for (int i = 0; i < charCount; i++) {
            RealmCharacter c = data.chars.get(i);
            JLabel player = playerIcon(c);
            TomatoGUI.labelsToChangeColor.add(player);
            JPanel p = new JPanel(new GridBagLayout());
            TomatoGUI.panelsToChangeColor.add(p);
            p.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
            p.setPreferredSize(new Dimension(150, 27));
            p.add(player);

            left.add(p);
        }
    }

    /**
     * Flushes player dungeon complete labels and rebuilds labels with corrected player list.
     */
    private void updatePanelWithPlayerListChanged() {
        charCount = data.chars.size();

        left.setLayout(new GridLayout(charCount, 1));
        right.removeAll();
        right.setLayout(new GridLayout(charCount, dungeonCount));
        labels = new JLabel[charCount][dungeonCount];

        for (int i = 0; i < charCount; i++) {
            RealmCharacter c = data.chars.get(i);

            for (int j = 0; j < dungeonCount; j++) {
                int v = c.charStats.dungeonStats[j];
                JPanel p2 = new JPanel();
                TomatoGUI.panelsToChangeColor.add(p2);
                p2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
                labels[i][j] = new JLabel("" + v);
                TomatoGUI.labelsToChangeColor.add(labels[i][j]);
                p2.add(labels[i][j]);
                p2.setPreferredSize(new Dimension(35, 27));
                right.add(p2);
            }
        }
    }

    /**
     * Creates account character label to be displayed to the left bar showing icon fame and class name.
     *
     * @param c Character to be made into label showing icon fame and class name.
     * @return Label displaying the character.
     */
    private JLabel playerIcon(RealmCharacter c) {
        try {
            int eq = c.skin;
            if (eq == 0) eq = c.classNum;
            BufferedImage img = ImageBuffer.getImage(eq);
            ImageIcon icon = new ImageIcon(img.getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel characterLabel = new JLabel(c.classString + " " + c.fame, icon, JLabel.CENTER);
            TomatoGUI.labelsToChangeColor.add(characterLabel);
            characterLabel.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    if (data.chars == null) return;
                    sortOrder = !sortOrder;
                    if (sortOrder) {
                        data.chars.sort(Comparator.comparingLong(o -> -o.fame));
                    } else {
                        data.chars.sort(Comparator.comparingLong(o -> o.fame));
                    }
                    update();
                }
            });
            return characterLabel;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
