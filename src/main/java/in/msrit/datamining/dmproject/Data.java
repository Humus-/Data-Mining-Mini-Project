package in.msrit.datamining.dmproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Data{
	
	private List<Class<? extends Attribute>> _dataAttributes;
	private FileReader f;
	private BufferedReader br;
	private long _entryNumber;
	private List<Attribute> _currentTransaction;
	private String inputFile;

	public Data() {
		_dataAttributes = new ArrayList<Class<? extends Attribute>>();
		_currentTransaction = new ArrayList<Attribute>();
		_entryNumber = 0;
	}
	
	private boolean isSet(Object o) {
		if (o == null)
			return false;
		return true;
	}

	/**
	 * Sets the path of the input file.
	 * 
	 * @param path
	 *            : Path of the input file
	 * @throws IOException
	 * @throws NoSuchFieldException
	 *             If the input file has an illegal Attribute Type in the
	 *             metadata.
	 */
	public void setInputPath(String path) throws IOException, NoSuchFieldException {
		f = new FileReader(path);
		br = new BufferedReader(f);
		this.inputFile = path;
		initializeFields();
	}
	
	private void initializeFields() throws IOException, NoSuchFieldException {
		String inputEntry = br.readLine();
		if (inputEntry.equals("#META")) {
			inputEntry = br.readLine();
			String inputEntryArr[] = inputEntry.split(",");
			for(String attr : inputEntryArr) {
				switch(attr) {
				case "Nominal":
					_dataAttributes.add(Nominal.class);
					break;
				case "Discrete":
					_dataAttributes.add(Discrete.class);
					break;
				case "Ordinal":
					_dataAttributes.add(Ordinal.class);
					break;
				case "Continious":
					_dataAttributes.add(Continious.class);
					break;
				case "Binary":
					_dataAttributes.add(Binary.class);
					break;
				default:
					throw new NoSuchFieldException("Illegal Attribute Type");
				}
			}
			br.readLine(); //to exaust the line containing the headings
		}
	}

	/**
	 * Get the specified column value from the next line of the input
	 * 
	 * @param column
	 *            : Get the element by its column number in the transaction
	 *            currently being accessed (1 indexed)
	 * @throws NoSuchFieldException if Input path is not set using setInputPath
	 */
	public Attribute getByColumn(int column) throws NoSuchFieldException {
		if (f == null) {
			throw new NoSuchFieldException("Input path not set");
		}
		return _currentTransaction.get(column-1);
	}
	
	/**
	 * Returns the next transaction as a list of its attributes
	 * @throws NoSuchFieldException 
	 * @throws IOException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Deprecated
	public Iterable<Attribute> getNextEntry() throws NoSuchFieldException, IOException, InstantiationException, IllegalAccessException {
		if (_dataAttributes == null) {
			throw new NoSuchFieldException("Attribute Types not set");
		}
		next();
		return _currentTransaction;
	}
	
	public Iterable<Attribute> getTransaction() throws NoSuchFieldException, IOException, InstantiationException, IllegalAccessException {
		if (_dataAttributes == null) {
			throw new NoSuchFieldException("Attribute Types not set");
		}
		return _currentTransaction;
	}
	
	/**
	 * Reads the next Transaction from the input
	 * Returns true if read successfully , false otherwise
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public boolean next() throws IOException, InstantiationException, IllegalAccessException {
		String input = br.readLine();
		if (input == null) {
			br.close();
			return false;
		}
		//reset current row value
		_currentTransaction = new ArrayList<Attribute>();
		Iterator<Class<? extends Attribute>> attr = _dataAttributes.iterator();
		for (String inputValue : input.split(",")) {
			if(!attr.hasNext()) {
				throw new ArrayIndexOutOfBoundsException("Too many values in input");
			}
			Class<? extends Attribute> fieldClass= attr.next();
			//Constructor<? extends Attribute> attrClassCtor = fieldClass.getConstructor();
			//Attribute field = attrClassCtor.newInstance();
			Attribute field = fieldClass.newInstance();
			field.parseFromString(inputValue);
			_currentTransaction.add(field);
		}
		_entryNumber++;
		return true;
	}
	
	/**
	 * 
	 * @return returns the number of transaction entry currently being accessed
	 */
	public long getCurrentTransactionNumber() {
		return this._entryNumber;
	}
	
	public void setFields(List<Class<? extends Attribute>> fields) {
		this._dataAttributes = fields;
	}
	
	public Iterable<Class<? extends Attribute>> getFields() {
		return _dataAttributes;
	}
	
	//to enable cloning of data for other tasks
	public String getInputFilePath() {
		return this.inputFile;
	}

}
