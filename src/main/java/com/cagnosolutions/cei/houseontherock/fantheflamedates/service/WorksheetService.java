package com.cagnosolutions.cei.houseontherock.fantheflamedates.service;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Worksheet;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.repository.WorksheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by greg on 7/3/14.
 */

@Transactional
@Service("worksheetService")
public class WorksheetService {

	@Autowired
	private WorksheetRepository dao;

	public Worksheet insert(Worksheet worksheet) {
		return dao.saveAndFlush(worksheet);
	}

	public void update(Worksheet worksheet) {
		dao.save(worksheet);
	}

	public void delete(Worksheet worksheet) {
		dao.delete(worksheet);
	}

	public List<Worksheet> findAll() {
		return dao.findAll();
	}

	public Worksheet findById(Long id) {
		return dao.findOne(id);
	}

	public boolean exists(Long id) {
		return dao.exists(id);
	}

	public List<Worksheet> findAllSorted(String sort, String order) {
		if ((isEmpty(sort) && isEmpty(order)) || isEmpty(sort))
			return dao.findAll();
		if (isEmpty(order) || !order.toLowerCase().startsWith("asc") || !order.toLowerCase().startsWith("desc"))
			return dao.findAll(new Sort(Sort.Direction.fromString("ASC"), sort));
		return dao.findAll(new Sort(Sort.Direction.fromString(order), sort));
	}

	private static boolean isEmpty(String string) {
		return (string == null || string.equals(""));
	}

	public List<Worksheet> findByVideoId(Long videoId) {
		return dao.findByVideoId(videoId);
	}

}
