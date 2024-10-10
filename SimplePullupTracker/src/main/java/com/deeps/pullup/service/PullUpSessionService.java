package com.deeps.pullup.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.deeps.account.dao.UserDAO;
import com.deeps.account.entity.UserEntity;
import com.deeps.account.service.UserService;
import com.deeps.common.util.DateUtils;
import com.deeps.pullup.dao.PullUpSessionDAO;
import com.deeps.pullup.entity.PullUpSessionEntity;
import com.deeps.pullup.view.PullUpSessionView;

@Service
public class PullUpSessionService {

	@Autowired
	PullUpSessionDAO pullUpSessDao;

	@Autowired
	UserService userService;

	@Autowired
	UserDAO userDao;

	public PullUpSessionEntity save(PullUpSessionView view) {
		PullUpSessionEntity entity = new PullUpSessionEntity();
		convertViewToEntity(view, entity);
		pullUpSessDao.save(entity);
		return null;
	}

	private void convertViewToEntity(PullUpSessionView view, PullUpSessionEntity entity) {
		entity.setPullUpCount(view.getPullUpCount());
		entity.setSessionDateTime(DateUtils.convertThymeleafStringToLocalDateTime(view.getSessionDateTime()));
		addUserToEntity(entity);
	}

	private void addUserToEntity(PullUpSessionEntity entity) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		entity.setUser(userDao.findByUserName(authentication.getName()));
	}

	public List<PullUpSessionView> allSession() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserEntity user = userDao.findByUserName(authentication.getName());
		return convertListEntityToView(pullUpSessDao.findByUserId(user.getId()));

	}

	private List<PullUpSessionView> convertListEntityToView(List<PullUpSessionEntity> entity) {
		List<PullUpSessionView> view = new ArrayList<PullUpSessionView>();
		int i = 1;
		for (PullUpSessionEntity temp : entity) {
			PullUpSessionView single = new PullUpSessionView();
			single.setIndex(i);
			single.setIdentity(temp.getId());
			single.setPullUpCount(temp.getPullUpCount());
			String time = DateUtils.convertLocalDateTimeToString(temp.getSessionDateTime());
			if (time != null) {
				single.setSessionDateTime(time);
			}
			i++;
			view.add(single);
		}
		return view;
	}

	public void deleteSessionByIdentity(Long identity) {
		if (identity != null) {
			pullUpSessDao.deleteById(identity);
		}
	}

	public PullUpSessionEntity updateSessionView(Long id, PullUpSessionView sessionView) {
		PullUpSessionEntity entity = pullUpSessDao.getById(id);
		entity.setPullUpCount(sessionView.getPullUpCount());
		return pullUpSessDao.save(entity);
	}
	
	public PullUpSessionEntity updateSessionEntity(Long id, PullUpSessionEntity entity) {
		PullUpSessionEntity original = pullUpSessDao.getById(id);
		original.setPullUpCount(entity.getPullUpCount());
		return pullUpSessDao.save(original);
	}


	public PullUpSessionView getSessionViewById(Long id) {
		PullUpSessionEntity entity = pullUpSessDao.getById(id);
		return convertEntitytoView(entity);

	}

	public PullUpSessionEntity getSessionEntityById(Long id) {
		return pullUpSessDao.getById(id);
	}

	public PullUpSessionView convertEntitytoView(PullUpSessionEntity entity) {
		if (entity != null) {
			PullUpSessionView view = new PullUpSessionView();
			view.setIdentity(entity.getId());
			view.setPullUpCount(entity.getPullUpCount());
			String time = DateUtils.convertLocalDateTimeToString(entity.getSessionDateTime());
			if (time != null) {
				view.setSessionDateTime(time);
			}
			return view;
		}
		return null;
	}

}
