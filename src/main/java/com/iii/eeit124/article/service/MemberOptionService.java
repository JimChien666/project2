package com.iii.eeit124.article.service;

import java.util.List;
import java.util.Map;

import com.iii.eeit124.entity.MemberOption;
import com.iii.eeit124.entity.Options;

public interface MemberOptionService {
	void save(MemberOption entity);

	Options findOptionById(Integer optionid);

	boolean CheckMemberVoteStatus(Integer mid, int fid);

	List<MemberOption> getVoteResult(Integer fid);
	
	Map<String, Integer> getVoteCount(Integer fid);

}
