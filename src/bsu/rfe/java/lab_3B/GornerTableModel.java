package bsu.rfe.java.lab_3B;

import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {

    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    private Double result;
    private Boolean check;
    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    @Override
    public int getRowCount() {
        return new Double(Math.ceil((to-from)/step)).intValue() + 1;
    }
    @Override
    public Object getValueAt(int row, int col) {
        double x = from + step*row;
        switch (col){
            case 0:
                return x;
            case 1:
            {
                result = 0.0;
                for(int i = 0; i < coefficients.length; i++){
                    result += Math.pow(x, coefficients.length-1-i)*coefficients[i];
                }
                return result;
            }
            default:
                String temp = result.toString();
                String p1 = "";
                String p2 = "";
                boolean flag = true;
                for (int i = 0; i <temp.length(); ++i){
                    if (temp.charAt(i) == '.'){
                        flag = false;
                        i++;
                    }
                    if (flag){
                        p1 += temp.charAt(i);
                    }
                    else{
                        p2 += temp.charAt(i);
                    }
                }
                return p1.equals(p2);
        }

    }
    @Override
    public Class<?> getColumnClass(int col) {
        if (col == 0 || col == 1){
            return Double.class;
        }
        else{
            return Boolean.class;
        }
    }
    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 2:
                return "Ограниченная симметрия";
            default:
                return "Значение многочлена";
        }

    }

}
