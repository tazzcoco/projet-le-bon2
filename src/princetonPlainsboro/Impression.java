/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package princetonPlainsboro;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.Color;

/**
 *
 * @author Manounette
 */
public class Impression implements Printable {

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        int res;
        res = Printable.NO_SUCH_PAGE;
        switch (pageIndex) {
            case 0: {
                double xLeft = pageFormat.getImageableX();
                double yTop = pageFormat.getImageableY();
                double width = pageFormat.getImageableWidth();
                double height = pageFormat.getImageableHeight();

                graphics.setColor(Color.BLACK);
                graphics.drawRect((int) xLeft,
                        (int) yTop,
                        (int) width,
                        (int) height);

                res = Printable.PAGE_EXISTS;
                break;
            }
            case 1: {
                double xLeft = pageFormat.getImageableX();
                double yTop = pageFormat.getImageableY();
                double width = pageFormat.getImageableWidth();
                double height = pageFormat.getImageableHeight();

                graphics.setColor(Color.BLACK);
                graphics.drawOval((int) xLeft,
                        (int) yTop,
                        (int) width,
                        (int) height);

                res = Printable.PAGE_EXISTS;
                break;
            }
        }

        return res;
    }

}
