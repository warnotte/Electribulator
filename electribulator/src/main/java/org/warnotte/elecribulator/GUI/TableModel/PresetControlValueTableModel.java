package org.warnotte.elecribulator.GUI.TableModel;

import javax.swing.table.AbstractTableModel;

import org.warnotte.elecribulator.PresetManager.ControlValue;
import org.warnotte.elecribulator.PresetManager.PresetManager_Synth;


public class PresetControlValueTableModel extends AbstractTableModel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8523962186884694223L;
	private String[] columnNames = new String []{"Label", "CC", "Valeur"};
    private PresetManager_Synth pm = null;

    public PresetControlValueTableModel(PresetManager_Synth pm)
    {
    	this.pm = pm;
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return pm.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
    	ControlValue o = pm.get(row);
    	if (col==0) return o.getLabel();
    	if (col==1) return o.getCC();
    	if (col==2) return o.getValue();
        return "DOH";
    }

    public Class getColumnClass(int c) {
    	return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col <= 1) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
    	ControlValue o = pm.get(row);
    	Float m = (Float) value;
    	if (col==2) o.setValue(m);
       // data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
   
}