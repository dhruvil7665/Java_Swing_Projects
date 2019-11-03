/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.BookFlights;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
//import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 *
 * @author shahd
 */
public class MyCellRenderer extends JCheckBox implements TableCellRenderer{
 private static  final TableCellRenderer RENDERER = new DefaultTableCellRenderer();
        
 
           public MyCellRenderer(){
               setOpaque(true);
               setForeground(Color.red);
               setBackground(Color.green);

}
 
        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus, int row,
                int column) {

            
         Component c = RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            System.out.println("inside render"+column+row);
           //if (column==0){
              Object result = table.getModel().getValueAt(row, column);
              System.out.println("inside if loop"+result);
//             boolean check = Boolean.parseBoolean(result.toString());
             Color color = null;
//              
              if(result.equals(true)){
                  c.setBackground(color);
                  c.setVisible(true);
                  
              }else{
                  
                 c.setEnabled(true);
              }
                  
//              
//              c.setForeground(color);
//              c.setEnabled(false);
//            }else{
//            c.setForeground(Color.red);
//            
//            
//            }
//        }
           //}
         return c;
  
}
        
 
}