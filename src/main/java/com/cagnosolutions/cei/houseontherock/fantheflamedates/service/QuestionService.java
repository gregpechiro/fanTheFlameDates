package com.cagnosolutions.cei.houseontherock.fantheflamedates.service;

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Question;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by greg on 7/3/14.
 */

@Transactional
@Service("questionService")
public class QuestionService {

	@Autowired
	private QuestionRepository dao;

	public Question insert(Question question) {
		return dao.saveAndFlush(question);
	}

	public void update(Question question) {
		dao.save(question);
	}

	public void delete(Question question) {
		dao.delete(question);
	}

	public List<Question> findAll() {
		return dao.findAll();
	}

	public Question findById(Long id) {
		return dao.findOne(id);
	}

	public boolean exists(Long id) {
		return dao.exists(id);
	}

	public List<Question> findAllSorted(String sort, String order) {
		if ((isEmpty(sort) && isEmpty(order)) || isEmpty(sort))
			return dao.findAll();
		if (isEmpty(order) || !order.toLowerCase().startsWith("asc") || !order.toLowerCase().startsWith("desc"))
			return dao.findAll(new Sort(Sort.Direction.fromString("ASC"), sort));
		return dao.findAll(new Sort(Sort.Direction.fromString(order), sort));
	}

	private static boolean isEmpty(String string) {
		return (string == null || string.equals(""));
	}

	public List<Question> findByVideoId(String videoId) {
		return dao.findByVideoId(videoId);
	}

}
