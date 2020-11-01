package tw.wey.zOld;

import org.hibernate.Session;

import nn.entity.Files;
import wey.entity.Animals;

public class FilesDao {
	private Session hSession;
	public FilesDao(Session hSession) {
		this.hSession = hSession;
	}
	
	public Files create(Files files) {
		Files result = hSession.get(Files.class, files.getId());
		if (result == null) {
			hSession.save(files);
			return files;
		}
		return null;
	}
}
