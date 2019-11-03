/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.BookFlights;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shahd
 */
public class DataModel extends DefaultTableModel {
    
    private static final int CHECK_COL = 1;
    private static int value= 1;

    DataModel(Vector<Boolean> data, Vector<String> header) {
        super(data, header);
    }

        @Override
        public void setValueAt(Object aValue, int row, int col) {
            if(isCellEditable(row, col))
            {    
            super.setValueAt(aValue, row, col);
            System.out.println("XYZ");
            value=2;
            
            }
            
            else{
               System.out.println("insileModel");
               super.setValueAt(aValue, row, col);
            
            }
//            else if(CHECK_COL==1&&(!isCellEditable(row, col))&&value==2){
//                
//                super.setValueAt(false, row, col);
//            
//            }
            
        }

        @Override
        public Class<?> getColumnClass(int col) {
//            if (col == CHECK_COL) {
//                return getValueAt(0, CHECK_COL).getClass();
//            }
            //return super.getColumnClass(col);
            
            return getValueAt(0, col).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            Object o = getValueAt(row, col);
            boolean b = o instanceof Boolean && (Boolean) o;
            return col == col && !b;
        
}
}