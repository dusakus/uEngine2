package dcode.games.uEngine2.games.ugametoolkit.uiinput;

import dcode.games.uEngine2.BGTasks.PBGTask;
import dcode.games.uEngine2.StData;
import dcode.games.uEngine2.games.ugametoolkit.randomParts.StringProvider;
import dcode.games.uEngine2.tools.Shortcuts;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by dusakus on 01.05.15.
 */
public class Button {

    protected Image[] textures;
    private Rectangle Bounds;
    private StringProvider Text;
    public int currentTexId;
    private IButtonInputReceiver reciv;
    protected boolean renderable = false;
    private String texKey = "missing";
    private boolean countdownRunning = false;
    private int lasttid = 0;
    private int countdown = 0;
    private String font;

    public Button(IButtonInputReceiver receiver, String sourcetexID, StringProvider text, Rectangle bounds, String font) {
        textures = new BufferedImage[4];
        Bounds = bounds;
        Text = text;
        reciv = receiver;
        texKey = sourcetexID;
        this.font = font;
        for (int i = 0; i < textures.length; i++) {
            textures[i] = Shortcuts.getTexture(sourcetexID);
        }
    }


    public Rectangle getBounds() {
        return Bounds;
    }

    public IButtonInputReceiver getHandler() {
        /*if (!Text.getLAstValue().equals(Text.getValue()))*/
        Shortcuts.registerOneTimeBGTask(new renderButton(this), false);
        return reciv;
    }

    public boolean canRender() {
        return renderable;
    }

    public void draw(Graphics2D g2D) {
        if (countdownRunning) {
            countdown--;
            if (countdown == 0) {
                countdownRunning = false;
                currentTexId = lasttid;

            }
        }
        g2D.drawImage(textures[currentTexId], Bounds.x, Bounds.y, null);
    }

    public void setID(int ID) {
        this.currentTexId = ID;
    }

    public void setIDforT(int i, int i1) {
        if (countdownRunning) return;
        lasttid = currentTexId;
        countdownRunning = true;
        countdown = i1;
        currentTexId = i;
    }


    private class renderButton extends PBGTask {

        private Button target;

        private renderButton(Button target) {
            this.target = target;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void perform() {
            target.renderable = false;
            String string = target.Text.getValue();

            for (int i = 0; i < target.textures.length; i++) {
                target.textures[i] = new BufferedImage(target.Bounds.width, target.Bounds.height, BufferedImage.TYPE_INT_ARGB);
                drawBack(target.textures[i], target.texKey + i);
                drawText(target.textures[i], string);
            }


            target.renderable = true;
        }

        private void drawText(Image texture, String string) {
            Image f = StData.resources.grf.getString(target.font, string);
            Graphics g = texture.getGraphics();
            g.drawImage(f.getScaledInstance(target.Bounds.width - 8, target.Bounds.height - 8, Image.SCALE_REPLICATE), 4, 4, null);
        }

        private void drawBack(Image texture, String s) {
            Graphics g = texture.getGraphics();
            g.drawImage(StData.resources.grf.getPartTexture(s, 0, 0, 7, 7), 0, 0, null);
            g.drawImage(StData.resources.grf.getPartTexture(s, 0, 9, 7, 7), 0, target.Bounds.height - 7, null);
            g.drawImage(StData.resources.grf.getPartTexture(s, 9, 0, 7, 7), target.Bounds.width - 7, 0, null);
            g.drawImage(StData.resources.grf.getPartTexture(s, 9, 9, 7, 7), target.Bounds.width - 7, target.Bounds.height - 7, null);
            if (Bounds.height > 14) {
                int trg = Bounds.height - 14;
                while (trg > 0) {
                    g.drawImage(StData.resources.grf.getPartTexture(s, 0, 7, 7, 2), 0, 5 + trg, null);
                    g.drawImage(StData.resources.grf.getPartTexture(s, 9, 7, 7, 2), target.Bounds.width - 7, 5 + trg, null);
                    trg -= 2;
                }
            }
            if (Bounds.width > 14) {
                int trg = Bounds.width - 14;
                while (trg > 0) {
                    g.drawImage(StData.resources.grf.getPartTexture(s, 7, 0, 2, 7), 5 + trg, 0, null);
                    g.drawImage(StData.resources.grf.getPartTexture(s, 7, 9, 2, 7), 5 + trg, target.Bounds.height - 7, null);
                    trg -= 2;
                }
            }
            if (Bounds.width > 14 && Bounds.height > 14) {
                g.drawImage(StData.resources.grf.getPartTexture(s, 7, 7, 2, 2).getScaledInstance(Bounds.width - 14, Bounds.height - 14, Image.SCALE_REPLICATE), 7, 7, null);
            }
        }
    }
}
