package factory.create.mapperXMLCreater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.entity.Field;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;

/**
 * 实体对象mapperXML
 */
public class MapperXMLCreater extends BaseCreater{

	public MapperXMLCreater() {
		super.initReader();
		setPathKey("mapper");
		setTemplatePath(PathSetting.mapperXMLTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		String className = StringCaseUtil.upcaseFirstCharRemoveMark(entity.getEntityName());
		createFile(className);
		fillData(className, entity);
	}
	
	@Override
    protected void fillData(String className, Entity entity){
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("author", entity.getAuthor());
		// 首字母大写的类名
		data.put("className", StringCaseUtil.lowcaseFirstCharRemoveMark(entity.getEntityName()));
		// 首字母小写的类名
		data.put("classNameL", StringCaseUtil.upcaseFirstChar(StringCaseUtil.lowcaseFirstCharRemoveMark(entity.getEntityName())));
		// 未转换的表名
		data.put("tableName", entity.getEntityName());
		
		data.put("daoPackage", getPackagePathWithPathKey("dao").replaceAll("/", "."));
		data.put("entityPackage", getPackagePathWithPathKey("entity").replaceAll("/", "."));
		
		data.put("dakuohao", "{");
		data.put("dollor", "$");
		data.put("jinghao", "#");
		
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//		List<Map<String,String>> idList = new ArrayList<Map<String,String>>();
		List<Field> fieldList = entity.getFields();
		for (int i = 0; i < fieldList.size(); i++) {
			Map<String,String> fieldMap = new HashMap<String,String>();
			if (null == fieldList.get(i) || "" == fieldList.get(i).getFieldName()) {
				continue;
			}
			fieldMap.put("fieldType", StringCaseUtil.upcaseAll(fieldList.get(i).getFieldType()));
			fieldMap.put("fieldName", fieldList.get(i).getFieldName());
			fieldMap.put("fieldNameProperty", StringCaseUtil.lowcaseFirstCharRemoveMark(fieldList.get(i).getFieldName()));
			
			if (fieldList.get(i).isPrimaryKey()){
				data.put("fieldId", StringCaseUtil.upcaseAll(fieldList.get(i).getFieldName()));
				data.put("fieldIdProperty", StringCaseUtil.lowcaseFirstCharRemoveMark(fieldList.get(i).getFieldName()));
				data.put("fieldIdType", StringCaseUtil.upcaseAll(fieldList.get(i).getFieldType()));
			}

			list.add(fieldMap);
		}
		data.put("fieldList", list);
		
		loadTemplateAndWriteFile(className, "Mapper.xml", data);
	}

}
