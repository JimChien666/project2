package com.iii.eeit124.article.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.article.dao.MemberOptionDAO;
import com.iii.eeit124.entity.MemberOption;
import com.iii.eeit124.entity.Options;

@Transactional
@Service
public class MemberOptionServiceImpl implements MemberOptionService{

	@Autowired(required = false)
	MemberOptionDAO memberOptionDAO;

	@Override
	public void save(MemberOption entity) {
		memberOptionDAO.save(entity);
	}

	@Override
	public Options findOptionById(Integer optionid) {
		return memberOptionDAO.findOptionById(optionid);
	}

	@Override
	public boolean CheckMemberVoteStatus(Integer mid, int fid) {
		return memberOptionDAO.CheckMemberVoteStatus(mid, fid);
	}

	@Override
	public List<MemberOption> getVoteResult(Integer fid) {
		return memberOptionDAO.getVoteResult(fid);
	}

	@Override
	public Map<String, Integer> getVoteCount(Integer fid) {
		return memberOptionDAO.getVoteCount(fid);
	}
	
	
}
