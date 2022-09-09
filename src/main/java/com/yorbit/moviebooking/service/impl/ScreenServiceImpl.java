package com.yorbit.moviebooking.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.yorbit.moviebooking.exception.ScreenNotFoundException;
import com.yorbit.moviebooking.model.Screen;
import com.yorbit.moviebooking.repository.ScreenRepository;
import com.yorbit.moviebooking.service.ScreenService;

@Service
@Transactional
@PropertySource("classpath:message.properties")
public class ScreenServiceImpl implements ScreenService{

	@Autowired
	private ScreenRepository screenRepository;
	
	private static final Logger LOGGER = Logger.getLogger(BookingServiceImpl.class);
	
	@Value("${screennotfound.message}")
	String errorMsg;
	
	@Override
	public List<Screen> getAllScreens() {
		return screenRepository.findAll();
	}

	@Override
	public Screen getScreenById(Long id) {
		return screenRepository.getReferenceById(id);
	}

	@Override
	public Screen saveScreen(Screen newScreen) {
		return screenRepository.save(newScreen);
	}

	@Override
	public Screen updateScreen(Screen updatedScreen, Long id) {
		if(screenRepository.existsById(id)) {
			return screenRepository.save(updatedScreen);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new ScreenNotFoundException(errorMsg + id);
		}
	}

	@Override
	public void deleteScreenById(Long id) {
		if(screenRepository.existsById(id)) {
			screenRepository.deleteById(id);
		}
		else {
			LOGGER.error(errorMsg + id);
			throw new ScreenNotFoundException(errorMsg + id);
		}
	}

}