package org.warnotte.elecribulator.GUI.TableModel;

import javax.swing.table.AbstractTableModel;

import org.warnotte.elecribulator.Multi.Thread_Arpegiateur;
import org.warnotte.elecribulator.PresetManager.ControlValue;
import org.warnotte.elecribulator.PresetManager.PresetManager_Synth;


public class ArpMultiTableModel extends AbstractTableModel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8523962186884694223L;
	private Thread_Arpegiateur pm = null;

    public ArpMultiTableModel(Thread_Arpegiateur pm)
    {
    	this.pm = pm;
    }
    
    public int getColumnCount() {
        return pm.getARPSeqLength()+1;
    }

    public int getRowCount() {
        return 3;
    }

    public String getColumnName(int col) {
    	if (col==0)
    		return "------";
        return "P "+col;
    }

    public Object getValueAt(int row, int col) {
    	if (col==0)
    	{
    		if (row==0)return "Offset";
    		if (row==1)return "SleepDiv";
    		if (row==2)return "GateDiv";
    	}
    	col--;
    	if (row==0)
    		return pm.getARPSeq(col);
    	else
    	if (row==1)
    		return pm.getARPSleepDiv(col);
    	else
    	if (row==2)
    		return pm.getARPGateDiv(col);
    	else
    		System.err.println("Colonne --1 tu dois faire ;)");
    	return "TOTO";
    }

    public Class getColumnClass(int c) {
    	if (c!=0)
    	return Float.class;
    	return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col >= 1) 
            return true;
        return false;
       // } else {
       //     return true;
      //  }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
    	//int [] seq = pm_synth.getNoteSeq();
    	//seq[col]=(Integer)value;
    	if (value==null)
    		return;
    	
    	if (row==0)
    		pm.setARPSeq(col-1,new Float(""+value).intValue());
    	else
    	if (row==1)
    		pm.setARPSleepDiv(col-1, new Float(""+value));
    	else
    	if (row==2)
    	{
    		float f = new Float(""+value);
    		if (f>=1.0f) {f=1;value=(Integer)1;};
    		pm.setARPGateDiv(col-1, new Float(""+value));
    	}
    	else
    		System.err.println("Colonne --1 tu dois faire ;)");
    	//Float method = (Float) value;
    	//if (col==2) o.setValue(method);
       // data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
   
}