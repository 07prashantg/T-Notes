package com.prashantgoswami.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prashantgoswami.entity.Todo;

@Controller
public class HomeController {
	
	@Autowired
	ServletContext context;
	
	@RequestMapping("/home")
	public String home(Model m)
	{
		String str = "home";
		m.addAttribute("name", str);
		
		
		List<Todo> list= (List<Todo>)context.getAttribute("list");
		m.addAttribute("todos", list);
		return "home";
	}
	
	@RequestMapping("/add")
	public String addToDo(Model m)
	{
//		String str = "add";
//		m.addAttribute("name", str);
		Todo t = new Todo();
		m.addAttribute("name", "add");
		m.addAttribute("todo", t);
		return "home";
	}
	
	@RequestMapping(value = "/saveTodo" ,method = RequestMethod.POST)
	public String saveTodo(@ModelAttribute("todo") Todo t, Model m)
	{
		m.addAttribute("page", "home");
		System.out.println(t);
		t.setTododate(new Date());
		//get the todo list from context
		
		List<Todo> list = (List<Todo>)context.getAttribute("list");
		list.add(t);
		m.addAttribute("msg","Successfully added...");
		return "home";
	}

}
