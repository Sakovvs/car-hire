package frames;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {
	public Table(ResultSet rs) {
		try {
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel dtm=new DefaultTableModel();
			
			for(int i=1; i<=rsmd.getColumnCount();i++) {
				dtm.addColumn(rsmd.getColumnName(i));
			}
			while (rs.next()==true) {
				//Object array[]=new Object[rsmd.getColumnCount()];
				Vector <Object> v= new Vector<Object>();
				for(int i=1; i<=rsmd.getColumnCount();i++) {
				//	array[i-1]=rs.getString(i);
					v.add(rs.getString(i));
				}
				//dtm.addRow(array);
				dtm.addRow(v);
			}
			
			setModel(dtm);
			setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
