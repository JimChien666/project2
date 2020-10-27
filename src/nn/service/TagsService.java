package nn.service;

import java.util.List;

import org.hibernate.Session;

import team6.nn.dao.CitysDAO;
import team6.nn.dao.TagsDAO;
import team6.nn.entity.Citys;
import team6.nn.entity.Tags;

public class TagsService implements ITagsService {
	
	private TagsDAO tagsDao;

	public TagsService(Session session) {
		tagsDao = new TagsDAO(session);
	}
	@Override
	public Tags insert(Tags bean) {		
		return tagsDao.insert(bean);
	}

	@Override
	public Tags select(int id) {
		return tagsDao.select(id);
	}

	@Override
	public List<Tags> selectAll() {
		return tagsDao.selectAll();
	}

	@Override
	public Tags update(int id, String name) {
		return tagsDao.update(id, name);
	}

	@Override
	public boolean delete(int id) {
		return tagsDao.delete(id);
	}

}
