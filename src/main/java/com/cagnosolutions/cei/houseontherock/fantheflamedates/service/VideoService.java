package com.cagnosolutions.cei.houseontherock.fantheflamedates.service;/**
 * Created by greg on 7/3/14.
 */

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Video;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("videoService")
public class VideoService {

	@Autowired
	private VideoRepository dao;

	public Video insert(Video video) {
		return dao.saveAndFlush(video);
	}

	public void update(Video video) {
		dao.save(video);
	}

	public void delete(Video video) {
		dao.delete(video);
	}

	public List<Video> findAll() {
		return dao.findAll();
	}

	public Video findById(Long id) {
		return dao.findOne(id);
	}

	public boolean exists(Long id) {
		return dao.exists(id);
	}

	public List<Video> findAllSorted(String sort, String order) {
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
