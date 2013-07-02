package com.gourmet.database;

import java.util.ArrayList;
import java.util.List;

import org.dynamicschema.reification.DBTable;
import org.dynamicschema.reification.RelationModel;
import org.dynamicschema.reification.Schema;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.dynamicschema.android.reification.gen.BelongingTable;
import com.github.dynamicschema.android.reification.gen.ClientTable;
import com.github.dynamicschema.android.reification.gen.ContenanceTable;
import com.github.dynamicschema.android.reification.gen.Description2Table;
import com.github.dynamicschema.android.reification.gen.DescriptionTable;
import com.github.dynamicschema.android.reification.gen.GourmetRelationModel;
import com.github.dynamicschema.android.reification.gen.IngredientTable;
import com.github.dynamicschema.android.reification.gen.LanguageTable;
import com.github.dynamicschema.android.reification.gen.MealTable;
import com.github.dynamicschema.android.reification.gen.Meal_RatingTable;
import com.github.dynamicschema.android.reification.gen.PreferenceTable;
import com.github.dynamicschema.android.reification.gen.ProhibitionTable;
import com.github.dynamicschema.android.reification.gen.RegionTable;
import com.github.dynamicschema.android.reification.gen.RequirementTable;
import com.github.dynamicschema.android.reification.gen.RestaurantTable;
import com.github.dynamicschema.android.reification.gen.SeasonTable;
import com.github.dynamicschema.android.reification.gen.TasteTable;
import com.github.dynamicschema.android.reification.gen.Taste_BelongingTable;
import com.github.dynamicschema.android.reification.gen.Taste_PreferenceTable;
import com.github.dynamicschema.android.reification.gen.User_ConstraintTable;
import com.github.dynamicschema.android.reification.gen.VisitTable;
import com.github.dynamicschema.android.sql.EmptyFilteringCondition;
import com.github.dynamicschema.android.sql.SQLStatementsLoader;

public class GourmetOpenHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "gourmetBigRecursiveTEMP.sqlite";
	private static final int DATABASE_VERSION = 1;
	
	private Schema dbSchema;
	
	//Customized Constructor
	public GourmetOpenHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		dbSchema = initSchema();
	}
	
	public GourmetOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}

	/**
	 * @return the dbSchema
	 */
	public Schema getReifiedSchema() {
		return dbSchema;
	}

	public GourmetOpenHelper(Context context, String name,CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		
	}
	
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		createDB(db);
		populateDB(db);

	}
	
	

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Attempt to Upgrade database. Not Implemented");
	}
	
	
	private void createDB(SQLiteDatabase db){
		List<DBTable> tables = this.dbSchema.getTables();
		for (DBTable dbTable : tables) {
			db.execSQL(dbTable.createTableStatement());
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
		List<DBTable> tables = initTables();
		for (DBTable dbTable : tables) {
			dbTable.setFiltering(new EmptyFilteringCondition());
		}
		RelationModel relModel = initRelationalModel(tables);
		Schema sch = new Schema();
		sch.setTables(tables);
		sch.setRelationModel(relModel);
		return sch;
	}
	
	/*
	 * This methods is adapted according to the generated set of tables
	 */
	private List<DBTable> initTables(){
		
		DBTable rest, meal, ingr, belonging, client, constraint, contenance,
		descrip, description2, lang, mealRating, preference, prohib,
		req, season,tasteBelong, tastePref, taste, visit, region;
		
		List<DBTable> tables = new ArrayList<DBTable>();
		
		rest = new RestaurantTable();
		tables.add(rest);
		
		meal = new MealTable();
		tables.add(meal);
		ingr = new IngredientTable();
		tables.add(ingr);
		belonging = new BelongingTable();
		tables.add(belonging);
		client =  new ClientTable();
		tables.add(client);
		constraint = new User_ConstraintTable();
		tables.add(constraint);
		contenance = new ContenanceTable();
		tables.add(contenance);
		description2 = new Description2Table();
		tables.add(description2);
		descrip = new DescriptionTable();
		tables.add(descrip);
		lang = new LanguageTable();
		tables.add(lang);
		mealRating = new Meal_RatingTable();
		tables.add(mealRating);
		preference  = new PreferenceTable();
		tables.add(preference);
		prohib = new ProhibitionTable();
		tables.add(prohib);
		req = new RequirementTable();
		tables.add(req);
		season  = new SeasonTable();
		tables.add(season);
		tasteBelong = new Taste_BelongingTable();
		tables.add(tasteBelong);
		tastePref = new Taste_PreferenceTable();
		tables.add(tastePref);
		taste  =new TasteTable();
		tables.add(taste);
		visit = new VisitTable();
		tables.add(visit);

		region = new RegionTable();
		tables.add(region);

		return tables;

	}

	
	/*
	 * Also to be adapted according to the generated classes
	 */
	private RelationModel initRelationalModel(List<DBTable> tables){
		GourmetRelationModel model = new GourmetRelationModel();
		model.updateTables(tables);
		return model;
	}

	
	
}
