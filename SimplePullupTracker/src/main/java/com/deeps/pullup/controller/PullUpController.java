package com.deeps.pullup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deeps.pullup.entity.PullUpSessionEntity;
import com.deeps.pullup.service.PullUpSessionService;
import com.deeps.pullup.view.PullUpSessionView;

@Controller
@RequestMapping("/pullup")
public class PullUpController {

	@Autowired
	PullUpSessionService pullUpSessionService;

	@GetMapping
	public String showPullup() {
		return "pullup";
	}

	@ModelAttribute("pullupsession")
	public PullUpSessionView pullUpSessionView() {
		return new PullUpSessionView();
	}
	
	@ModelAttribute("pullupsessionall")
	public List<PullUpSessionView> pullUpSessionAll() {
		List<PullUpSessionView> views = pullUpSessionService.allSession();
		if (views != null) {
			return views;
		}
		return null;
	}

	@PostMapping
	public String registerPullupSession(@ModelAttribute("pullupsession") PullUpSessionView pullupsession) {
		pullUpSessionService.save(pullupsession);
		return "redirect:/pullup?success";
	}

	@PostMapping("/delete/{identity}")
	public String deleteSession(@PathVariable String identity) {
		pullUpSessionService.deleteSessionByIdentity(Long.parseLong(identity));
		return "redirect:/pullup?deletesuccess";
	}

	@GetMapping("/update/{id}")
	public String showUpdatePullup(@PathVariable Long id, Model model) {
		PullUpSessionEntity entity = pullUpSessionService.getSessionEntityById(id);
		model.addAttribute("pullupsessionentity", entity);
		return "updatepullup";
	} 
 
	@PostMapping("/update/{id}")
	public String updateSession(@PathVariable Long id, @ModelAttribute("pullupsessionentity") PullUpSessionEntity entity) {
		if (pullUpSessionService.updateSessionEntity(id, entity) != null) {
			return "redirect:/pullup?updatesuccess";
		} else {
			return "redirect:/pullup?updatefail";
		}
	}

}
