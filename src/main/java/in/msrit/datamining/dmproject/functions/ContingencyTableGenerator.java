package in.msrit.datamining.dmproject.functions;

import java.util.Iterator;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import in.msrit.datamining.dmproject.Attribute;
import in.msrit.datamining.dmproject.Binary;
import in.msrit.datamining.dmproject.Data;
import in.msrit.datamining.dmproject.Task;

/**
 * Creates a contingency table between 2 binary attributes
 * 
 * @author humus
 *
 */

public class ContingencyTableGenerator implements Task {

	/**
	 * Stores the Contingency table between 2 variables x and y
	 * 
	 * @author humus
	 *
	 */
	public class ContingencyTable {
		private long xAndY;
		private long xNotY;
		private long NotxY;
		private long NotxNotY;
		
		void incrementXY(long n) {
			this.xAndY += n;
		}
		void incrementXbarY(long n) {
			this.NotxY += n;
		}
		void incrementXYbar(long n) {
			this.xNotY += n;
		}
		void incrementXbarYbar(long n) {
			this.NotxNotY += n;
		}
		//bx and by are true if x and y belong to that transaction resp
		void setByBool(boolean bx, boolean by) {
			if (bx && !by)
				incrementXYbar(1);
			else if (bx && by)
				incrementXY(1);
			else if (!bx && by)
				incrementXbarY(1);
			else 
				incrementXbarYbar(1);
		}
		
		public long NumofX() {
			return xNotY + xAndY;
		}
		
		public long NumofY() {
			return xAndY + NotxY;
		}
		
		public long totalCount() {
			return NumofX() + NumofY();
		}
		
		@Override
		public String toString() {
			return xAndY+" "+xNotY+"\n"+NotxY+" "+NotxNotY;
		}
		
	}
	
	int fields[];
	ContingencyTable ct = new ContingencyTable();
	
	@Override
	public void doTask(Data input) throws Exception {
		if (fields == null) {
			throw new NullPointerException("Please initialize fields");
		}
		if (this.fields.length > 2) {
			throw new ArrayStoreException("Expecting only 2 fields to calculate Covariance");
		}
		// checking if fields are binomial
		Class<? extends Attribute> a = Iterables.get((Iterable<Class<? extends Attribute>>) input.getFields(),
				fields[0]-1);
		Class<? extends Attribute> b = Iterables.get((Iterable<Class<? extends Attribute>>) input.getFields(),
				fields[1]-1);
		
		if (!(a.getName() == Binary.class.getName())) {
			throw new IllegalAccessException("Invalid Attribute Type.Need Binomial");
		}
		if (!(b.getName() == Binary.class.getName())) {
			throw new IllegalAccessException("Invalid Attribute Type.Need Binomial");
		}
		
		while(input.next()) {
			boolean x = (Boolean)input.getByColumn(fields[0]).getValue();
			boolean y = (Boolean)input.getByColumn(fields[1]).getValue();
			
			ct.setByBool(x, y);
		}
		
	}

	@Override
	public void setFields(int[] fields) {
		this.fields = fields;
	}
	
	public ContingencyTable getContingencyTable() {
		return ct;
	}
	
	@Override
	public void setField(int field) {
		int fields[]= {field};
		this.fields = fields;
	}
	
}