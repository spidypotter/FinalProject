package com.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController 
{
	@Autowired
	CoronaVirusDataService coronaVirusDataService;
@GetMapping("/")
public String home(Model model)
{
	List <LocationStats>allStats =(List<LocationStats>) coronaVirusDataService.getAllStats();
	int TotalReportedCases = allStats.stream().mapToInt(stat-> stat.getLatestTotalCases()).sum();
	int TotalNewCases = allStats.stream().mapToInt(stat-> stat.getDiffFromPrevDay()).sum();
	model.addAttribute( "LocationStats",allStats  );
	model.addAttribute( "TotalReportedCases",TotalReportedCases );
	model.addAttribute( "TotalNewCases",TotalNewCases );
	
 return "home"	;
}
}
