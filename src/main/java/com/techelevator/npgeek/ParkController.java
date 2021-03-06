package com.techelevator.npgeek;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ParkController {
	
	@Autowired
	private ParkDao parkDao;
	
	@RequestMapping(path= "/", method=RequestMethod.GET)
	public String HomePage(ModelMap modelHolder) {
		modelHolder.put("parks", parkDao.getAllParks());
		return "home";
	}
	
	
	
	@RequestMapping(path="/details", method=RequestMethod.GET)
	public String DetailPage(String parkCode, ModelMap modelHolder) {
		Park parks = parkDao.getSelectedPark(parkCode);
		modelHolder.put("park", parks);
		modelHolder.addAttribute("weather", parkDao.getWeather(parkCode));
		modelHolder.addAttribute("dayOne", parkDao.getDayOneWeather(parkCode));
		return "details";
	}
	
	@RequestMapping(path="/details", method=RequestMethod.POST)
	public String updateFarenheit(String parkCode) {
		parkDao.changeFarenheit();
		return "redirect:/details?parkCode=" + parkCode;
	}
	
	
	@RequestMapping(path="/favorite", method=RequestMethod.GET)
	public String FavoritePage(ModelMap modelHolderF) {
		modelHolderF.put("favs", parkDao.getFavorites());
		modelHolderF.put("count", parkDao.getCounts());
		return "favorite";
	}
	
	
}
