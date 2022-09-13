package com.yorbit.moviebooking.service;

import java.util.List;

import com.yorbit.moviebooking.model.Screen;

public interface ScreenService {
	
	List<Screen> getAllScreens();

    Screen getScreenById(Integer id);

    Screen saveScreen(Screen newScreen);

    Screen updateScreen(Screen updatedScreen, Integer id);

    void deleteScreenById(Integer id);
}
