package com.shz.foundation.captcha;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;

public class CaptchaGenerater {

	private static final List<Color> COLORS = new ArrayList<Color>(2);
    private static final List<Font> FONTS = new ArrayList<Font>(3);
    
    private static int width = 175;
    private static int height = 75;
    
    public static void setWidth(int width) {
		CaptchaGenerater.width = width;
	}
    public static void setHeight(int height) {
		CaptchaGenerater.height = height;
	}
    
    static {
        COLORS.add(Color.BLACK);

        FONTS.add(new Font("Geneva", Font.ITALIC, 48));
        FONTS.add(new Font("Courier", Font.BOLD, 48));
        FONTS.add(new Font("Arial", Font.BOLD, 48));
    }
    
	public static Captcha generateCaptcha() {
		DefaultWordRenderer wordRenderer=new DefaultWordRenderer(COLORS, FONTS);
        Captcha captcha = new Captcha.Builder(width, height).addText(wordRenderer)
                .gimp()
                //.addNoise()
                .addBackground(new GradiatedBackgroundProducer(Color.LIGHT_GRAY,Color.WHITE))
                .build();
        return captcha;    
	}
}
