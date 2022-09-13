package com.yorbit.moviebooking.service.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yorbit.moviebooking.exception.DbException;
import com.yorbit.moviebooking.exception.ScreenNotFoundException;
import com.yorbit.moviebooking.model.Screen;
import com.yorbit.moviebooking.repository.ScreenRepository;
import com.yorbit.moviebooking.service.ScreenService;

@Service
public class ScreenServiceImpl implements ScreenService{

	@Autowired
	private ScreenRepository screenRepository;
	
	private static final Logger LOGGER = Logger.getLogger(ScreenServiceImpl.class);
	
	@Value("${globalexception.message}")
	String globalErrorMsg;
	
	@Value("${screennotfound.message}")
	String errorMsg;
	
	@Override
	@Transactional(readOnly = true)
	public List<Screen> getAllScreens() {
		return screenRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Screen getScreenById(Integer id) {
		return screenRepository.getReferenceById(id);
	}

	@Override
	@Transactional
	public Screen saveScreen(Screen newScreen) {
		Integer result = screenRepository.saveScreen(newScreen.getId(), newScreen.getType());
		if(result == 1) {
			return newScreen;
		}
		else {
			throw new DbException(globalErrorMsg);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Screen updateScreen(Screen updatedScreen, Integer id) {
		if(screenRepository.existsById(id)) {
			return screenRepository.save(updatedScreen);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new ScreenNotFoundException(errorMsg + id);
		}
	}

	@Override
	@Transactional
	public void deleteScreenById(Integer id) {
		if(screenRepository.existsById(id)) {
			screenRepository.deleteById(id);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new ScreenNotFoundException(errorMsg + id);
		}
	}

}
