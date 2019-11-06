package factory.create.entityCreater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.entity.Field;
import factory.entity.FieldType;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;

/**
 * Entity类 生成
 * 
 */
public class EntityCreater extends BaseCreater {

	public EntityCreater() {
		super.initReader();
		setPathKey("entity");
		setTemplatePath(PathSetting.entityTemplate);
	}

	@Override
	public void executeCreateTask(Entity entity) {
		String className = StringCaseUtil.upcaseFirstCharRemoveMark(entity.getEntityName()) + "Entity";
		createFile(className);
		fillData(className, entity);
	}

	@Override
	protected void fillData(String className, Entity entity) {

		List<Field> fieldList = entity.getFields();
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("author", entity.getAuthor());
		// 首字母小写的类名 驼峰式
		data.put("entityNameL", StringCaseUtil.lowcaseFirstCharRemoveMark(entity.getEntityName()));
		// 首字母大写的类名 驼峰式
		data.put("entityNameU", StringCaseUtil.upcaseFirstCharRemoveMark(entity.getEntityName()));
		
		//去掉 package 最后的.
		String packageStr = getPackagePathWithPathKey().replaceAll("/", ".");
		packageStr = packageStr.endsWith(".") ? packageStr.substring(0, packageStr.length() - 1) : packageStr;
		data.put("package", packageStr);

		// 属性列表
		List<Object> attrList = new ArrayList<Object>();
		for (int i = 0; i < fieldList.size(); i++) {
			Field field = fieldList.get(i);
			if (null == field || "" == field.getFieldName()) {
				continue;
			}
			Map<String, String> fieldMap = new HashMap<String, String>();
			String type = FieldType.transferFieldType(field.getFieldType());
			fieldMap.put("fieldType", type);
			fieldMap.put("fieldName", StringCaseUtil.lowcaseFirstCharRemoveMark(field.getFieldName()));
			attrList.add(fieldMap);
		}
		data.put("attributes", attrList);
		loadTemplateAndWriteFile(className, ".java", data);
	}
}
