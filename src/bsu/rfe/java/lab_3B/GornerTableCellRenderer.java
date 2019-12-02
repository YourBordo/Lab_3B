package bsu.rfe.java.lab_3B;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import static java.lang.Character.getNumericValue;

public class GornerTableCellRenderer implements TableCellRenderer {

    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();

    private String needle = null;
    public void setNeedle(String needle) {
        this.needle = needle;
    }


    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    public GornerTableCellRenderer() {
        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
    }


    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            String formattedDouble = "";
        if (col == 0 || col == 1) {
            formattedDouble = formatter.format(value);
            label.setText(formattedDouble);
        }
        boolean flag = false;
        if (col == 0 && needle!=null && needle.equals(formattedDouble)) {
            panel.setBackground(Color.RED);
        }
        else if (col == 1) {
            for (int i = 0; i < formattedDouble.length(); ++i) {
                if (formattedDouble.charAt(i) == '.') {
                    flag = true;
                    i++;
                }
                if (flag) {
                    int temp = 0;
                    for (; i < formattedDouble.length(); ++i) {
                        temp += getNumericValue(formattedDouble.charAt(i));
                    }
                    if (temp % 10 == 0) {
                        panel.setBackground(Color.PINK);
                    }
                    else{
                        panel.setBackground(Color.WHITE);
                    }
                    break;
                }
            }
        }
        else {
            panel.setBackground(Color.WHITE);
        }
        return panel;
    }
}
