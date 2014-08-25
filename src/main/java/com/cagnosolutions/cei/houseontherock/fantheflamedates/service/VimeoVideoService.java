package com.cagnosolutions.cei.houseontherock.fantheflamedates.service;

/**
 * Created by greg on 8/25/14.
 */

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.VimeoVideo;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.repository.VimeoVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("vimeovideoService")
public class VimeoVideoService {

	@Autowired
	private VimeoVideoRepository dao;

	public VimeoVideo insert(VimeoVideo vimeovideo) {
		return dao.saveAndFlush(vimeovideo);
	}

	public void update(VimeoVideo vimeovideo) {
		dao.save(vimeovideo);
	}

	public void delete(VimeoVideo vimeovideo) {
		dao.delete(vimeovideo);
	}

	public List<VimeoVideo> findAll() {
		return dao.findAll();
	}

	public VimeoVideo findById(String id) {
		return dao.findOne(id);
	}

	public boolean exists(String id) {
		return dao.exists(id);
	}

	public List<VimeoVideo> findAllSorted(String sort, String order) {
		if ((isEmpty(sort) && isEmpty(order)) || isEmpty(sort))
			return dao.findAll();
		if (isEmpty(order) || !order.toLowerCase().startsWith("asc") || !order.toLowerCase().startsWith("desc"))
			return dao.findAll(new Sort(Sort.Direction.fromString("ASC"), sort));
		return dao.findAll(new Sort(Sort.Direction.fromString(order), sort));
	}

	private static boolean isEmpty(String string) {
		return (string == null || string.equals(""));
	}

}
