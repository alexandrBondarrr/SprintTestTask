package com.testTask.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.testTask.model.Part;
import com.testTask.model.PartFilter;
import com.testTask.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PartController {

	@Autowired
	private PartService partService;

	private final int PARTS_PER_PAGE = 10;

	@GetMapping("/")
	public String index(@RequestParam Map<String, String> queryMap, Model model) {
		PartFilter currentFilter = PartFilter.valueOf(queryMap.getOrDefault("filter", "ALL"));
		String query = queryMap.getOrDefault("query", "");
		int page = Integer.valueOf(queryMap.getOrDefault("page", "1"));

		List<Part> parts = partService.list(currentFilter, query);

		int totalParts = parts.size();
		int totalPages = Math.max((int)Math.ceil(totalParts * 1.0 / PARTS_PER_PAGE), 1);

		List<Part> partsOnPage = parts.subList(Math.min((page - 1) * PARTS_PER_PAGE, totalParts), Math.min(page * PARTS_PER_PAGE, totalParts));

		Long totalComputers = parts.stream()
				.filter(Part::getIsRequired)
				.map(Part::getCount)
				.reduce(Long.MAX_VALUE, Math::min);

		if(totalComputers == Long.MAX_VALUE) {
			totalComputers = 0L;
		}

		model.addAttribute("parts", partsOnPage);
		model.addAttribute("totalParts", totalParts);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("totalComputers", totalComputers);
		model.addAttribute("currentFilter", currentFilter);
		model.addAttribute("allFilters", PartFilter.all());
		model.addAttribute("currentQuery", query);
		model.addAttribute("page", page);

		return "index";
	}
	
    @ModelAttribute("part")
	public Part part() {
		return new Part();
	}

	@GetMapping("/add")
	public String addForm(Model model) {
		return "add";
	}

	@GetMapping("/update")
	public String update(@RequestParam("id") long id, Model model) {
		Part part = partService.byId(id);
		model.addAttribute("part", part);
		return "update";
	}

	@PostMapping("/update")
	public String updateUser(@ModelAttribute("part") @Valid Part part, BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("part", part);
			return "update";
		}

//		throw new Exception(part.getName());

		partService.update(part);

		return "redirect:/";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") long id) {
		Part part = partService.byId(id);
		partService.delete(part);
		return "redirect:/";
	}

	@PostMapping("/add")
	public String saveUser(@ModelAttribute("part") @Valid Part part, BindingResult result, Model model) {


		if (result.hasErrors()) {
			model.addAttribute("part", part);
		    return addForm(model);
		}

		partService.save(part);

		return "redirect:/";
	}
}
