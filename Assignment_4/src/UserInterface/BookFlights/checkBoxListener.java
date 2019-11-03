/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.BookFlights;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author shahd
 */
public class checkBoxListener implements TableModelListener{
    
    
    
    public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
           
                TableModel model = (TableModel) e.getSource();
                String columnName = model.getColumnName(column);
                
                Boolean checked = (Boolean) model.getValueAt(row, column);
                if (checked) {
                    System.out.println(columnName + ": " + true);
                    
                } else {
                    System.out.println(columnName + ": " + false);
                }
            }
        
}
