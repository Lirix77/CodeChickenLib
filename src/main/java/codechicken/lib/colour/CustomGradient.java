package codechicken.lib.colour;

import java.awt.image.BufferedImage;

import net.minecraft.util.ResourceLocation;

import codechicken.lib.math.MathHelper;
import codechicken.lib.render.TextureUtils;

public class CustomGradient {

    public int[] gradient;

    public CustomGradient(ResourceLocation textureFile) {
        BufferedImage img = TextureUtils.loadBufferedImage(textureFile);
        if (img == null) throw new RuntimeException("File not found: " + textureFile.toString());

        int[] data = new int[img.getWidth()];
        img.getRGB(0, 0, img.getWidth(), 1, data, 0, img.getWidth());
        gradient = new int[img.getWidth()];
        for (int i = 0; i < data.length; i++) gradient[i] = (data[i] << 8) | (((data[i]) >> 24) & 0xFF);
    }

    public ColourRGBA getColour(double position) {
        return new ColourRGBA(getColourI(position));
    }

    public int getColourI(double position) {
        int off = (int) MathHelper.clip(gradient.length * position, 0, gradient.length - 1);
        return gradient[off];
    }
}
