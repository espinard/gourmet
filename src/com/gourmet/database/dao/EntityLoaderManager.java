package com.gourmet.database.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dynamicschema.context.RelationalContextManager;
import org.dynamicschema.reification.ContextedTable;
import org.dynamicschema.reification.Table;

import android.database.Cursor;

import com.gourmet.database.IDatabaseEntityAdaptor;
import com.gourmet.database.gen.Add_DescriptionTable;
import com.gourmet.database.gen.AddressTable;
import com.gourmet.database.gen.ChefTable;
import com.gourmet.database.gen.ClientTable;
import com.gourmet.database.gen.Description2Table;
import com.gourmet.database.gen.DescriptionTable;
import com.gourmet.database.gen.IngredientTable;
import com.gourmet.database.gen.LanguageTable;
import com.gourmet.database.gen.MealTable;
import com.gourmet.database.gen.RegionTable;
import com.gourmet.database.gen.RestaurantTable;
import com.gourmet.database.gen.RestoDescriptionTable;
import com.gourmet.model.Address;
import com.gourmet.model.AddressDescription;
import com.gourmet.model.Chef;
import com.gourmet.model.Client;
import com.gourmet.model.Ingredient;
import com.gourmet.model.IngredientDescription;
import com.gourmet.model.Language;
import com.gourmet.model.Meal;
import com.gourmet.model.MealDescription;
import com.gourmet.model.Region;
import com.gourmet.model.Restaurant;
import com.gourmet.model.RestoDescription;
import com.gourmet.model.interfaces.IEntityObject;

public class EntityLoaderManager implements IDatabaseEntityAdaptor {

	
	//store instances of entity objects already loaded from cursors; 
		//Entity objects are the objects storing the values of a table row. There will be as many instances of entity objects as there are rows in a result set. 
	private Map<ContextedTable,IEntityObject> loadedEntityObjects; 
	
	public EntityLoaderManager() {
		loadedEntityObjects = new HashMap<ContextedTable, IEntityObject>();
	}
	
	
	
	/*
	 * 
	 */
	public IEntityObject loadEntityObjectFromCursor(Cursor cursor, RelationalContextManager ctx){
		
		reinitLoadedObjectsMap();
		
		Table rootTable = ctx.getBaseTable();
		ContextedTable rootCtxTable =(ContextedTable) ctx.getFirstContextedTable(rootTable);
		return loadEntityObjectFromCursor(rootCtxTable, cursor, ctx, ctx.getTablesDependencies());
	}
	
	/*
	 * Recursively load entities from cursor 
	 */
	private IEntityObject loadEntityObjectFromCursor(ContextedTable table, Cursor cursor, RelationalContextManager ctx, Map<ContextedTable,ContextedTable> dependencies){
	
		IEntityObject mainObj = getEntityObjectInstanceForContextedTable(table, ctx, cursor);
		List<ContextedTable> depend = getAllDependencies(table, dependencies);
		for (ContextedTable ctxTable : depend) {
			IEntityObject relatedObj = loadEntityObjectFromCursor(ctxTable, cursor, ctx, dependencies);
			mainObj.setAssociatedObject(relatedObj);
		}
		 
		return  mainObj;
		
	}
	
	private void reinitLoadedObjectsMap(){
				Set<ContextedTable> previousKeys = loadedEntityObjects.keySet();
				for (ContextedTable prevCtxTable : previousKeys) {
					loadedEntityObjects.remove(prevCtxTable);
				}	
	}
	
	private IEntityObject alreadyLoadedObject(ContextedTable ctxTable){
		return loadedEntityObjects.get(ctxTable);
	}
	
	/*
	 * Get the Application model object instance
	 */
	private IEntityObject getEntityObjectInstanceForContextedTable(ContextedTable table, RelationalContextManager ctx, Cursor cursor){
		

		IEntityObject objInstance = alreadyLoadedObject(table);
		boolean alreadyLoaded = objInstance != null;
		
		if(!alreadyLoaded){
			objInstance = getInstanceForEntityType(table);
			
			int colWidth = table.getColumnValues().size();
			for (int i = table.getOffset(); i < colWidth + table.getOffset(); i++) {
				String colName = cursor.getColumnName(i);
				objInstance.setAttribute(colName, cursor.getString(i));
			}
		}
	
		return objInstance;	
	}


	private List<ContextedTable> getAllDependencies(ContextedTable table, Map<ContextedTable,ContextedTable> dependencies) {
		
		List<ContextedTable> listDepend = new ArrayList<ContextedTable>();
		Set<ContextedTable> keySet = dependencies.keySet();
		for (ContextedTable ctxTable : keySet) {
			if(table.getName().equals(ctxTable.getName())){
				listDepend.add(dependencies.get(ctxTable));
			}
		}
		
		return  listDepend;
	}
	
	@Override
	public IEntityObject getInstanceForEntityType(Table table) {	
		return getObjectOfEntityType(table.getName());
	}
	
private IEntityObject getObjectOfEntityType(String entityType){
		
		if(entityType.startsWith(RestaurantTable.NAME))
			return new Restaurant();
		if(entityType.startsWith(MealTable.NAME))
			return new Meal();
		if(entityType.startsWith(ClientTable.NAME))
			return  new Client();	
		if(entityType.startsWith(IngredientTable.NAME))
			return new Ingredient();
		if(entityType.startsWith(LanguageTable.NAME))
			return new Language();
		if(entityType.startsWith(ChefTable.NAME))
			return new Chef();	
		if(entityType.startsWith(Description2Table.NAME))
			return new IngredientDescription();
		
		if(entityType.startsWith(DescriptionTable.NAME))
			return new MealDescription();
		
		if(entityType.startsWith(RestoDescriptionTable.NAME))
			return new RestoDescription();
		
		if(entityType.startsWith(RegionTable.NAME))
			return new Region();
		
		if(entityType.startsWith(AddressTable.NAME))
			return new Address();
		
		if(entityType.startsWith(Add_DescriptionTable.NAME))
			return new AddressDescription();
		
		return null;
	}
	
}
