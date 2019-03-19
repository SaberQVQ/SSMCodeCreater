package factory.create.dtoCreater;

import factory.create.BaseCreater;
import factory.entity.Entity;
import factory.pathSetting.PathSetting;
import factory.stringCaseUtil.StringCaseUtil;

public class EntityDtoCreater extends BaseCreater{

	public EntityDtoCreater() {
		super.initReader();
		setPathKey("dto");
		setTemplatePath(PathSetting.entityDtoTemplate);
	}
	
	@Override
	public void executeCreateTask(Entity entity) {
		String className = StringCaseUtil.upcaseFirstCharRemoveMark(entity.getEntityName()) + "Dto";
		createFile(className);
		fillData(className, entity);
	}

}
