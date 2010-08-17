/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpowder.chartTools;

import java.text.DecimalFormat;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnit;
import org.jfree.chart.axis.TickUnitSource;

/**
 *
 * @author
 */
public class JpowderTickUnitSourceYAxis implements TickUnitSource {

    private static double LOG_10_VALUE = Math.log(10.0);

    public JpowderTickUnitSourceYAxis() {
        super();
    }

    @Override
    public TickUnit getLargerTickUnit(TickUnit unit) {
        double x = unit.getSize();
        double log = Math.log(x) / LOG_10_VALUE;
        double higher = Math.ceil(log);
//        System.out.println("X"+x);
//        System.out.println("high : "+higher);
//        System.out.println("Math.pow(10, higher) : "+Math.pow(10, higher));
        return new NumberTickUnit(Math.pow(10, higher),
                new DecimalFormat(AxisFormatPanel.getDecimalPattern()));

    }

    @Override
    public TickUnit getCeilingTickUnit(TickUnit unit) {
        return getLargerTickUnit(unit);
    }

    @Override
    public TickUnit getCeilingTickUnit(double size) {
        double log = Math.log(size) / LOG_10_VALUE;
        double higher = Math.ceil(log);
        return new NumberTickUnit(Math.pow(10, higher),
                new DecimalFormat(AxisFormatPanel.getDecimalPattern()));
    }
}
