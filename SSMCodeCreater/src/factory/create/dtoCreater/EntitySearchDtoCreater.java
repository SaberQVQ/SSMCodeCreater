package factory.create.dtoCreater;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;

public class EntitySearchDtoCreater extends BaseCreater{

	public EntitySearchDtoCreater() {
		super.initReader();
		setPathKey("dto");
		setTemplatePath(PathSetting.entitySearchDtoTemplate);
	}
	
	@Override
	public void executeCreateTask(Entity entity) {
		String className = StringCaseUtil.upcaseFirstCharRemoveMark(entity.getEntityName()) + "SearchDto";
		createFile(className);
		fillData(className, entity);
	}
	
}
