package com.gourmet.database;

import java.util.List;

import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.Schema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.github.dynamicschema.android.sql.SQLStatementsLoader;
import com.gourmet.database.gen.GourmetSchema;


public class GourmetOpenHelper extends SQLiteOpenHelper { 

	private static final String DATABASE_NAME = "gourmetVersion16.sqlite";  
	private static final int DATABASE_VERSION = 1;

	private Schema dbSchema;

	private static GourmetOpenHelper helperInstance = null;

	public static GourmetOpenHelper getInstance(Context context){
		if(helperInstance == null)
			helperInstance = new GourmetOpenHelper(context); 
		return helperInstance;
	}



	//Customized Constructor
	public GourmetOpenHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		dbSchema = initSchema();
	}

	public GourmetOpenHelper(Context context, String name, 	CursorFactory factory, int version) {
		super(context, name, factory, version);

	}

	/**
	 * @return the dbSchema
	 */
	public Schema getReifiedSchema() {
		return dbSchema;
	}

	//	public GourmetOpenHelper(Context context, String name,CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
	//		super(context, name, factory, version, errorHandler);
	//		
	//	}



	@Override
	public void onCreate(SQLiteDatabase db) {
		createDB(db);
		populateDB(db);

	}



	@Override
	public void onUpgrade(SQLiteDatabase db, int old, int newV) {
		Log.w ("GourmetOpenHelper.upgradeDB", "Upgrading database. Old version: " + old + "New version: "+ newV);
		onCreate(db);
	}


	//TODO remove TEST
	public void deleteDatabase(SQLiteDatabase db){

		List<DBTable> tables = this.dbSchema.getTables();
		for (DBTable dbTable : tables) {
			db.execSQL(dbTable.dropTableStatement());
		}

	}



	private void createDB(SQLiteDatabase db){
		List<DBTable> tables = this.dbSchema.getTables();
		for (DBTable dbTable : tables) {
			String statement = dbTable.createTableStatement();
			db.execSQL(statement);

		}

	}

	private void populateDB(SQLiteDatabase db){
		SQLStatementsLoader loader = new SQLStatementsLoader();
		String [] insertInstr = loader.getInsertInstructions();
		for (String instr : insertInstr) {
			db.execSQL(instr);

		}
	}


	/*
	 * Initialize the reified schema to be used in the queries
	 */
	private Schema initSchema() {		
		Schema sch= new GourmetSchema();
		return sch;
	}






}
