package com.cagnosolutions.cei.houseontherock.fantheflamedates.service;/**
 * Created by greg on 7/29/14.
 */

import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.User;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.domain.Worksheet;
import com.cagnosolutions.cei.houseontherock.fantheflamedates.repository.WorksheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service("worksheetService")
public class WorksheetService {

	@Autowired
	private WorksheetRepository dao;

	@Autowired
	private MailService mailService;

	@Autowired
	private UserService userService;

	public void add(Worksheet worksheet) {
		dao.save(worksheet);
		if (worksheet.isEmail()) {
			User user = userService.findById(worksheet.getUserId());
			mailService.sendSimpleEmail("noreply@noreplyfantheflamedates.com", "Worksheet", worksheet.getAnswers(), user.getEmail());
		}
		if (worksheet.isSend()) {
			mailService.sendSimpleEmail("noreply@noreplyfantheflamedates.com", "Worksheet", worksheet.getAnswers(), "");
		}
	}

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

}
